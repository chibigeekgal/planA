package keyboard;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.example.firstapp.R;
import com.example.homepage.HomePageActivity;

public class SymbolTableClicker implements OnItemClickListener{
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Activity currentA = HomePageActivity.currentActivity;
		EditText e = null;
		if(currentA instanceof HomePageActivity){
			e = (EditText) currentA.findViewById(R.id.q_content);
		} else if(currentA instanceof KeyboardEntry) {
			e = (EditText) currentA.findViewById(R.id.content);
		}
		String s = ((TextView)arg1).getText().toString();
		HomePageActivity.string += " " + s;
	}


}
