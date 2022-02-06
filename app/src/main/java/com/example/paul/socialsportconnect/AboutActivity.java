package com.example.paul.socialsportconnect;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Paul on 28/02/2016.
 */
public class AboutActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout layout = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_about, null);

        Typeface faceDesc = Typeface.createFromAsset(getAssets(), "fonts/AvenirBlack.ttf");
        TextView descText1 = (TextView) layout.findViewById(R.id.textView4);
        TextView descText2 = (TextView) layout.findViewById(R.id.textView5);
        TextView descText3 = (TextView) layout.findViewById(R.id.textView6);
        TextView descText4 = (TextView) layout.findViewById(R.id.textView7);
        TextView descText5 = (TextView) layout.findViewById(R.id.textView8);

        descText1.setTypeface(faceDesc);
        descText2.setTypeface(faceDesc);
        descText3.setTypeface(faceDesc);
        descText4.setTypeface(faceDesc);
        descText5.setTypeface(faceDesc);

        setContentView(layout);
    }

    public void onCreateDialog(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.dialog_signin, null);

        Typeface faceSignDialog = Typeface.createFromAsset(getAssets(), "fonts/AvenirLight.ttf");

        TextView signOn = (TextView) promptView.findViewById(R.id.signon);

        TextView signIn = (TextView) promptView.findViewById(R.id.signin);

        signOn.setTypeface(faceSignDialog);
        signIn.setTypeface(faceSignDialog);

        signOn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeAboutToSignOn(v);
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeAboutToSignIn(v);
            }
        });

        builder.setView(promptView);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        //builder.setView(inflater.inflate(R.layout.dialog_signin, null));

        Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    // permet de faire un retour propre quand on appuie sur la touche retour de la barre d'action du telephone
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void changeAboutToSignOn(View v) {
        Intent intent = new Intent(this, SignOnActivity.class);

        startActivity(intent);

        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
    }

    public void changeAboutToSignIn(View v) {
        Intent intent = new Intent(this, SignInActivity.class);

        startActivity(intent);

        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
    }
}
