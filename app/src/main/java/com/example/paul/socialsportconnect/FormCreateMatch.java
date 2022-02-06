package com.example.paul.socialsportconnect;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 20/04/2016.
 */
public class FormCreateMatch extends Activity {

    private EditText editTextCity;
    private EditText editTextFrequency;
    private EditText editTextStadium;
    private EditText editTextDescription;
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_formcreatematch, null);


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

        TextView form_create_title = (TextView) layout.findViewById(R.id.form_create_title);
        TextView form_create_city = (TextView) layout.findViewById(R.id.form_create_city);
        TextView form_create_frequence = (TextView) layout.findViewById(R.id.form_create_frequence);
        TextView form_create_stade = (TextView) layout.findViewById(R.id.form_create_stade);
        TextView form_create_description = (TextView) layout.findViewById(R.id.form_create_description);
        Button form_create_button = (Button) layout.findViewById(R.id.form_create_button);

        EditText editText10 = (EditText) layout.findViewById(R.id.editText10);
        EditText editText11 = (EditText) layout.findViewById(R.id.editText11);
        EditText editText12 = (EditText) layout.findViewById(R.id.editText12);
        EditText editText13 = (EditText) layout.findViewById(R.id.editText13);

        Typeface faceFormCreateMatch = Typeface.createFromAsset(getAssets(), "fonts/AvenirLight.ttf");

        form_create_title.setTypeface(faceFormCreateMatch);
        form_create_city.setTypeface(faceFormCreateMatch);
        form_create_frequence.setTypeface(faceFormCreateMatch);
        form_create_stade.setTypeface(faceFormCreateMatch);
        form_create_description.setTypeface(faceFormCreateMatch);
        form_create_button.setTypeface(faceFormCreateMatch);

        editText10.setTypeface(faceFormCreateMatch);
        editText11.setTypeface(faceFormCreateMatch);
        editText12.setTypeface(faceFormCreateMatch);
        editText13.setTypeface(faceFormCreateMatch);

        editTextCity = (EditText) layout.findViewById(R.id.editText10);
        editTextFrequency = (EditText) layout.findViewById(R.id.editText11);
        editTextStadium = (EditText) layout.findViewById(R.id.editText12);
        editTextDescription = (EditText) layout.findViewById(R.id.editText13);

        setContentView(layout);
    }

    public void insert(View view){
        String city = editTextCity.getText().toString();
        String frequency = editTextFrequency.getText().toString();
        String stadium = editTextStadium.getText().toString();
        String description = editTextDescription.getText().toString();

        insertToDatabase(city, frequency, stadium, description);
    }

    private void insertToDatabase(String city, String frequency, String stadium, String description) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramUsername = params[0];
                String paramAddress = params[1];

                String city = editTextCity.getText().toString();
                String frequency = editTextFrequency.getText().toString();
                String stadium = editTextStadium.getText().toString();
                String description = editTextDescription.getText().toString();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("city", city));
                nameValuePairs.add(new BasicNameValuePair("frequency", frequency));
                nameValuePairs.add(new BasicNameValuePair("stadium", stadium));
                nameValuePairs.add(new BasicNameValuePair("description", description));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://socialsportconnect.fr/insert-db-match.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Match crée";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                changeFormCreateMatchToMatch(layout.findViewById(android.R.id.content));

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(city, frequency, stadium, description);
    }

    public void changeFormCreateMatchToMatch(View v) {
        Intent intent = new Intent(this, MatchActivity.class);

        startActivity(intent);

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
