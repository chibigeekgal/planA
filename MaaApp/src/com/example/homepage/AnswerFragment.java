package com.example.homepage;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.firstapp.JsonResultHandler;
import com.example.firstapp.Library;
import com.example.firstapp.Question;
import com.example.firstapp.R;
import com.example.firstapp.ServerConnector;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class AnswerFragment extends ListFragment {

	/*public static final String[] titles = new String[] { "dummy", "dummy",
			"dummy", "dummy" };

	public static final String[] descriptions = new String[] { "It is an Q",
			"It is an Q", "It is an Q", "It is an Q" };

	public static final Integer[] images = { R.drawable.default_pic,
			R.drawable.default_pic, R.drawable.default_pic,
			R.drawable.default_pic };*/

	private List<Question> allQuestions;

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
		allQuestions = new ArrayList<Question>();
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("request", "get_all"));
		ServerConnector connector = new ServerConnector(getActivity(),
				"/question", pairs, new QuestionListResultsHandler());
		connector.connect();
		Log.d("Connect", "connect");
		Log.d("size2", ((Integer) allQuestions.size()).toString());

	}

	private class QuestionListResultsHandler extends JsonResultHandler {
		@Override
		public void processJsonResults(JsonElement element) {
			JsonArray questions = element.getAsJsonArray();
			int size = questions.size();
			String[] values = new String[size];
			for (int i = 0; i < size; i++) {
				JsonObject o = questions.get(i).getAsJsonObject();
				String username = Library.convertToString(o.get("username"));
				String title = Library.convertToString(o.get("title"));
				values[i] = title;
				int index = o.get("index").getAsInt();
				int bestAnswer = o.get("best_answer").getAsInt();
				allQuestions.add(i, new Question(index, bestAnswer, username,
						title));
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					getActivity(), R.layout.row_layout_view, R.id.label, values);
			setListAdapter(adapter);
			Log.d("size1", ((Integer) allQuestions.size()).toString());
		}

	}
}
