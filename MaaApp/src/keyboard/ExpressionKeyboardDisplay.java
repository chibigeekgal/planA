package keyboard;

import java.util.HashMap;

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
		latexMap.put("Sum",
				"Please enter the base, the limit and the expression separated by a space.");
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
				String result = "";
				if (s.equals("Powers")) {
					result = parts[0] + "_{" + parts[1] + "}";
				} else if (s.equals("Subscripts")) {
					result = parts[0] + "^{" + parts[1] + "}";
				} else if (s.equals("Fraction")) {
					result = "\\" + "\\" + "frac{" + parts[0] + "}{" + parts[1]
							+ "}";
				} else if (s.equals("NthRoot")) {
					result = "\\\\\\sqrt[" + parts[0] + "]" + "{" + parts[1]
							+ "}";
				} else if (s.equals("Integral")) {
					result = "\\\\\\int_{" + parts[0] + "}^{" + parts[1] + "}"
							+ parts[2];
				} else if (s.equals("Sum")) {
					result = "\\\\\\sum_{" + parts[0] + "}^{" + parts[1] + "}"
							+ parts[2];
				}
				Intent i = new Intent();
				System.out.println("result: " + result);
				i.putExtra("Argument", result);
				setResult(RESULT_OK, i);
				finish();
			}
		}
	}
}
