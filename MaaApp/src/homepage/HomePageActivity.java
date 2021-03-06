package homepage;

import keyboard.KeyboardEntry;
import model.UserInfo;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.firstapp.R;

public class HomePageActivity extends FragmentActivity implements
		ActionBar.TabListener {

	private FrameAdapter adapter;
	private ViewPager viewPagerControl;
	private UserInfo user;

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
        user=(UserInfo) getIntent().getExtras().getSerializable("User");
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

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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
			finish();
			return true;
		case R.id.menu_search:
			onSearchRequested();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void startActivity(Intent intent) {

		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			intent.putExtra("User", user);
		}
		super.startActivity(intent);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case (1): {
			if (resultCode == RESULT_OK) {

				EditText result = (EditText) findViewById(R.id.content);
				if (result.hasFocus()) {
					String newText = result.getText().toString() + " "
							+ data.getStringExtra("Argument");
					result.setText("");
					result.append(newText);
				}
			}
			break;
		}
		}
	}

	@Override
	public void onBackPressed() {

	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

}
