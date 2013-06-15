package com.example.firstapp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import keyboard.KeyboardEntry;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.homepage.IndividualQuestion;
import com.example.homepage.QuestionViewAdapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class SearchActivity extends Activity {

	private TextView t;
	private ListView l;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		System.out.println("oh baby");
		setContentView(R.layout.search);
		l = (ListView) findViewById(R.id.searchlist);
		t = (TextView) findViewById(R.id.serchtext);
		handleIntent(getIntent());

	}

	public void onNewIntent(Intent intent) {
		handleIntent(intent);
	};

	private void handleIntent(Intent intent) {
		System.out.println("Hello,world!");
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			showResults(query);
		}
	}

	private void showResults(String query) {
		t.setText(query);
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("substring", query));
		pairs.add(new BasicNameValuePair("request", "search"));
		ServerConnector connector = new ServerConnector(this, "/question",
				pairs, new SearchResultHandler());
		connector.connect();

	}

	private class SearchResultHandler extends JsonResultHandler {
		@Override
		public void processJsonResults(JsonElement element) {
			final List<Question> questions = new LinkedList<Question>();
			JsonArray qjsons = element.getAsJsonArray();
			for (int i = 0; i < qjsons.size(); i++) {
				JsonObject o = qjsons.get(i).getAsJsonObject();
				Question question = new Question(o);
				questions.add(question);
			}
			QuestionViewAdapter adapter = new QuestionViewAdapter(
					SearchActivity.this, R.layout.question_item, questions);
			l.setAdapter(adapter);
			l.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent intent = new Intent(SearchActivity.this,
							IndividualQuestion.class);
					intent.putExtra("Question", questions.get(position));
				}

			});
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.menu_search)
				.getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.extra_symbol:
			Intent i = new Intent(getApplicationContext(), KeyboardEntry.class);
			i.putExtra("Extra", "Please enter the Maths expression!");
			startActivityForResult(i, 1);
			return true;
		case R.id.LogOut:
			Intent i3 = new Intent(getApplicationContext(), MainActivity.class);
			startActivity(i3);
			return true;
		case R.id.menu_search:
			System.out.println("searching");
			onSearchRequested();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
