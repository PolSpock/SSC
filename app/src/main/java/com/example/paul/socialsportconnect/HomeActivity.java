package com.example.paul.socialsportconnect;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeActivity extends Activity {

    String name, password, email, Err;
    TextView nameTV, emailTV, passwordTV, err;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout layout = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_home, null);

        TextView textViewHomeTitle = (TextView) layout.findViewById(R.id.textViewHomeTitle);
        TextView textView23 = (TextView) layout.findViewById(R.id.textView23);
        TextView textView27 = (TextView) layout.findViewById(R.id.textView27);
        TextView textView28 = (TextView) layout.findViewById(R.id.textView28);

        Typeface faceHome = Typeface.createFromAsset(getAssets(), "fonts/AvenirLight.ttf");
        Typeface faceName = Typeface.createFromAsset(getAssets(), "fonts/AvenirBlack.ttf");

        textViewHomeTitle.setTypeface(faceHome);
        textView23.setTypeface(faceHome);
        textView27.setTypeface(faceHome);
        textView28.setTypeface(faceHome);

        nameTV = (TextView) layout.findViewById(R.id.home_name);
        //emailTV = (TextView) findViewById(R.id.home_email);
        //passwordTV = (TextView) findViewById(R.id.home_password);
        //err = (TextView) findViewById(R.id.err);

        name = getIntent().getStringExtra("name");
        //password = getIntent().getStringExtra("password");
        //email = getIntent().getStringExtra("email");
        //Err = getIntent().getStringExtra("err");

        nameTV.setText(name);
        nameTV.setTypeface(faceName);

        //passwordTV.setText("Your password is "+password);
        //emailTV.setText("Your email is "+email);
        //err.setText(Err);

        setContentView(layout);

    }

    public void changeHomeToMatch(View v) {
        Intent intent = new Intent(this, MatchActivity.class);

        startActivity(intent);

        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
    }
}