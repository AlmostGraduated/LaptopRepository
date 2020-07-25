package com.joranjansen;

public class Attraction {
    protected static double carnavalTurnover;
    protected static int carnavalTicketsSold;

    protected double ticketPrice;
    protected boolean RRA;
    protected int spinningLimit;
    protected int totalTicketsSold;
    protected double totalRevenue;
    protected double surfaceArea;
    protected String name;
    protected int currentRunStreak;
    protected double earnedSinceLastTaxAgentVisit;



    protected void buyTickets(int nrTickets) {
        this.totalTicketsSold += nrTickets;
        this.totalRevenue += (this.ticketPrice * nrTickets);
        earnedSinceLastTaxAgentVisit += (this.ticketPrice * nrTickets);
        System.out.println("$$$$$$$$$$$$$$$$$$$$ " + (earnedSinceLastTaxAgentVisit += (this.ticketPrice * nrTickets)) + " $$$$$$$$$$$$$$$$$$$$");
        this.carnavalTurnover += (this.ticketPrice * nrTickets);
        this.carnavalTicketsSold += nrTickets;
        this.currentRunStreak += nrTickets;
        System.out.println("You have just bought " + nrTickets + " " + this.name + " ticket(s).\n");
        constructionInspection(nrTickets);

    }

    public void constructionInspection(int nrTickets) {
        if(this.RRA){
            if(this.currentRunStreak >= this.spinningLimit) {
                System.out.println("Inspection carried out");
                this.currentRunStreak = (this.currentRunStreak - this.spinningLimit);
                System.out.println("After inspection and outstanding rides the currentRunStreak is: " + this.currentRunStreak);
            }else{
                System.out.println("Inspection is not necessary yet: currentRunStreak = " + this.currentRunStreak);
            }
        }
    }
}