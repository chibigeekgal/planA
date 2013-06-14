package com.example.homepage;

import java.util.ArrayList;
import java.util.List;

import keyboard.ExpressionKeyboardDisplay;
import keyboard.SymbolKeyboardDisplay;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.firstapp.R;

public class IndividualQuestion extends Activity {

	public static final String[] usernames = new String[] { "dr411", "xs1511",
			"dz1611", "hs2711" };

	public static final Integer[] answersImage = { R.drawable.temp_answer_pic,
			R.drawable.temp_answer_pic, R.drawable.temp_answer_pic,
			R.drawable.temp_answer_pic };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.individual_question);

		ImageView question = (ImageView) findViewById(R.id.question);
		question.setImageResource(R.drawable.temp_question_pic);
		
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
				String args = getIntent().getStringExtra("Argument");
				final EditText e = (EditText) findViewById(R.id.answer_section);
				String r = e.getText().toString();
				if (args != null)
					r += args;
				String result = r;
				Intent i = new Intent();
				i.putExtra("Argument", result);
				setResult(RESULT_OK, i);
				finish();
			}

		});

		String title = getIntent().getStringExtra("Title");
		String author = getIntent().getStringExtra("Author");
		TextView t = (TextView) findViewById(R.id.question_title);
		TextView q = (TextView) findViewById(R.id.author);
		System.out.println(title);
		System.out.println(author);
		t.setText(title);
		q.setText(author);

		List<Answer> answers = new ArrayList<Answer>();
		for (int i = 0; i < usernames.length; i++) {
			Answer item = new Answer(answersImage[i], usernames[i]);
			answers.add(item);
		}

		ListView answer_list = (ListView) findViewById(R.id.answer_list);
		AnswerAdapter adapter = new AnswerAdapter(this,
				R.layout.individual_answer, answers);
		answer_list.setAdapter(adapter);
		
		answer_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Answer a = (Answer) arg0.getItemAtPosition(arg2);
				int i  = a.getAnswer();
				System.out.println(i);
			}
		});
		/* For the answer lists */
		registerForContextMenu(answer_list);
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
}
