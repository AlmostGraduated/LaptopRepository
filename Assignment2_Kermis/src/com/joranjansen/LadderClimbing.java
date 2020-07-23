package com.joranjansen;

public class LadderClimbing extends Attraction implements GamblingTaxable {

    public LadderClimbing() {
        this.ticketPrice = 5.00;
        this.surfaceArea = 100;
        this.RRA = false;
    }


    @Override
    public void constructionInspection() {

    }

    @Override
    public double gamblingTax(double factor) {
        return 0;
    }
}
