package com.example.firstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MainPageActivity extends Activity {
	
	private GestureDetector swipDetector = new GestureDetector(new swipListener());
	
	@Override
	protected void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.mainpage_view);
		
		
		
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	   boolean result = swipDetector.onTouchEvent(event);
	   if (!result) {
		     
	   }
	   return result;
	}
	
	
	
	//used to build swipDetector
	private class swipListener extends GestureDetector.SimpleOnGestureListener {
		   @Override
		   public boolean onDown(MotionEvent e) {
		       return true;
		   }
		}


}
