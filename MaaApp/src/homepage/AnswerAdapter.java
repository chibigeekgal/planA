package homepage;

import java.util.List;

import model.Answer;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firstapp.R;

public class AnswerAdapter extends ArrayAdapter<Answer> {

	private Context context;

	public AnswerAdapter(Context context, int textViewResourceId,
			List<Answer> answers) {
		super(context, textViewResourceId, answers);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AnswerStruct aStruct = null;
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		Answer a = getItem(position);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.individual_answer, null);
			aStruct = new AnswerStruct();
			aStruct.answer = (ImageView) convertView.findViewById(R.id.answer);
			aStruct.username = (TextView) convertView
					.findViewById(R.id.answer_author);
			convertView.setTag(aStruct);
		} else {
			aStruct = (AnswerStruct) convertView.getTag();
		}
		aStruct.username.setText(a.getUsername());
		aStruct.answer.setImageBitmap(a.getContent());
		return convertView;
	}

	private class AnswerStruct {
		private ImageView answer;
		private TextView username;

	}

}
