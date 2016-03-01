package com.example.paul.socialsportconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout layout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_main, null);


        setContentView(layout);
    }

    public void changeActivity(View v) {
        Intent intent =  new Intent(MainActivity.this, AboutActivity.class);

        startActivity(intent);
    }
}
