package com.example.homepage;

import com.example.firstapp.R;

import android.app.Activity;
import android.os.Bundle;

public class IndividualQuestion extends Activity {
	
	private String title;
	private String content;
	
	public IndividualQuestion()
	{
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.individual_question);
	}

}
