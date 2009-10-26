package com.bluecasedevelopment.example3;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;

public class RepairReportService extends Service {

	private static final String REPORTING_SERVICE_PATH = "http://www.bluecasedevelopment.com/does_not_exist/";

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);

		String action = intent.getAction();
		Uri data = intent.getData();
		if (action != null && data != null) {
			long id = ContentUris.parseId(data);

			makeRequest(REPORTING_SERVICE_PATH, id, action);
			this.stopSelf();
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return bindingInstance;
	}

	// Never used
	private final IRepairReportService.Stub bindingInstance = new IRepairReportService.Stub() {
		@Override
		public void insert(long id) throws RemoteException {
			makeRequest(REPORTING_SERVICE_PATH, id, "insert");
		}

		@Override
		public void delete(long id) throws RemoteException {
			makeRequest(REPORTING_SERVICE_PATH, id, "delete");
		}

		@Override
		public void update(long id) throws RemoteException {
			makeRequest(REPORTING_SERVICE_PATH, id, "update");
		}
	};

	private static String makeRequest(String path, Long id, String action) {
		String result = null;

		path = path + "?id=" + id.toString() + "&action=" + action;

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(path);
		try {
			result = httpclient.execute(httpget, new BasicResponseHandler());
		} catch (Exception e) {

		}
		return result;
	}
}
