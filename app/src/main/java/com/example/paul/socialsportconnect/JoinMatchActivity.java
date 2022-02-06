package com.example.paul.socialsportconnect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Paul on 03/05/2016.
 */
public class JoinMatchActivity extends Activity {
    Context ctx=this;
    TextView city, frequency, stadium, description;
    String CITY=null, FREQUENCY=null, STADIUM=null, DESCRIPTION=null;
    String City, Frequency, Stadium, Description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout layout = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_joinmatch, null);

        Typeface faceJoinMatch = Typeface.createFromAsset(getAssets(), "fonts/AvenirLight.ttf");

        TextView title = (TextView) layout.findViewById(R.id.joinMatchTitle);

        city = (TextView) layout.findViewById(R.id.editText14);
        frequency = (TextView) layout.findViewById(R.id.editText15);
        stadium = (TextView) layout.findViewById(R.id.editText16);
        description = (TextView) layout.findViewById(R.id.editText17);

        TextView textCity = (TextView) layout.findViewById(R.id.joinMatchCity);
        TextView textFrequency = (TextView) layout.findViewById(R.id.joinMatchFrequency);
        TextView textStadium = (TextView) layout.findViewById(R.id.joinMatchStadium);
        TextView textDescription = (TextView) layout.findViewById(R.id.joinMatchDescription);

        textCity.setTypeface(faceJoinMatch);
        textFrequency.setTypeface(faceJoinMatch);
        textStadium.setTypeface(faceJoinMatch);
        textDescription.setTypeface(faceJoinMatch);

        String cityIntent = getIntent().getStringExtra("city");
        String frequencyIntent = getIntent().getStringExtra("frequency");
        String stadiumIntent = getIntent().getStringExtra("stadium");
        String descriptionIntent = getIntent().getStringExtra("description");

        title.setTypeface(faceJoinMatch);

        city.setText(cityIntent);
        city.setTypeface(faceJoinMatch);

        frequency.setText(frequencyIntent);
        frequency.setTypeface(faceJoinMatch);

        stadium.setText(stadiumIntent);
        stadium.setTypeface(faceJoinMatch);

        description.setText(descriptionIntent);
        description.setTypeface(faceJoinMatch);

        setContentView(layout);

    }

    public void main_join(View v){
        City = city.getText().toString();
        Frequency = frequency.getText().toString();
        Stadium = stadium.getText().toString();
        Description = description.getText().toString();
        BackGround b = new BackGround();
        b.execute(City, Frequency, Stadium, Description);
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String city = params[0];
            String frequency = params[1];
            String stadium = params[2];
            String description = params[3];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://socialsportconnect.fr/participant.php");
                String urlParams = "city="+city+"&frequency="+frequency+"&stadium="+stadium+"&description="+description;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }

                is.close();
                httpURLConnection.disconnect();

                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            String err=null;

            try {
                JSONObject root = new JSONObject(s);
                JSONObject nb_participant = root.getJSONObject("nb_participant");
                String test = nb_participant.getString("id");

                System.out.println("je suis test");
                System.out.println(test);


                //PASSWORD = user_data.getString("password");
                //EMAIL = user_data.getString("email");
            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: "+e.getMessage();
            }

            Intent i = new Intent(ctx, MatchActivity.class);
            startActivity(i);

        }
    }

    public String readApi() {
        InputStream in = null;
        String result = null;
        try {
            URL url = new URL("http://socialsportconnect.fr/matchlist.php");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());
            result = convert(in);
        } catch (Exception e) {
            Log.e("error", "readApi" + e);
        } finally {
            try {
                in.close();
            } catch (Exception e) {

            }
        }
        return result;

    }

    public String convert(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;

        try {
            while ( (line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (Exception e) {

        } finally {
            try {
                is.close();
            } catch (Exception e) {

            }
        }

        return sb.toString();
    }
}
