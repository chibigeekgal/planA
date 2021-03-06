package main;

import com.example.firstapp.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class StartUp extends Activity {

    private final int SPLASH_DISPLAY_LENGHT = 1500;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.start_up);

        ImageView i = (ImageView) findViewById(R.id.splashscreen);
        i.setBackgroundColor(Color.BLACK);
        /* New Handler to start the Menu-Activity 
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-AcainACtivitytivity. */
                Intent mainIntent = new Intent(StartUp.this,MainActivity.class);
                StartUp.this.startActivity(mainIntent);
                StartUp.this.finish();
                
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}


























