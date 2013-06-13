package keyboard;

import java.util.HashMap;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;


public class ExpressionKeyboardDisplay extends KeyboardDisplay{

	String s;

	@Override
	public void fillInHashMap() {
		latexMap = new HashMap<String, String>();
		latexMap.put(Html.fromHtml("X<sup>2<sup>").toString(), "");
		latexMap.put(Html.fromHtml("X<sub>2<sub>").toString(), "");
		latexMap.put("Fraction", "");
		latexMap.put("NthRoot", "");
		latexMap.put("Integral", "");
		latexMap.put("sum", "");
		//latexMap.put("limit", "");		
	}

	@Override
	public void generateListener(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		Intent i = new Intent(getApplicationContext(),
				KeyboardEntry.class);
		s = ((TextView) arg1).getText().toString();
		System.out.println(s);
		startActivityForResult(i,1);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	//	super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
		     if(resultCode == RESULT_OK){      
		         String value = data.getStringExtra("Argument");
					String[] parts = value.split("(?<!\\\\) ");
					String result = "";
					System.out.println("what's in the editable" + value);
					System.out.println("part 0" + parts[0] + "part1 " + parts[1] + " total value" + value);
					if (s.equals("X<sup>2<sup>")) {
						result = parts[0] + "_{" + parts[1] + "}";
					} else if (s.equals("X<sub>2<sub>")) {
						result = parts[0] + "^{" + parts[1] + "}";
					} else if (s.equals("Fraction")) {
						result = "\\" + "\\" + "frac{" + parts[0] + "}{" + parts[1] + "}";
					} else if (s.equals("NthRoot")) {
						result = "\\\\\\sqrt[" + parts[0] + "]" + "{" + parts[1] + "}";
					} else if (s.equals("Integral")) {
						result = "\\\\\\int_{" + parts[0] + "}^{" + parts[1] + "}"
								+ parts[2];
					} else if (s.equals("sum")) {
						result = "\\\\\\sum_{" + parts[0] + "}^{" + parts[1] + "}"
								+ parts[2];
					}
		         Intent i = new Intent();
		         i.putExtra("Argument", result);
		         setResult(RESULT_OK, i);
		         finish();
		     }
		  }
	}
}
