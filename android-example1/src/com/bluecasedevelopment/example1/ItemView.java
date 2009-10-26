package com.bluecasedevelopment.example1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ItemView extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.itemview);

		Intent i = this.getIntent();
		Bundle b = i.getExtras();
		String name = b.getString("name");

		TextView t = (TextView) this.findViewById(R.id.TextView01);
		t.setText(name);
	}
}
