package com.example.homepage;

import com.example.firstapp.R;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firstapp.UserInfo;

public class HomePageFragment extends Fragment {
	private UserInfo user;
	private String personalPicPath;

	String[] qs = { "What's the definition of a Group?",
			"What's the meaning of life?", "Is 1 + 1 actually 2?" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View homePageView = inflater.inflate(R.layout.homepage_structure,
				container, false);
		user = (UserInfo) getActivity().getIntent().getExtras().get("User");
		// select a picture
		homePageView.findViewById(R.id.personal_profile).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Intent intent = new Intent(getActivity(),
								PersonalProfile.class);
						intent.putExtra("User", user);
						startActivity(intent);
					}
				});

		return homePageView;
	}

	//setting the personal picture
	
	
	
	
	
	
	
	
}
