package com.example.paul.socialsportconnect;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 30/03/2016.
 */
public class FormSignOn extends Activity {

    private EditText editTextPseudo;
    private EditText editTextPassword;
    private EditText editTextLastName;
    private EditText editTextFirstName;
    private EditText editTextBirthDate;
    private EditText editTextEMail;
    private EditText editTextMobile;
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_formsignon, null);


        /*
        try {
            JSONArray jsonArray = new JSONArray(readApi());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject firstObj = jsonArray.getJSONObject(i);

                String idLivre = firstObj.getString("idLivre");
                String titreLivre = firstObj.getString("titreLivre");
                String auteurLivre = firstObj.getString("auteurLivre");

                System.out.println("je suis la");
                System.out.println(idLivre);
                System.out.println(titreLivre);
                System.out.println(auteurLivre);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        */

        editTextPseudo = (EditText) layout.findViewById(R.id.editText);
        editTextPassword = (EditText) layout.findViewById(R.id.editText2);
        editTextLastName = (EditText) layout.findViewById(R.id.editText3);
        editTextFirstName = (EditText) layout.findViewById(R.id.editText4);
        editTextBirthDate = (EditText) layout.findViewById(R.id.editText5);
        editTextEMail = (EditText) layout.findViewById(R.id.editText6);
        editTextMobile = (EditText) layout.findViewById(R.id.editText7);

        TextView textView15 = (TextView) layout.findViewById(R.id.textView15);
        TextView textView16 = (TextView) layout.findViewById(R.id.textView16);
        TextView textView17 = (TextView) layout.findViewById(R.id.textView17);
        TextView textView18 = (TextView) layout.findViewById(R.id.textView18);
        TextView textView19 = (TextView) layout.findViewById(R.id.textView19);
        TextView textView20 = (TextView) layout.findViewById(R.id.textView20);
        TextView textView21 = (TextView) layout.findViewById(R.id.textView21);
        TextView textView22 = (TextView) layout.findViewById(R.id.textView22);

        EditText editText = (EditText) layout.findViewById(R.id.editText);
        EditText editText2 = (EditText) layout.findViewById(R.id.editText2);
        EditText editText3 = (EditText) layout.findViewById(R.id.editText3);
        EditText editText4 = (EditText) layout.findViewById(R.id.editText4);
        EditText editText5 = (EditText) layout.findViewById(R.id.editText5);
        EditText editText6 = (EditText) layout.findViewById(R.id.editText6);
        EditText editText7 = (EditText) layout.findViewById(R.id.editText7);

        Typeface faceFormSignOn = Typeface.createFromAsset(getAssets(), "fonts/AvenirLight.ttf");
        textView15.setTypeface(faceFormSignOn);
        textView16.setTypeface(faceFormSignOn);
        textView17.setTypeface(faceFormSignOn);
        textView18.setTypeface(faceFormSignOn);
        textView19.setTypeface(faceFormSignOn);
        textView20.setTypeface(faceFormSignOn);
        textView21.setTypeface(faceFormSignOn);
        textView22.setTypeface(faceFormSignOn);

        editText.setTypeface(faceFormSignOn);
        editText2.setTypeface(faceFormSignOn);
        editText3.setTypeface(faceFormSignOn);
        editText4.setTypeface(faceFormSignOn);
        editText5.setTypeface(faceFormSignOn);
        editText6.setTypeface(faceFormSignOn);
        editText7.setTypeface(faceFormSignOn);

        setContentView(layout);
    }

    public void insert(View view){
        String pseudo = editTextPseudo.getText().toString();
        String password = editTextPassword.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String firstName = editTextFirstName.getText().toString();
        String birthDate = editTextBirthDate.getText().toString();
        String eMail = editTextEMail.getText().toString();
        String mobile = editTextMobile.getText().toString();

        insertToDatabase(pseudo, password, lastName, firstName, birthDate, eMail, mobile);
    }
    private void insertToDatabase(String pseudo, String password, String lastName, String firstName, String birthDate, String eMail, String mobile) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramUsername = params[0];
                String paramAddress = params[1];

                String pseudo = editTextPseudo.getText().toString();
                String password = editTextPassword.getText().toString();
                String lastName = editTextLastName.getText().toString();
                String firstName = editTextFirstName.getText().toString();
                String birthDate = editTextBirthDate.getText().toString();
                String eMail = editTextEMail.getText().toString();
                String mobile = editTextMobile.getText().toString();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("pseudo", pseudo));
                nameValuePairs.add(new BasicNameValuePair("password", password));
                nameValuePairs.add(new BasicNameValuePair("lastName", lastName));
                nameValuePairs.add(new BasicNameValuePair("firstName", firstName));
                nameValuePairs.add(new BasicNameValuePair("birthDate", birthDate));
                nameValuePairs.add(new BasicNameValuePair("eMail", eMail));
                nameValuePairs.add(new BasicNameValuePair("mobile", mobile));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://socialsportconnect.fr/insert-db.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Inscription réussie";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

                changeFormSignOnToSignInActivity(layout.findViewById(android.R.id.content));
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(pseudo, password, lastName, firstName, birthDate, eMail, mobile);
    }

    public void changeFormSignOnToSignInActivity(View v) {
        Intent intent = new Intent(this, SignInActivity.class);

        startActivity(intent);

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}