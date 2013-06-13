package keyboard;

import java.util.HashMap;

import android.text.Html;




public class ExpressionKeyboardDisplay extends KeyboardDisplay{
	
	@Override
	public ExpressionTableClicker putListener() {
		//EditText e = (EditText) findViewById(R.id.q_content);
		ExpressionTableClicker etc = new ExpressionTableClicker();
		//HomePageActivity.string = e.getText().toString();
		return etc;
	}

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
}
