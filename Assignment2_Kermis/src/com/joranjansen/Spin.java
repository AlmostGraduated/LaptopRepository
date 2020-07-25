package com.joranjansen;

public class Spin extends Attraction implements GamblingTaxable{

    public Spin() {
        this.name = "Spin";
        this.ticketPrice = 2.25;
        this.surfaceArea = 100;
        this.RRA = true;
        this.spinningLimit = 5;
    }
    @Override
    protected void buyTickets(int nrTickets) {
        if(nrTickets <= this.spinningLimit){
            totalTicketsSold += nrTickets;
            totalRevenue += (this.ticketPrice * nrTickets);
            earnedSinceLastTaxAgentVisit += (this.ticketPrice * nrTickets);
            this.carnavalTurnover += (this.ticketPrice * nrTickets);
            this.carnavalTicketsSold += nrTickets;
            this.currentRunStreak += nrTickets;
            System.out.println("You have just bought " + nrTickets + " " + this.name + " ticket(s).\n");
            constructionInspection(nrTickets);
        }else{
            System.out.println("Sorry you can only purchase " + this.spinningLimit + " tickets at the most");
        }
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
