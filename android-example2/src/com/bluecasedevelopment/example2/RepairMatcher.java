package com.bluecasedevelopment.example2;

import android.content.UriMatcher;

public class RepairMatcher extends UriMatcher {
	public static final int REPAIRS = 1;
	public static final String REPAIRS_PATTERN = "repair";

	public static final int REPAIR_ID = 2;
	public static final String REPAIR_ID_PATTERN = "repair/#";

	public RepairMatcher() {
		super(UriMatcher.NO_MATCH);
		this.addURI(Repair.AUTHORITY, REPAIRS_PATTERN, REPAIRS);
		this.addURI(Repair.AUTHORITY, REPAIR_ID_PATTERN, REPAIR_ID);
	}
}
