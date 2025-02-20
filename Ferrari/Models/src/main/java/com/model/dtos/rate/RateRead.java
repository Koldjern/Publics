package com.model.dtos.rate;

import com.rki.rki.Rating;

public class RateRead {
    private double todaysRate;
    private Rating rki;

    public RateRead(double todaysRate, Rating rki) {
        this.todaysRate = todaysRate;
        this.rki = rki;
    }

    public double getTodaysRate() {
        return todaysRate;
    }

    public void setTodaysRate(double todaysRate) {
        this.todaysRate = todaysRate;
    }

    public Rating getRki() {
        return rki;
    }

    public void setRki(Rating rki) {
        this.rki = rki;
    }
}
