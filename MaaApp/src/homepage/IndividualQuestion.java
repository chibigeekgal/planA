package homepage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import keyboard.ExpressionKeyboardDisplay;
import keyboard.SymbolKeyboardDisplay;
import main.BitmapResultHandler;
import main.JsonResultHandler;
import main.Library;
import main.ServerConnector;
import main.StringResultHandler;
import model.Answer;
import model.Question;
import model.UserInfo;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.firstapp.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class IndividualQuestion extends Activity {
	private Question question;
	private UserInfo user;
	private ImageView questionView;
	private List<Answer> answers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.individual_question);
		question = (Question) getIntent().getSerializableExtra("Question");
		user = (UserInfo) getIntent().getSerializableExtra("User");
		questionView = (ImageView) findViewById(R.id.question);
		setOnClickListener();
		drawContent();

		TextView t = (TextView) findViewById(R.id.question_title);
		TextView q = (TextView) findViewById(R.id.author);
		t.setText(question.getTitle());
		q.setText(question.getUser());
		getAnswers();

		/*
		 * answer_list.setOnItemClickListener(new OnItemClickListener() {
		 * 
		 * @Override public void onItemClick(AdapterView<?> arg0, View arg1, int
		 * arg2, long arg3) { Answer a = (Answer) arg0.getItemAtPosition(arg2);
		 * Bitmap b = a.getAnswer(); Intent fullImage = new
		 * Intent(IndividualQuestion.this, FullImage.class);
		 * fullImage.putExtra("Image", b); startActivity(fullImage); } });
		 */
	}

	private void drawContent() {
		List<NameValuePair> pairs = new LinkedList<NameValuePair>();
		Log.d("Index", ((Integer) question.getIndex()).toString());
		pairs.add(new BasicNameValuePair("request", "get_content"));
		pairs.add(new BasicNameValuePair("index", ((Integer) question
				.getIndex()).toString()));

		ServerConnector questionConnector = new ServerConnector(this,
				"/question", pairs, new QuestionContentResultHandler());
		questionConnector.connect();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case (1): {
			if (data != null) {
				String newText = data.getStringExtra("Argument");
				if (newText != null) {
					EditText content = (EditText) findViewById(R.id.answer_section);
					String nt = content.getText().toString() + "  " + newText;
					content.setText("");
					content.append(nt);
				}
			}
			break;
		}
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.answer_option, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.best_answer:
			System.out.println("best answer!!");
			return true;
		case R.id.spam:
			System.out.println("oooh, spamming eh?");
			return true;
		}
		return super.onContextItemSelected(item);
	}

	private class QuestionContentResultHandler extends BitmapResultHandler {

		@Override
		protected void processBitmapResults(Bitmap results) {
			questionView.setImageBitmap(results);
		}
	}

	private class NewAnswerResultHandler extends StringResultHandler {

		@Override
		protected void processStringResults(String results) {
			Library.showAlert(IndividualQuestion.this,
					"Your answer has been posted");
			Intent intent = new Intent(IndividualQuestion.this,
					HomePageActivity.class);
			intent.putExtra("User", user);
			startActivity(intent);
		}

	}

	private void setOnClickListener() {
		Button sym = (Button) findViewById(R.id.Symbol);
		sym.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						SymbolKeyboardDisplay.class);
				startActivityForResult(i, 1);
			}
		});

		Button expr = (Button) findViewById(R.id.Expressions);
		expr.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i2 = new Intent(getApplicationContext(),
						ExpressionKeyboardDisplay.class);
				startActivityForResult(i2, 1);
			}

		});

		Button enter = (Button) findViewById(R.id.Enter);
		enter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final EditText e = (EditText) findViewById(R.id.answer_section);
				String r = e.getText().toString();
				String result = r;
				List<NameValuePair> pairs = new ArrayList<NameValuePair>();
				pairs.add(new BasicNameValuePair("username", user.getUsername()));
				pairs.add(new BasicNameValuePair("index", String
						.valueOf(question.getIndex())));
				pairs.add(new BasicNameValuePair("content", result));
				pairs.add(new BasicNameValuePair("request", "answer"));
				new ServerConnector(IndividualQuestion.this, "/answer", pairs,
						new NewAnswerResultHandler()).connect();
			}

		});
	}

	private void getAnswers() {
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("index", question.getIndex()
				.toString()));
		pairs.add(new BasicNameValuePair("request", "get_answers"));
		ServerConnector connector = new ServerConnector(this, "/answer", pairs,
				new AnswerListResultHandler());
		connector.connect();
	}

	private class AnswerListResultHandler extends JsonResultHandler {

		@Override
		public void processJsonResults(JsonElement element) {
			JsonArray ajsons = element.getAsJsonArray();
			System.out.println(ajsons);
			answers = new ArrayList<Answer>();
			for (int i = 0; i < ajsons.size(); i++) {
				JsonObject ajson = ajsons.get(i).getAsJsonObject();
				String username = Library
						.convertToString(ajson.get("username"));
				String contentString = Library.convertToString(ajson
						.get("content"));
				byte[] ba = Base64.decode(contentString, Base64.DEFAULT);
				Bitmap content = BitmapFactory
						.decodeByteArray(ba, 0, ba.length);
				answers.add(new Answer(username, content));
			}
			ListView answer_list_view = (ListView) findViewById(R.id.answer_list);
			answer_list_view.setAdapter(new AnswerAdapter(
					IndividualQuestion.this, R.layout.individual_answer,
					answers));

		}
	}
}
