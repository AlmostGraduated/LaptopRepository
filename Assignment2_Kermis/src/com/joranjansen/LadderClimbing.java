package com.joranjansen;

public class LadderClimbing extends Attraction implements GamblingTaxable{

    public LadderClimbing() {
        this.name = "Ladder Climbing";
        this.ticketPrice = 5.00;
        this.surfaceArea = 100;
        this.RRA = false;
        this.spinningLimit = 0;
    }


    @Override
    public double financialImpact(double taxationFactor) {
        double tempTax = this.earnedSinceLastTaxAgentVisit * taxationFactor;
        totalRevenue -= tempTax;
        carnavalTurnover -= tempTax;
        this.earnedSinceLastTaxAgentVisit = 0;
        return tempTax;
    }

}
