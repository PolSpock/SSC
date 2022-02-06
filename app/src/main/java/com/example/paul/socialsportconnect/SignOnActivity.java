package com.example.paul.socialsportconnect;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Paul on 23/03/2016.
 */
public class SignOnActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        RelativeLayout layout = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_signon, null);

        TextView textView9 = (TextView) layout.findViewById(R.id.textView9);
        TextView textView10 = (TextView) layout.findViewById(R.id.textView10);
        TextView textView12 = (TextView) layout.findViewById(R.id.textView12);
        TextView textView14 = (TextView) layout.findViewById(R.id.textView14);


        Typeface faceSignOn = Typeface.createFromAsset(getAssets(), "fonts/AvenirLight.ttf");
        textView9.setTypeface(faceSignOn);
        textView10.setTypeface(faceSignOn);
        textView12.setTypeface(faceSignOn);
        textView14.setTypeface(faceSignOn);

        TextView textView11 = (TextView) layout.findViewById(R.id.textView11);
        TextView textView13 = (TextView) layout.findViewById(R.id.textView13);
        Typeface faceSignOnDetails = Typeface.createFromAsset(getAssets(), "fonts/AvenirBlack.ttf");
        textView11.setTypeface(faceSignOnDetails);
        textView13.setTypeface(faceSignOnDetails);

        setContentView(layout);
    }

    public void changeSignOnToFormSignOn(View v) {
        Intent intent = new Intent(this, FormSignOn.class);

        startActivity(intent);

        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
    }

    public void changeSignOnToSignIn(View v) {
        Intent intent = new Intent(this, SignInActivity.class);

        startActivity(intent);

        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
    }
}
