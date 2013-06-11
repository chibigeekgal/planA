package keyboard;

import com.example.homepage.HomePageActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SymbolTableClicker implements OnItemClickListener{

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		HomePageActivity.string += ((TextView)arg1).getText();
		System.out.println("SymbolTable" + HomePageActivity.string);
	}


}
