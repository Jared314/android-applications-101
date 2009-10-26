package com.bluecasedevelopment.example2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;

public class ViewItem extends Activity {

	DatePicker date;
	EditText description;
	EditText shop;

	private static final int EDIT_REQUEST = 1;
	private static final int ID_NOT_SET = -1;
	int id = ID_NOT_SET;
	int menuId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.edititem);

		this.date = (DatePicker) this.findViewById(R.id.itemdate01);
		this.description = (EditText) this.findViewById(R.id.itemdescription01);
		this.shop = (EditText) this.findViewById(R.id.itemshop01);

		// Make read only
		this.date.setEnabled(false);
		this.date.setFocusable(false);
		this.description.setEnabled(false);
		this.description.setFocusable(false);
		this.shop.setEnabled(false);
		this.shop.setFocusable(false);

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

		MenuItem m = menu.add("Edit");
		this.menuId = m.getItemId();

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);

		if (item.getItemId() == this.menuId) {
			Intent i = new Intent(Intent.ACTION_EDIT).setType(
					Repair.CONTENT_ITEM_TYPE).putExtra(Repair._ID, id);
			this.startActivityForResult(i, EDIT_REQUEST);
		}

		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == EDIT_REQUEST && resultCode == Activity.RESULT_OK) {
			this.populate(id);
		}
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
}
