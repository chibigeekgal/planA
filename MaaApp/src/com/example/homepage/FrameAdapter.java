package com.example.homepage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FrameAdapter extends FragmentPagerAdapter {

	public FrameAdapter(FragmentManager fm) {
		super(fm);

	}

	@Override
	public Fragment getItem(int i) {
		return new HomePageFragment();

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
			return "ASK";
		default:
			return "Answers";

		}
	}

}
