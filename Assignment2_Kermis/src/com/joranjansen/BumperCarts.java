package com.joranjansen;

public class BumperCarts extends Attraction {

    public BumperCarts() {
        this.ticketPrice = 2.50;
        this.surfaceArea = 100;
        this.RRA = false;
        this.spinningLimit = 0;
    }


    @Override
    public void constructionInspection() {
        if(this.RRA){


        }
    }



}
