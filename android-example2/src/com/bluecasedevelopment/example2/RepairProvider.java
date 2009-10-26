package com.bluecasedevelopment.example2;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class RepairProvider extends ContentProvider {

	private RepairDbOpenHelper helper;

	private static final RepairMatcher matcher = new RepairMatcher();

	private void replaceElement(String[] values, String searchString,
			String replacementValue) {
		if (values == null || searchString == null)
			return;

		for (int i = 0; i < values.length; i++) {
			if (values[i].contains(searchString))
				values[i] = values[i].replace(searchString, replacementValue);
		}
	}

	private String[] ensureElement(String[] values, String searchString) {
		if (values == null)
			return null;

		List<String> l = new ArrayList<String>();
		boolean found = false;
		for (String s : values) {
			l.add(s);
			if (s.equals(searchString)) {
				found = true;
				break;
			}
		}

		if (found)
			return values;

		if (!l.contains(searchString)) {
			l.add(searchString);
		}
		return l.toArray(new String[l.size()]);
	}

	@Override
	public boolean onCreate() {
		this.helper = new RepairDbOpenHelper(this.getContext());

		return (helper != null);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		if (this.getType(uri) != Repair.CONTENT_ITEM_TYPE)
			throw new UnsupportedOperationException();

		if (selection != null)
			selection = selection.replace(Repair._ID, "ROWID");
		replaceElement(selectionArgs, Repair._ID, "ROWID");

		SQLiteDatabase db = this.helper.getWritableDatabase();
		int count = db.delete(Repair.TABLE_NAME, selection, selectionArgs);
		if (count > 0) {
			this.getContext().getContentResolver().notifyChange(uri, null);
		}
		return count;
	}

	@Override
	public String getType(Uri uri) {
		String result = null;

		switch (matcher.match(uri)) {
		case RepairMatcher.REPAIRS:
			result = Repair.CONTENT_TYPE;
			break;
		case RepairMatcher.REPAIR_ID:
			result = Repair.CONTENT_ITEM_TYPE;
			break;
		}

		return result;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		if (this.getType(uri) != Repair.CONTENT_TYPE)
			throw new UnsupportedOperationException();

		Uri result = null;

		SQLiteDatabase db = this.helper.getWritableDatabase();

		long newId = db.insert(Repair.TABLE_NAME, null, values);
		if (newId > -1) {
			result = ContentUris.withAppendedId(Repair.CONTENT_URI, newId);
			this.getContext().getContentResolver().notifyChange(result, null);
		}

		return result;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		if (sortOrder == null)
			sortOrder = Repair.DEFAULT_SORT_ORDER;
		if (projection == null)
			projection = new String[] { Repair._ID, Repair.DATE,
					Repair.DESCRIPTION, Repair.SHOP };

		projection = ensureElement(projection, Repair._ID);
		replaceElement(projection, Repair._ID, "ROWID as " + Repair._ID);

		if (selection != null)
			selection = selection.replace(Repair._ID, "ROWID");
		replaceElement(selectionArgs, Repair._ID, "ROWID");
		if (sortOrder != null)
			sortOrder = sortOrder.replace(Repair._ID, "ROWID");

		SQLiteDatabase db = this.helper.getReadableDatabase();

		SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
		builder.setTables(Repair.TABLE_NAME);
		Cursor c = builder.query(db, projection, selection, selectionArgs,
				null, null, sortOrder);

		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		if (this.getType(uri) != Repair.CONTENT_ITEM_TYPE)
			throw new UnsupportedOperationException();

		if (selection != null)
			selection = selection.replace(Repair._ID, "ROWID");
		replaceElement(selectionArgs, Repair._ID, "ROWID");

		SQLiteDatabase db = this.helper.getWritableDatabase();
		int count = db.update(Repair.TABLE_NAME, values, selection,
				selectionArgs);
		if (count > 0) {
			this.getContext().getContentResolver().notifyChange(uri, null);
		}
		return count;
	}
}
