package com.example.homepage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firstapp.R;

public class AnswerFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// setting up the view
		View subFragmentView = inflater.inflate(R.layout.answerfragment_view,
				container, false);
		Bundle args = getArguments();

		return subFragmentView;
	}
}
