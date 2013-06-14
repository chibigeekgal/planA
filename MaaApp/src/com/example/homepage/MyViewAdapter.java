package com.example.homepage;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firstapp.Question;
import com.example.firstapp.R;

public class MyViewAdapter extends ArrayAdapter<Question> {

	Context context;

	public MyViewAdapter(Context context, int resourceId, List<Question> items) {
		super(context, resourceId, items);
		this.context = context;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewStruct holder = null;
		Question QuestionItem = getItem(position);

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.question_item, null);
			holder = new ViewStruct();
			holder.usename = (TextView) convertView.findViewById(R.id.username);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.personal_pic = (ImageView) convertView
					.findViewById(R.id.icon);
			convertView.setTag(holder);
		} else
			holder = (ViewStruct) convertView.getTag();

		holder.usename.setText(QuestionItem.getUser());
		holder.title.setText(QuestionItem.getTitle());
		holder.personal_pic.setImageBitmap(QuestionItem.getPersonalIcon());

		return convertView;
	}

	private class ViewStruct {
		ImageView personal_pic;
		TextView title;
		TextView usename;
	}
}
