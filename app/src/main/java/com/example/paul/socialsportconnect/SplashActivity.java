package com.example.paul.socialsportconnect;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

/**
 * Created by Paul on 23/03/2016.
 */
public class SplashActivity extends Activity {

    // Splash screen timer
    private int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
