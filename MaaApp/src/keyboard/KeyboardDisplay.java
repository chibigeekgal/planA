package keyboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.firstapp.R;

public abstract class KeyboardDisplay extends Activity {


	protected HashMap<String, String> latexMap;

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
			System.out.println(pairs.getKey());
		}

		String[] items = new String[preItems.size()];
		preItems.toArray(items);

		GridView g = (GridView) findViewById(R.id.gridView1);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);
		g.setAdapter(aa);
		g.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				generateListener(arg0, arg1, arg2, arg3);
			}
			
		});
	}

	public abstract void generateListener(AdapterView<?> arg0, View arg1, int arg2, long arg3);

	public HashMap<String, String> getLatexMap() {
		return latexMap;
	}
}
