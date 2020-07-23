package com.joranjansen;

abstract public class Attraction {
    protected static double carnavalTurnover;
    protected static int carnavalTicketsSold;
    protected double ticketPrice;
    protected boolean RRA;
    protected int spinningLimit;
    protected int totalTicketsSold;
    protected double totalRevenue;
    protected double surfaceArea;

    protected void buyTickets(int nrTickets) {
        totalTicketsSold += nrTickets;
        totalRevenue += (this.ticketPrice * nrTickets);
        this.carnavalTurnover += (this.ticketPrice * nrTickets);
        this.carnavalTicketsSold += nrTickets;
        System.out.println("You have just bought " + nrTickets + " " + this.getClass().getSimpleName() + " ticket(s).\n");
    }

    abstract public void constructionInspection();
}