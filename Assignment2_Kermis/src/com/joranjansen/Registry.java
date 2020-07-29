package com.joranjansen;

import javax.sound.midi.SysexMessage;
import java.util.Scanner;

public class Registry {
    private Attraction bumperCarts;
    private Attraction spin;
    private Attraction mirrorPalace;
    private Attraction ghostHouse;
    private Attraction hawaii;
    private Attraction ladderClimbing;
    private TaxInspection taxInspection;
    private boolean alpha = true;


    protected Registry() {
        this.bumperCarts = new BumperCarts();
        this.spin = new Spin();
        this.mirrorPalace = new MirrorPalace();
        this.ghostHouse = new GhostHouse();
        this.hawaii = new Hawaii();
        this.ladderClimbing = new LadderClimbing();
        this.taxInspection = new TaxInspection();
    }

    protected void startSystem() {
        System.out.println("==============================================================");
        System.out.println("WELCOME TO THE CARNAVAL REGISTRY.");
        System.out.println("==============================================================\n");
        while (alpha) {
            printMainMenu();
            exeMainUserInput();
        }
    }

    private void printMainMenu() {
        System.out.println("==============================================================");
        System.out.println("==============================================================");
        System.out.println("Please choose one of the options:");
        System.out.println("==============================================================");
        System.out.println("\t" + "1. Purchase ticket(s)");
        System.out.println("\t" + "2. Show carnaval tickets sale");
        System.out.println("\t" + "3. Show carnaval financials");
        System.out.println("\t" + "4. IRS is at the door");
        System.out.println("\t" + "q. System shut-down");
        System.out.println("==============================================================");
        System.out.println("==============================================================\n");
    }

    private void exeMainUserInput(){
        Scanner scanner = new Scanner(System.in);
        switch(scanner.next()){
            case "1":
                printSubMenuBuyTickets();
                exeSubMenuBuyTickets();
                break;
            case "2":
                showTicketOverview();
                break;
            case "3":
                showFinancialOverview();
                break;
            case "4":
                calculateWhatToPay(spin, ladderClimbing);
                break;
            case "q":
                shutDownSystem();
                break;
            default:
                System.out.println("This is not a valid option.");
        }
    }

    private void printSubMenuBuyTickets() {
        System.out.println("==============================================================");
        System.out.println("==============================================================");
        System.out.println("You wish to buy tickets for:");
        System.out.println("==============================================================");
        System.out.println("\t" + "1. Bumper carts");
        System.out.println("\t" + "2. Spin (max. 5 tickets)");
        System.out.println("\t" + "3. Mirror Palace");
        System.out.println("\t" + "4. Ghost House");
        System.out.println("\t" + "5. Hawaii (max. 10 tickets)");
        System.out.println("\t" + "6. Ladder Climbing");
        System.out.println("\t" + "7. Return to previous page");
        System.out.println("==============================================================");
        System.out.println("==============================================================\n");
    }


    private void exeSubMenuBuyTickets(){
        Scanner scanner2 = new Scanner(System.in);
        String attractionName;
        switch(scanner2.next()){
            case "1":
                attractionName = "Bumper Carts";
                bumperCarts.buyTickets(howManyTickets(attractionName));
                break;
            case "2":
                attractionName = "Spin";
                spin.buyTickets(howManyTickets(attractionName));
                break;
            case "3":
                attractionName = "Mirror Palace";
                mirrorPalace.buyTickets(howManyTickets(attractionName));
                break;
            case "4":
                attractionName = "Ghost House";
                ghostHouse.buyTickets(howManyTickets(attractionName));
                break;
            case "5":
                attractionName = "Hawaii";
                hawaii.buyTickets(howManyTickets(attractionName));
                break;
            case "6":
                attractionName = "Ladder Climbing";
                ladderClimbing.buyTickets(howManyTickets(attractionName));
                break;
            case "7":
                printMainMenu();
                exeMainUserInput();
                break;
            default:
                System.out.println("This is not a valid option");
        }
    }

    private  int howManyTickets(String attractionName){
        System.out.println("How many " + attractionName + " tickets do you wish to purchase?");
        Scanner scanner2 = new Scanner(System.in);
        return scanner2.nextInt();
    }

    private void showTicketOverview(){
        System.out.println("==============================================================");
        System.out.println("==============================================================");
        System.out.println("CARNAVAL TICKET SALE OVERVIEW:\n");
        System.out.println("==============================================================");
        System.out.println("Total tickets sold: " + Attraction.carnavalTicketsSold + "\n");
        System.out.println("==============================================================");
        System.out.println("Tickets sold per attraction: ");
        System.out.println("==============================================================");
        System.out.println("\t" + "Bumper carts tickets sold: " + bumperCarts.totalTicketsSold);
        System.out.println("\t" + "Spin tickets sold: " + spin.totalTicketsSold);
        System.out.println("\t" + "Mirror Palace tickets sold: " + mirrorPalace.totalTicketsSold);
        System.out.println("\t" + "Ghost House tickets sold: " + ghostHouse.totalTicketsSold);
        System.out.println("\t" + "Hawaii tickets sold: " + hawaii.totalTicketsSold);
        System.out.println("\t" + "Ladder Climbing tickets sold: " + ladderClimbing.totalTicketsSold);
        System.out.println("==============================================================");
        System.out.println("==============================================================\n");
    }

    private void showFinancialOverview(){
        System.out.println("==============================================================");
        System.out.println("==============================================================");
        System.out.println("CARNAVAL FINANCIAL OVERVIEW:\n");
        System.out.println("==============================================================");
        System.out.println("Total turnover: " + Attraction.carnavalTurnover + "\n");
        System.out.println("==============================================================");
        System.out.println("Turnover per attraction: ");
        System.out.println("==============================================================");
        System.out.println("\t" + "Bumper carts turnover: " + bumperCarts.totalRevenue);
        System.out.println("\t" + "Spin turnover: " + spin.totalRevenue);
        System.out.println("\t" + "Mirror Palace turnover: " + mirrorPalace.totalRevenue);
        System.out.println("\t" + "Ghost House turnover: " + ghostHouse.totalRevenue);
        System.out.println("\t" + "Hawaii turnover: " + hawaii.totalRevenue);
        System.out.println("\t" + "Ladder Climbing turnover: " + ladderClimbing.totalRevenue);
        System.out.println("==============================================================");
        System.out.println("==============================================================\n");
    }

    private void shutDownSystem(){
        System.out.println("==============================================================");
        System.out.println("==============================================================");
        System.out.println("System is shutting down.....");
        System.out.println("Bye bye");
        System.out.println("==============================================================");
        System.out.println("==============================================================\n");
        alpha = false;
        System.exit(0);
    }

    private void calculateWhatToPay(Attraction spin,Attraction ladderClimbing){
        taxInspection.calculateTaxes(spin,ladderClimbing);
    }
}

