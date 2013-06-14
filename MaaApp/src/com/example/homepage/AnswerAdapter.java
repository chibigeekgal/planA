package com.example.homepage;

import java.util.List;

import com.example.firstapp.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AnswerAdapter extends ArrayAdapter<Answer> {

	private Context context;

	public AnswerAdapter(Context context, int textViewResourceId,
			List<Answer> answers) {
		super(context, textViewResourceId, answers);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AnswerStruct answer = null;
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		Answer a = getItem(position);
		
		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.individual_answer, null);
			answer = new AnswerStruct();
			answer.answer = (ImageView) convertView.findViewById(R.id.answer);
			answer.username = (TextView) convertView.findViewById(R.id.answer_author);
			convertView.setTag(answer);
		} else {
			answer = (AnswerStruct) convertView.getTag();
		}
		
		answer.answer.setImageResource(a.getAnswer());
		answer.username.setText(a.getUsername());
		return convertView;
	}

	private class AnswerStruct {
		ImageView answer;
		TextView username;
	}

}
