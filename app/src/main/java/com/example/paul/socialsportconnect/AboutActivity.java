package com.example.paul.socialsportconnect;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Paul on 28/02/2016.
 */
public class AboutActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        setContentView(R.layout.activity_home);
    }
}
