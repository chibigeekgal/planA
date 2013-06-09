package com.example.firstapp;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class KeyboardDisplay extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_layout);

		String[] items = { "\u03B1", "is", "a", "really", "really2", "really3",
				"really4", "really5", "silly", "list" };
		GridView g = (GridView) findViewById(R.id.gridView1);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);
		g.setAdapter(aa);
		g.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Log.d("View!", ((TextView)arg1).getText().toString());
				((TextView)arg1).setText(Html.fromHtml("X<sup>2</sup>"));
				/*String latex = "x=\\frac{-b \\pm \\sqrt {b^2-4ac}}{2a}";
				
				TeXFormula formula = new TeXFormula(latex);
				TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
				BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(),BufferedImage.TYPE_INT_ARGB);
				
				File outputfile = new File("image.jpg");
				try {
					ImageIO.write(bufferedImage, "jpg", outputfile);
				} catch (IOException e) {
					e.printStackTrace();
				}*/
			}
			
		});
		
	}

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		Rect p = MainActivity.p;
		WindowManager.LayoutParams lp =  getWindow().getAttributes();
		lp.gravity = Gravity.LEFT | Gravity.TOP;
		lp.width = 500;
		lp.x = p.right-400;
		lp.y = p.bottom-30;
		Log.d("x",String.valueOf(p.right));
		Log.d("y", String.valueOf(p.bottom));
		getWindow().setAttributes(lp);
	}
}
