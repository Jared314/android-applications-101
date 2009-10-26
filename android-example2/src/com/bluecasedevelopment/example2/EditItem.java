package com.bluecasedevelopment.example2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;

public class EditItem extends Activity {

	DatePicker date;
	EditText description;
	EditText shop;

	private static final int ID_NOT_SET = -1;
	int id = ID_NOT_SET;
	private static final int SAVE_MENU_ID = 1;
	private static final int CANCEL_MENU_ID = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.edititem);

		this.date = (DatePicker) this.findViewById(R.id.itemdate01);
		this.description = (EditText) this.findViewById(R.id.itemdescription01);
		this.shop = (EditText) this.findViewById(R.id.itemshop01);

		Intent i = this.getIntent();
		Bundle b = i.getExtras();

		this.populateDefaults();

		if (b != null && b.containsKey(Repair._ID)) {
			this.id = b.getInt(Repair._ID);
			this.populate(id);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		menu.add(1, SAVE_MENU_ID, 1, "Save");
		menu.add(1, CANCEL_MENU_ID, 2, "Cancel");

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);

		if (item.getItemId() == SAVE_MENU_ID) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, this.date.getYear());
			c.set(Calendar.MONTH, this.date.getMonth());
			c.set(Calendar.DAY_OF_MONTH, this.date.getDayOfMonth());

			String value = this.description.getText().toString();
			String shop = this.shop.getText().toString();

			Uri result = this.save(this.id, c, value, shop);
			if (result != null) {
				this
						.setResult(Activity.RESULT_OK, new Intent()
								.setData(result));
				this.finish();
			}

		} else if (item.getItemId() == CANCEL_MENU_ID) {
			this.setResult(Activity.RESULT_CANCELED);
			this.finish();
		}

		return true;
	}

	private void populateDefaults() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);

		this.date.updateDate(year, month, day);
	}

	private void populate(int id) {
		String stext = "[empty]";
		String shopText = "[empty]";
		Date date = new Date();

		ContentResolver cr = this.getContentResolver();
		Uri uri = Repair.CONTENT_URI;
		Cursor c = cr.query(uri, null, Repair._ID + " = ?",
				new String[] { String.valueOf(id) }, null);
		this.startManagingCursor(c);

		if (c != null && c.getCount() > 0) {
			c.moveToFirst();

			int descriptionColumnNumber = c.getColumnIndex(Repair.DESCRIPTION);
			int shopColumnNumber = c.getColumnIndex(Repair.SHOP);
			int dateColumnNumber = c.getColumnIndex(Repair.DATE);

			stext = c.getString(descriptionColumnNumber);
			shopText = c.getString(shopColumnNumber);

			SimpleDateFormat formatter = new SimpleDateFormat(
					Repair.DATE_FORMAT);
			try {
				date = formatter.parse(c.getString(dateColumnNumber));
			} catch (ParseException e) {
				// e.printStackTrace();
			}
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);

		this.description.setText(stext);
		this.shop.setText(shopText);
		this.date.updateDate(year, month, day);
	}

	private Uri save(int id, Calendar date, String description, String shop) {

		long l = date.getTimeInMillis();
		String datestring = DateFormat.format(Repair.DATE_FORMAT, l).toString();

		ContentValues values = new ContentValues(3);
		values.put(Repair.DESCRIPTION, description);
		values.put(Repair.SHOP, shop);
		values.put(Repair.DATE, datestring);

		ContentResolver cr = this.getContentResolver();

		Uri resultUri = null;
		int result;
		if (id == ID_NOT_SET) {
			resultUri = cr.insert(Repair.CONTENT_URI, values);
			result = (int) ContentUris.parseId(resultUri);
		} else {
			resultUri = ContentUris.withAppendedId(Repair.CONTENT_URI, id);
			result = cr.update(resultUri, values, Repair._ID + " = ?",
					new String[] { String.valueOf(id) });
		}

		// TODO: throw error

		return (result > 0) ? resultUri : null;
	}
}
