package com.example.paul.socialsportconnect;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    RelativeLayout layout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        layout = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_main, null);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/CenturyGothicRegular.ttf");
        TextView welcomeText = (TextView) layout.findViewById(R.id.textView);
        welcomeText.setTypeface(face);

        Typeface faceSlogan = Typeface.createFromAsset(getAssets(), "fonts/AvenirBlack.ttf");
        TextView sloganText = (TextView) layout.findViewById(R.id.textView3);
        sloganText.setTypeface(faceSlogan);

        Typeface faceAbout = Typeface.createFromAsset(getAssets(), "fonts/AvenirLight.ttf");
        TextView aboutText = (TextView) layout.findViewById(R.id.textView2);
        aboutText.setTypeface(faceAbout);

        layout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeLeft() {
                changeMainToAbout(layout);
            }
        });

        setContentView(layout);
    }

    public void changeMainToAbout(View v) {
        Intent intent = new Intent(this, AboutActivity.class);

        startActivity(intent);

        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
    }
}
