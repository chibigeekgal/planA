package com.example.homepage;

import keyboard.ExpressionKeyboardDisplay;
import keyboard.SymbolKeyboardDisplay;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.firstapp.IndividualQuestion;
import com.example.firstapp.R;

public class HomePageActivity extends FragmentActivity implements
		ActionBar.TabListener {

	private FrameAdapter adapter;
	private ViewPager viewPagerControl;
	public static String string = "";
	public static ImageView image;
	public static Activity currentActivity;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage_view);
		// adapter that return a fragment for the sections
		adapter = new FrameAdapter(getSupportFragmentManager());

		final ActionBar actionBar = getActionBar();

		actionBar.setHomeButtonEnabled(false);

		// Specify that we will be displaying tabs in the action bar.
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// ViewPager listeners setting up, attaching the adapter

		viewPagerControl = (ViewPager) findViewById(R.id.pager);
		viewPagerControl.setAdapter(adapter);
		viewPagerControl
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < adapter.getCount(); i++) {
			actionBar.addTab(actionBar.newTab()
					.setText(adapter.getPageTitle(i)).setTabListener(this));
		}
		currentActivity = this;
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		viewPagerControl.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.Symbol:
			Intent i = new Intent(getApplicationContext(),
					SymbolKeyboardDisplay.class);
			startActivityForResult(i, 1);
			// postString();
			return true;
		case R.id.Expression:
			Intent i2 = new Intent(getApplicationContext(),
					ExpressionKeyboardDisplay.class);
			startActivityForResult(i2, 1);
			// postString();
			return true;
		case R.id.setting:
			Intent i3 = new Intent(getApplicationContext(),
					IndividualQuestion.class);
			startActivity(i3);
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void postString() {
		EditText e = (EditText) findViewById(R.id.q_content);
		if (e != null) {
			e.setText(string);
			System.out.println("postsymbol" + string);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case (1): {
				String newText = data.getStringExtra("Expr");
				EditText result = (EditText) findViewById(R.id.q_content);
				// result.setText(result.getText().toString() + ree + " ");
				result.setText(result.getText().toString() + "  " + newText);
			break;
		}
		}

	}
}
