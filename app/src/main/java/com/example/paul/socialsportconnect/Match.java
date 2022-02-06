package com.example.paul.socialsportconnect;

/**
 * Created by Paul on 03/05/2016.
 */
public class Match {
    private String descriptionCreatematch;
    private String stadiumCreatematch;
    private String frequencyCreatematch;
    private String cityCreatematch;

    public Match(String cityCreatematch, String frequencyCreatematch, String stadiumCreatematch, String descriptionCreatematch) {
        this.cityCreatematch = cityCreatematch;
        this.frequencyCreatematch = frequencyCreatematch;
        this.stadiumCreatematch = stadiumCreatematch;
        this.descriptionCreatematch = descriptionCreatematch;
    }

    public String getCityCreatematch() {
        return cityCreatematch;
    }

    public String getFrequencyCreatematch() {
        return frequencyCreatematch;
    }

    public String getStadiumCreatematch() {
        return stadiumCreatematch;
    }

    public String getDescriptionCreatematch() {
        return descriptionCreatematch;
    }

}

