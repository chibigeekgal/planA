package keyboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;

import com.example.firstapp.R;
import com.example.homepage.HomePageActivity;

public abstract class KeyboardDisplay extends Activity {


	protected HashMap<String, String> latexMap;
	protected static Activity current_activity;
	protected EditText e;

	public KeyboardDisplay() {
		super();
		fillInHashMap();
	}

	public abstract void fillInHashMap();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_layout);
		List<String> preItems = new ArrayList<String>();

		Iterator<Entry<String, String>> it = getLatexMap().entrySet()
				.iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> pairs = (Entry<String, String>) it.next();
			preItems.add(pairs.getKey());
		}

		String[] items = new String[preItems.size()];
		preItems.toArray(items);

		GridView g = (GridView) findViewById(R.id.gridView1);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);
		g.setAdapter(aa);
		OnItemClickListener stc = putListener();
		g.setOnItemClickListener(stc);
	}

	@Override
	protected void onResume() {
		super.onResume();
		current_activity = this;
	}

	public abstract OnItemClickListener putListener();

	public HashMap<String, String> getLatexMap() {
		return latexMap;
	}

	public static Activity getCurrentActivity() {
		return current_activity;
	}
	
	public void addText(String s){
		e.setText(e.getText().toString()+ s);
	}
	
	public EditText getEditable(){
		return this.e;
	}
	
}
