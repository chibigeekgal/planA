package keyboard;

import java.util.HashMap;

import com.example.firstapp.Library;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

public class ExpressionKeyboardDisplay extends KeyboardDisplay {

	String s;

	@Override
	public void fillInHashMap() {
		latexMap = new HashMap<String, String>();
		latexMap.put("Powers",
				"Please enter the base and the exponential number separated by a space");
		latexMap.put("Subscripts".toString(),
				"Please enter the number and the subscript separated by a space");
		latexMap.put("Fraction",
				"Please enter the numerator and the denominator separated by a space");
		latexMap.put(
				"NthRoot",
				"Please enter the number and the root number separated by a space. Cube root of 5 would be 5 3");
		latexMap.put("Integral",
				"Please enter the limits separated by a space, and then the expression");
		latexMap.put(
				"Sum",
				"Please enter the variable, the base, the limit and the expression separated by a space.");
		// latexMap.put("limit", "");
	}

	@Override
	public void generateListener(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		Intent i = new Intent(getApplicationContext(), KeyboardEntry.class);
		s = ((TextView) arg1).getText().toString();
		System.out.println(s);
		i.putExtra("Extra", latexMap.get(s));
		startActivityForResult(i, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				String value = data.getStringExtra("Argument");
				String[] parts = value.split("\\s+");
				int size = parts.length;
				String result = "";
				if (s.equals("Powers")) {
					if (size != 2) {
						return;
					}
					result = parts[0] + "<sup align=right>" + parts[1]
							+ "</sup>";
				} else if (s.equals("Subscripts")) {
					if (size != 2) {
						return;
					}
					result = parts[0] + "<sub align=right>" + parts[1]
							+ "</sub>";
				} else if (s.equals("Fraction")) {
					if (size != 2) {
						return;
					}
					result = "<math>{" + parts[0] + "<over>" + parts[1] + "}</math>";
				} else if (s.equals("NthRoot")) {
					if (size != 2) {
						return;
					}
					result = "<root>" + parts[0] + "<of>" + parts[1]
							+ "</root>";
				} else if (s.equals("Integral")) {
					if (size != 3) {
						return;
					}
					result = "&int;_" + parts[0] + "_^" + parts[1] + "^{"
							+ parts[2] + "}";
				} else if (s.equals("Sum")) {
					if (size != 4) {
						return;
					}
					result = "<MATH>&sum;<sub>" + parts[0] + " = " + parts[1]
							+ "</sub><sup>" + parts[2] + "</sup>" + parts[3]+"</MATH>";
				}
				Intent i = new Intent();
				i.putExtra("Argument", result);
				setResult(RESULT_OK, i);
				finish();
			}
		}
	}
}
