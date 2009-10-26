package com.bluecasedevelopment.example3;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ItemList extends ListActivity {

	int menuId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ContentResolver cr = this.getContentResolver();
		Cursor c = cr.query(Repair.CONTENT_URI, null, null, null, null);

		if (c != null) {
			this.startManagingCursor(c);

			SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
					R.layout.listitems, c, new String[] { Repair.DESCRIPTION },
					new int[] { R.id.listitem01 });

			this.setListAdapter(adapter);
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Intent i = new Intent(Intent.ACTION_VIEW).setType(
				Repair.CONTENT_ITEM_TYPE).putExtra(Repair._ID, (int) id);
		this.startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		MenuItem m = menu.add("Create");
		this.menuId = m.getItemId();

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);

		if (item.getItemId() == this.menuId) {
			Intent i = new Intent(Intent.ACTION_INSERT)
					.setType(Repair.CONTENT_ITEM_TYPE);
			this.startActivityForResult(i, 0);
		}

		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

	}
}