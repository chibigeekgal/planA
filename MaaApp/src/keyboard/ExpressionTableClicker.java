package keyboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.example.homepage.HomePageActivity;

public class ExpressionTableClicker implements OnItemClickListener {


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		final String s = ((TextView) arg1).getText().toString();

		AlertDialog.Builder alert = new AlertDialog.Builder(
				KeyboardDisplay.current_activity);

		alert.setTitle("Title");
		alert.setMessage("Message");

		// Set an EditText view to get user input
		final EditText input = new EditText(KeyboardDisplay.current_activity);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				String value = input.getText().toString();
				String[] parts = value.split(" ");
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
				HomePageActivity.string += ree+ " ";
			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
					}
				});

		alert.show();

	}

}
