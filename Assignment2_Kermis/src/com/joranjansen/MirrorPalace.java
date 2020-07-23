package com.joranjansen;

public class MirrorPalace extends Attraction {
    private final double ticketPrice;
    private boolean RRA;
    private int spinningLimit;

    public MirrorPalace() {
        this.ticketPrice = 2.75;
        this.surfaceArea = 100;
        this.RRA = false;
    }


    @Override
    public void constructionInspection() {

    }

}
