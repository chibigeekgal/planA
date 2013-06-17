package homepage;

import java.util.ArrayList;
import java.util.List;

import main.JsonResultHandler;
import main.ServerConnector;
import model.Question;
import model.UserInfo;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.firstapp.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.markupartist.android.widget.PullToRefreshListView;
import com.markupartist.android.widget.PullToRefreshListView.OnRefreshListener;

public class AnswerFragment extends ListFragment {

	private PullToRefreshListView question_list_view; 
	private List<Question> allQuestions;
	private UserInfo user;
	private QuestionViewAdapter questionAdapter;

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
		user = (UserInfo) getActivity().getIntent().getExtras().get("User");
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("request", "get_all"));
		ServerConnector connector = new ServerConnector(getActivity(),
				"/question", pairs, new QuestionListResultsHandler());
		connector.connect();
		
		question_list_view = (PullToRefreshListView) getView()
				.findViewById(android.R.id.list);
		questionAdapter = new QuestionViewAdapter(
				getActivity(), R.layout.question_item, allQuestions);
		question_list_view.setAdapter(questionAdapter);
		question_list_view.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				allQuestions = new ArrayList<Question>();
				user = (UserInfo) getActivity().getIntent().getExtras()
						.get("User");
				ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
				pairs.add(new BasicNameValuePair("request", "get_all"));
				ServerConnector connector = new ServerConnector(
						getActivity(), "/question", pairs,
						new QuestionListResultsHandler());
				connector.connect();
			}

		});
		question_list_view
				.setOnItemClickListener(new OnItemClickListener() {

					public void onItemClick(AdapterView<?> parent,
							View view, int position, long id) {
						Question question = allQuestions.get(position-1);
						Intent intent = new Intent(getActivity(),
								IndividualQuestion.class);
						intent.putExtra("Question", question);
						intent.putExtra("User", user);
						startActivity(intent);
					}

				});
	}

	private class QuestionListResultsHandler extends JsonResultHandler {
		@Override
		public void processJsonResults(JsonElement element) {
			JsonArray questions = element.getAsJsonArray();
			int size = questions.size();
			String[] values = new String[size];
			for (int i = 0; i < size; i++) {
				JsonObject o = questions.get(i).getAsJsonObject();
				Question question = new Question(o);
				values[i] = question.getTitle();
				allQuestions.add(i, question);
			}
			questionAdapter.notifyDataSetChanged();
			((PullToRefreshListView) question_list_view).onRefreshComplete();
		}

	}
}
