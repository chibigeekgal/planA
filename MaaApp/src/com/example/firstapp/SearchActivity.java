package com.example.firstapp;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

public class SearchActivity extends Activity{

	private TextView t;
	private ListView l;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.search);
		
		l = (ListView) findViewById(R.id.list);
		t = (TextView) findViewById(R.id.text);
		System.out.println("works");
		handleIntent(getIntent());
		
	}
	
	public void onNewIntent(Intent intent) {
		handleIntent(intent);
	};

	private void handleIntent(Intent intent) {
		System.out.println("search!!");
		if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			showResults(query);
		}
	}

	private void showResults(String query) {

		/* Use sql of somekind, not sure yet....*/
	}
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.main, menu);

	        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
	            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	            SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
	            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
	            searchView.setIconifiedByDefault(false);
	        }

	        return true;
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	            case R.id.menu_search:
	                onSearchRequested();
	                return true;
	            default:
	                return false;
	        }
	    }
	
}
