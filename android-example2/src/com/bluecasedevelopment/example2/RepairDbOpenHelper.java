package com.bluecasedevelopment.example2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RepairDbOpenHelper extends SQLiteOpenHelper {
	public static final String DB_FILE_NAME = "repairs.db";

	public RepairDbOpenHelper(Context c, int version) {
		super(c, DB_FILE_NAME, null, version);
	}

	public RepairDbOpenHelper(Context c) {
		this(c, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + Repair.TABLE_NAME + " (" + Repair.DATE
				+ " TEXT, " + Repair.DESCRIPTION + " TEXT, " + Repair.SHOP
				+ " TEXT );");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + Repair.TABLE_NAME + ";");
		this.onCreate(db);
	}
}
