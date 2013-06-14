package keyboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.firstapp.R;

public class KeyboardEntry extends Activity {

	String result = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.keyboard_entry);
		String s = getIntent().getStringExtra("Extra");
		TextView t = (TextView) findViewById(R.id.dialog_message);
		t.setText(s);
		Button b = (Button) findViewById(R.id.enter_button);

		b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String args = getIntent().getStringExtra("Argument");
				final EditText e = (EditText) findViewById(R.id.content);
				String r = e.getText().toString();
				if (args != null)
					r += args;
				result = r;
				Intent i = new Intent();
				i.putExtra("Argument", result);
				setResult(RESULT_OK, i);
				finish();
			}
		});

		Button symbol = (Button) findViewById(R.id.Symbol_button);
		symbol.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(),
						SymbolKeyboardDisplay.class);
				startActivityForResult(i, 1);
			}

		});
		Button expr = (Button) findViewById(R.id.Expression_button);
		expr.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i2 = new Intent(getApplicationContext(),
						ExpressionKeyboardDisplay.class);
				startActivityForResult(i2, 1);
			}

		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case (1): {
			if (data != null) {
				String newText = data.getStringExtra("Argument");
				if (newText != null) {
					EditText content = (EditText) findViewById(R.id.content);
					String nt = content.getText().toString() + "  " + newText;
					content.setText("");
					content.append(nt);
				}
			}
			break;
		}
		}
	}

}
