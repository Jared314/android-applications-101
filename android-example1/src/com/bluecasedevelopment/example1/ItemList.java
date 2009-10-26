package com.bluecasedevelopment.example1;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ItemList extends ListActivity {

	private final String[] data = new String[] { "item1", "item2", "item3" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.listitems, R.id.listitem01, this.data);
		this.setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(this, ItemView.class).putExtra("id", id)
				.putExtra("name", data[(int) id]);
		this.startActivity(i);
	}
}
