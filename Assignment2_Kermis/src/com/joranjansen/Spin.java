package com.joranjansen;

public class Spin extends Attraction implements GamblingTaxable{

    public Spin() {
        this.ticketPrice = 2.25;
        this.surfaceArea = 100;
        this.RRA = true;
        this.spinningLimit = 5;
    }


    @Override
    public void constructionInspection() {

    }

    @Override
    public double gamblingTax(double factor) {
        return 0;
    }
}
