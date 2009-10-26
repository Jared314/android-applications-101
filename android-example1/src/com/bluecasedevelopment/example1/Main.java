package com.bluecasedevelopment.example1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);

		final Main m = this;
		Button b = (Button) this.findViewById(R.id.clickMeButton01);
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				m.startActivity(new Intent(m, ItemList.class));
			}
		});
	}
}