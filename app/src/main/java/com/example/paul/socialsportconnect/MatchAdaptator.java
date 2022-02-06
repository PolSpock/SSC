package com.example.paul.socialsportconnect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paul.socialsportconnect.Match;

import java.util.List;

/**
 * Created by Paul on 15/03/2016.
 */
public class MatchAdaptator extends ArrayAdapter<Match> {

    // tweet = list of models to display
    public MatchAdaptator(Context context, List<Match> matchs) {
        super(context, 0, matchs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_rowtextview,parent, false);
        }

        // get xml object
        TweetViewHolder viewHolder = (TweetViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new TweetViewHolder();
            viewHolder.city = (TextView) convertView.findViewById(R.id.city);
            viewHolder.stadium = (TextView) convertView.findViewById(R.id.stadium);
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);
            viewHolder.frequency = (TextView) convertView.findViewById(R.id.frequency);
            convertView.setTag(viewHolder);
        }

        Match match = getItem(position);

        // set view
        viewHolder.city.setText(match.getCityCreatematch());
        viewHolder.stadium.setText(match.getStadiumCreatematch());
        viewHolder.description.setText(match.getDescriptionCreatematch());
        viewHolder.frequency.setText(match.getFrequencyCreatematch());

        return convertView;
    }

    private class TweetViewHolder{
        public TextView city;
        public TextView description;
        public TextView frequency;
        public TextView stadium;
    }
}