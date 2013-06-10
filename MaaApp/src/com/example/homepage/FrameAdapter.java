package com.example.homepage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FrameAdapter extends FragmentPagerAdapter {

	public FrameAdapter(FragmentManager fm) {
		super(fm);

	}

	@Override
	public Fragment getItem(int i) {

		switch (i) {
		case 0:
			// this is this the HomePage
			return new HomePageFragment();
		case 1:
			// I want to ask page
			Fragment askfragment = new AskFragment();
			return askfragment;
		default:
			// I want to answer page
			Fragment answerfragment = new AnswerFragment();
			Bundle args = new Bundle();
			answerfragment.setArguments(args);
			return answerfragment;
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
