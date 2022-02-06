package com.example.paul.socialsportconnect;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Paul on 20/04/2016.
 */
public class MatchActivity extends Activity {
    RelativeLayout layout = null;
    private TextView text = null;
    String[] prenoms = new String[] {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_match, null);

        /*
        text = (TextView) layout.findViewById(R.id.textView25);
        text.setText(readApi());
        text.setPadding(100, 100, 20, 150);
        text.setTextColor(Color.parseColor("#000000"));
        */

        Typeface faceCreateMatch = Typeface.createFromAsset(getAssets(), "fonts/AvenirLight.ttf");
        TextView createMatch = (TextView) layout.findViewById(R.id.textView24);
        createMatch.setTypeface(faceCreateMatch);

        Typeface faceSearchMatch = Typeface.createFromAsset(getAssets(), "fonts/AvenirLight.ttf");
        TextView searchMatch = (TextView) layout.findViewById(R.id.textView26);
        TextView titleMatch = (TextView) layout.findViewById(R.id.titleMatch);
        searchMatch.setTypeface(faceSearchMatch);
        titleMatch.setTypeface(faceCreateMatch);

        ArrayList<Match> matchs = new ArrayList<Match>();

        try {
            JSONArray jsonArray = new JSONArray(readApi());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject firstObj = jsonArray.getJSONObject(i);

                String cityCreatematch = firstObj.getString("city_creatematch");
                String frequencyCreatematch = firstObj.getString("frequency_creatematch");
                String stadiumCreatematch = firstObj.getString("stadium_creatematch");
                String descriptionCreatematch = firstObj.getString("description_creatematch");

                System.out.println("je suis la");
                System.out.println(cityCreatematch);
                System.out.println(frequencyCreatematch);
                System.out.println(stadiumCreatematch);
                System.out.println(descriptionCreatematch);

                matchs.add(new Match(cityCreatematch, frequencyCreatematch, stadiumCreatematch, descriptionCreatematch));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final ListView mainListView = (ListView) layout.findViewById(R.id.listView);
        MatchAdaptator adapter = new MatchAdaptator(MatchActivity.this, matchs);

        mainListView.setAdapter(adapter);
        mainListView.setClickable(true);
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                final View v = arg1 ; // Save selected view in final variable**

                TextView city = (TextView) v.findViewById(R.id.city);
                String cityText = (String) city.getText();

                TextView stadium = (TextView) v.findViewById(R.id.stadium);
                String stadiumText = (String) stadium.getText();

                TextView frequency = (TextView) v.findViewById(R.id.frequency);
                String frequencyText = (String) frequency.getText();

                TextView description = (TextView) v.findViewById(R.id.description);
                String descriptionText = (String) description.getText();


                Intent intent = new Intent(MatchActivity.this, JoinMatchActivity.class);
                intent.putExtra("city", cityText);
                intent.putExtra("stadium", stadiumText);
                intent.putExtra("frequency", frequencyText);
                intent.putExtra("description", descriptionText);
                startActivity(intent);

                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
            }
        });

        setContentView(layout);
    }

    public void changeMatchToFormCreateMatch(View v) {
        Intent intent = new Intent(this, FormCreateMatch.class);

        startActivity(intent);

        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
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
