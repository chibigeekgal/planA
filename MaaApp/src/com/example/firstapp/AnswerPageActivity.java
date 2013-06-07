package com.example.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class AnswerPageActivity extends Activity implements OnGestureListener{
	
	private GestureDetector swipeDetector = new GestureDetector(this);
	
	
	@Override
	protected void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.answerpage_view);
		swipeDetector = new GestureDetector(this);
	}

    @Override
    public boolean onTouchEvent(MotionEvent event) {
	    return swipeDetector.onTouchEvent(event);
    }
	
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent start, MotionEvent finish, float xVelocity, float yVelocity) {
		if (start.getRawY() > finish.getRawY()) {//swipe to right
			startActivity(new Intent(getApplicationContext(),
					MainPageActivity.class));
		}
		return true;
		 
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

}
