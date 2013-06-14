package com.example.firstapp;

import com.example.firstapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class StartUp extends Activity {

    private final int SPLASH_DISPLAY_LENGHT = 2000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.start_up);

        /* New Handler to start the Menu-Activity 
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-AcainACtivitytivity. */
                Intent mainIntent = new Intent(StartUp.this,MainActivity.class);
                StartUp.this.startActivity(mainIntent);
                StartUp.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}


























