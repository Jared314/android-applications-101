package com.bluecasedevelopment.example3;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class RepairReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent i = new Intent(context, RepairReportService.class);
		i = i.setData(intent.getData()).setAction(intent.getAction());

		ComponentName cn = context.startService(i);
		if (cn != null) {

		}
	}
}
