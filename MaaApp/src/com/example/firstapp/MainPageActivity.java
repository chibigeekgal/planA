package com.example.firstapp;

  

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

public class MainPageActivity extends Activity  implements OnGestureListener{
	
    private GestureDetector swipeDetector = new GestureDetector(this);
    
    
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage_view);
        swipeDetector = new GestureDetector(this);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent me) {
        return swipeDetector.onTouchEvent(me);
    }
    
    
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean onFling(MotionEvent start, MotionEvent finish, float xVelocity, float yVelocity) {
		
		if (start.getRawY() < finish.getRawY()) {
			startActivity(new Intent(getApplicationContext(),
					AnswerPageActivity.class));
		} else {
			startActivity(new Intent(getApplicationContext(),
					AskPageActivity.class));
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
