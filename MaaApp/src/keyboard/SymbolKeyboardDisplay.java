package keyboard;

import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

public class SymbolKeyboardDisplay extends KeyboardDisplay {

	public void fillInHashMap() {
		latexMap = new HashMap<String, String>();
		latexMap.put("\u03AC", "&alpha;");
		latexMap.put("\u03AD", "&beta;");
		latexMap.put("\u03B3", "&gamma;");
		latexMap.put("\u03B4", "&delta;");
		latexMap.put("\u03B5", "&epsilon;");
		latexMap.put("\u03B6", "&zeta;");
		latexMap.put("\u03B8", "&theta;");
		latexMap.put("\u03BB", "&lamba;");
		latexMap.put("\u03BC", "&mu;");
		latexMap.put("\u03C0", "&pi;");
		latexMap.put("\u03C4", "&tau;");
		latexMap.put("\u03C6", "&phi;");
		latexMap.put("\u03A6", "&Phi;");
		latexMap.put("\u03C8", "&psi;");
		latexMap.put("\u03C9", "&omega;");
		latexMap.put("\u03A9", "&Omega;");
		latexMap.put("\u220A", "&isin;");
		latexMap.put("\u220D", "&ni;");
		latexMap.put("\u2264", "&le;");
		latexMap.put("\u2265", "&ge;");
		latexMap.put("\u223C", "&sim;");
		latexMap.put("\u224C", "&cong;");
		latexMap.put("\u2261", "&equiv;");
		latexMap.put("\u2248", "&approv;");
		latexMap.put("\u2282", "&sub;");
		latexMap.put("\u2286", "&sube;");
		latexMap.put("\u2283", "&sup;");
		latexMap.put("\u2287", "&supe;");
		latexMap.put("\u22A5", "&perp;");
		latexMap.put("\u221D", "&propt;");
		latexMap.put("\u00B1", "&plusmn;");
		latexMap.put("\u2260", "&ne;");
		latexMap.put("\u2209", "&notin;");
		latexMap.put("\u2200", "&forall;");
		latexMap.put("\u221E", "&infin;");
		latexMap.put("sin", "sin");
		latexMap.put("cos", "cos");
		latexMap.put("tan", "tan");
		latexMap.put("log", "log");
		latexMap.put("sec", "sec");
		latexMap.put("cot", "cot");
		latexMap.put("lim", "lim");
	}

	@Override
	public void generateListener(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		Bundle bundle = new Bundle();
		bundle.putString("Argument",
				(latexMap.get(((TextView) arg1).getText().toString())));
		Intent myIntent = new Intent();
		myIntent.putExtras(bundle);
		setResult(RESULT_OK, myIntent);
		finish();
	}

}
