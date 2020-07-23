package com.joranjansen;

import javax.sound.midi.SysexMessage;
import java.util.Scanner;

public class Registry {
    protected Attraction bumperCarts;
    protected Attraction spin;
    protected Attraction mirrorPalace;
    protected Attraction ghostHouse;
    protected Attraction hawaii;
    protected Attraction ladderClimbing;
    protected GamblingTaxInspector gamblingTaxInspector;
    protected boolean alpha = true;


    public Registry() {
        this.bumperCarts = new BumperCarts();
        this.spin = new Spin();
        this.mirrorPalace = new MirrorPalace();
        this.ghostHouse = new GhostHouse();
        this.hawaii = new Hawaii();
        this.ladderClimbing = new LadderClimbing();
        this.gamblingTaxInspector = new GamblingTaxInspector();
    }

    protected void startSystem() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welkom to the Carnaval Registry.");
        while (alpha) {
            gamblingTaxInspector.calculateTax((GamblingTaxable) ladderClimbing);
            printOptions();
            String userInput = scanner.next();
            executeCommand(userInput);
        }
    }

    protected void printOptions() {
        System.out.println("Please choose one of the options:");
        System.out.println("\t" + "1. Buy ticket(s)");
        System.out.println("\t" + "2. Buy - Spin ticket(s)");
        System.out.println("\t" + "3. Buy - MirrorPalace ticket(s)");
        System.out.println("\t" + "4. Buy - GhostHouse ticket(s)");
        System.out.println("\t" + "5. Buy - Hawaii ticket(s)");
        System.out.println("\t" + "6. Buy - LadderClimbing ticket(s)");
        System.out.println("\t" + "o. Show turnover carnaval");
        System.out.println("\t" + "k. Show ticket sale carnaval");
        System.out.println("\t" + "q. Shut-down system");
    }

    protected void executeCommand(String userInput){
        Scanner scanner2 = new Scanner(System.in);
        switch(userInput){
            case "1":

                System.out.println("You would like to buy (a) ticket(s) for?:");
                buySomeTicket(scanner2.nextInt());
                break;
            case "2":
                System.out.println("How many tickets would you like to buy?:");
                spin.buyTickets(scanner2.nextInt());
                break;
            case "3":
                System.out.println("How many tickets would you like to buy?:");
                mirrorPalace.buyTickets(scanner2.nextInt());
                break;
            case "4":
                System.out.println("How many tickets would you like to buy?:");
                ghostHouse.buyTickets(scanner2.nextInt());
                break;
            case "5":
                System.out.println("How many tickets would you like to buy?:");
                hawaii.buyTickets(scanner2.nextInt());
                break;
            case "6":
                System.out.println("How many tickets would you like to buy?:");
                ladderClimbing.buyTickets(scanner2.nextInt());
                break;
            case "o":
                System.out.println("Carnaval turnover: $" + Attraction.carnavalTurnover);
                break;
            case "k":
                System.out.println("Caraval ticket sale: " + Attraction.carnavalTicketsSold);
                break;
            case "q":
                alpha = false;
                System.out.println("Shutting down, bye bye.... ");
                break;
            default :
                System.out.println("This is not a valid option");
        }
    }

    protected void buySomeTicket(int userInput){
        Scanner scanner3 = new Scanner(System.in);
        System.out.println();
        switch(userInput){
            case 1:
                System.out.println("\t" + "1. Buy - BumperCarts ticket(s)");
        }
    }

//    System.out.println("\t" + "1. Buy ticket(s)");
//        System.out.println("\t" + "2. Buy - Spin ticket(s)");
//        System.out.println("\t" + "3. Buy - MirrorPalace ticket(s)");
//        System.out.println("\t" + "4. Buy - GhostHouse ticket(s)");
//        System.out.println("\t" + "5. Buy - Hawaii ticket(s)");
//        System.out.println("\t" + "6. Buy - LadderClimbing ticket(s)");

}

