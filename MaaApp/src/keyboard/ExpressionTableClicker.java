package keyboard;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class ExpressionTableClicker implements OnItemClickListener {

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		final String s = ((TextView) arg1).getText().toString();

		Intent i = new Intent(KeyboardDisplay.current_activity,
				KeyboardEntry.class);
		i.putExtra("Argument", s);
		KeyboardDisplay.current_activity.startActivity(i);
	}

}
