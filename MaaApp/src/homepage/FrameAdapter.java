package homepage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class FrameAdapter extends FragmentPagerAdapter {
	private Fragment[] fragments;

	public FrameAdapter(FragmentManager fm) {
		super(fm);
		fragments = new Fragment[3];
	}

	public Fragment getFragment(int i) {
		return fragments[i];
	}

	@Override
	public Fragment getItem(int i) {
		Log.d("GetItem", "getitem done " + i);
		switch (i) {
		case 0:
			Fragment f = new HomePageFragment();
			fragments[0] = f;
			return f;
		case 1:
			// I want to ask page
			Fragment askfragment = new AskFragment();
			fragments[1] = askfragment;
			return askfragment;
		case 2:
			// I want to answer page
			Fragment answerfragment = new AnswerFragment();
			fragments[2] = answerfragment;
			return answerfragment;
		default:
			return null;
		}

	}

	// we have 3 different contents : Homepage,Ask,Answer
	@Override
	public int getCount() {
		return 3;
	}

	// the name for each tag
	// HomePage, Ask,Answer
	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
		case 0:
			return "HomePage";
		case 1:
			return "I want to ASK";
		default:
			return "I want to Answer";

		}
	}

}
