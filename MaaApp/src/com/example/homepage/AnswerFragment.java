package com.example.homepage;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.firstapp.R;

public class AnswerFragment extends ListFragment {

	public static final String[] titles = new String[] { "groups", "algebra",
			"analysis", "mike" };

	public static final String[] descriptions = new String[] { "dr411",
			"xs1511", "dz1611", "hs2711" };

	public static final Integer[] images = { R.drawable.default_pic,
			R.drawable.default_pic, R.drawable.default_pic,
			R.drawable.default_pic };

	ListView question_list_view;
	List<QuestionWrap> QuestionList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View subFragmentView = inflater.inflate(R.layout.answerfragment_view,
				container, false);
		return subFragmentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		QuestionList = new ArrayList<QuestionWrap>();
		for (int i = 0; i < titles.length; i++) {
			QuestionWrap item = new QuestionWrap(images[i], titles[i],
					descriptions[i]);
			QuestionList.add(item);
		}

		question_list_view = (ListView) getView().findViewById(
				android.R.id.list);
		MyViewAdapter adapter = new MyViewAdapter(getActivity(),
				R.layout.question_item, QuestionList);
		question_list_view.setAdapter(adapter);
		question_list_view.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				QuestionWrap q = (QuestionWrap) parent.getItemAtPosition(position);
				Intent intent = new Intent(getActivity(),
						IndividualQuestion.class);
				intent.putExtra("Title", q.getTitle());
				intent.putExtra("Author", q.getUsername());
				startActivity(intent);

			}

		});

	}

}
