package com.joranjansen;

public class Hawaii extends Attraction {

    public Hawaii() {
        this.name = "Hawaii";
        this.ticketPrice = 2.90;
        this.surfaceArea = 100;
        this.RRA = true;
        this.spinningLimit = 10;
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

}
