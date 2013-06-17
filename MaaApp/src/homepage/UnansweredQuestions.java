package homepage;

import java.util.ArrayList;
import java.util.List;

import main.JsonResultHandler;
import main.ServerConnector;
import model.Question;
import model.UserInfo;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.firstapp.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class UnansweredQuestions extends Activity{

	private UserInfo user;
	private List<Question> allQuestions;
	private ListView l;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		allQuestions = new ArrayList<Question>();
		user = (UserInfo) getIntent().getExtras().getSerializable("User");
		
		
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("request", "get_question_info"));
		pairs.add(new BasicNameValuePair("username", user.getUsername()));
		ServerConnector connector = new ServerConnector(this,
				"/question", pairs, new QuestionListResultsHandler());
		connector.connect();
		
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
			l = (ListView) findViewById(R.id.unanswered_list);
			QuestionViewAdapter questionAdapter = new QuestionViewAdapter(
					UnansweredQuestions.this, R.layout.question_item, allQuestions);
			l.setAdapter(questionAdapter);
			l.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> parent,
						View view, int position, long id) {
					Question question = allQuestions.get(position);
					Intent intent = new Intent(UnansweredQuestions.this,
							IndividualQuestion.class);
					intent.putExtra("Question", question);
					intent.putExtra("User", user);
					startActivity(intent);
				}

			});
		}
		
	}
	
	
}
