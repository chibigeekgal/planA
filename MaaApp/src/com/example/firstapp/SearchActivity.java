package com.example.firstapp;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class SearchActivity extends Activity{

	private TextView t;
	private ListView l;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.search);
		
		l = (ListView) findViewById(R.id.list);
		t = (TextView) findViewById(R.id.text);
		
		handleIntent(getIntent());
		
	}
	
	protected void onNewIntent(Intent intent) {
		handleIntent(intent);
	};

	private void handleIntent(Intent intent) {
		if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			showResults(query);
		}
	}

	private void showResults(String query) {

		/* Use sql of somekind, not sure yet....*/
		
		
	}
	
}
