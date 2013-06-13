package keyboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.firstapp.R;

public class KeyboardEntry extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.keyboard_entry);
		Button b = (Button) findViewById(R.id.enter_button);

		final String s = getIntent().getStringExtra("Argument");

		final EditText e = (EditText) findViewById(R.id.content);
		b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				String value = e.getText().toString();
				String[] parts = value.split(" ");
				System.out.println("what's in the editable" + value);
				String ree = " ";
				if (s.equals("X<sup>2<sup>")) {
					ree = parts[0] + "_{" + parts[1] + "}";
				} else if (s.equals("X<sub>2<sub>")) {
					ree = parts[0] + "^{" + parts[1] + "}";
				} else if (s.equals("Fraction")) {
					ree = "\\\\frac{" + parts[0] + "}{" + parts[1] + "}";
				} else if (s.equals("NthRoot")) {
					ree = "\\\\sqrt[" + parts[0] + "]" + "{" + parts[1] + "}";
				} else if (s.equals("Integral")) {
					ree = "\\\\int_{" + parts[0] + "}^{" + parts[1] + "}"
							+ parts[2];
				} else if (s.equals("sum")) {
					ree = "\\\\sum_{" + parts[0] + "}^{" + parts[1] + "}"
							+ parts[2];
				}

				Intent resultIntent = new Intent();
				resultIntent.putExtra("Expr", ree + " ");
				setResult(Activity.RESULT_OK, resultIntent);
				finish();
				// EditText result = (EditText)
				// getCallingActivity().findViewById(R.id.content);
				// result.setText(result.getText().toString() + ree + " ");
			}
		});

		Button symbol = (Button) findViewById(R.id.Symbol_button);
		symbol.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(),
						SymbolKeyboardDisplay.class);
				KeyboardDisplay.current_activity = (Activity) arg0.getContext();
				startActivityForResult(i, 1);
			}

		});
		Button expr = (Button) findViewById(R.id.Expression_button);
		expr.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i2 = new Intent(getApplicationContext(),
						ExpressionKeyboardDisplay.class);
				KeyboardDisplay.current_activity = (Activity) v.getContext();
				;
				startActivityForResult(i2, 1);
			}

		});
		// HomePageActivity.currentActivity = this;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case (1): {
				String newText = data.getStringExtra("Expr");
				EditText result = (EditText) findViewById(R.id.content);
				// result.setText(result.getText().toString() + ree + " ");
				result.setText(result.getText().toString() + "  " + newText);
			break;
		}
		}
	}

	public void postString() {
		/*
		 * EditText e = (EditText) findViewById(R.id.q_content); if(e != null) {
		 * e.setText(string); System.out.println("postsymbol" + string); }
		 */
	}

}
