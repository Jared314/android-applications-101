package com.bluecasedevelopment.example2;

import android.net.Uri;
import android.provider.BaseColumns;

public final class Repair implements BaseColumns {
	public static final String DATE = "date";
	public static final String DESCRIPTION = "description";
	public static final String SHOP = "shop";

	public static final String TABLE_NAME = "repairs";
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.bluecasedevelopment.example2.repair";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.bluecasedevelopment.example2.repair";
	public static final String AUTHORITY = "com.bluecasedevelopment.example2";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/repair");
	public static final String DEFAULT_SORT_ORDER = DATE + " DESC";
	public static final String DATE_FORMAT = "yyyy-MM-dd kk:mm:ss";
}
