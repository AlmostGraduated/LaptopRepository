package com.joranjansen;

import java.util.List;

public class TaxInspection {
    private static double taxationFactor = 0.30;
    private double totalSpinTaxPaid;
    private double totalLadderClimbingTaxPaid;



    public void calculateTaxes(Attraction spin, Attraction ladderClimbing){
        double tempSpinTaxes = 0;
        double tempLadderClimbing = 0;
        tempSpinTaxes = ((GamblingTaxable)spin).financialImpact(taxationFactor);
        this.totalSpinTaxPaid += tempSpinTaxes;
        tempLadderClimbing = ((GamblingTaxable)ladderClimbing).financialImpact(taxationFactor);
        this.totalLadderClimbingTaxPaid += tempLadderClimbing;
        System.out.println("==============================================================");
        System.out.println("==============================================================");
        System.out.println("TAXES OVERVIEW:");
        System.out.println("==============================================================");
        System.out.println("Current payment:");
        System.out.println("==============================================================");
        System.out.println("\t" + "Total payed: " + (tempSpinTaxes + tempLadderClimbing));
        System.out.println("==============================================================");
        System.out.println("\t" + "\t" + "Spin: " + tempSpinTaxes);
        System.out.println("\t" + "\t" + "Ladder Climbing: " + tempLadderClimbing);
        System.out.println("==============================================================");
        System.out.println("==============================================================");
        System.out.println("Total taxes paid since opening: ");
        System.out.println("==============================================================");
        System.out.println("\t" + "Total payed: " + (totalSpinTaxPaid +  totalLadderClimbingTaxPaid));
        System.out.println("==============================================================");
        System.out.println("\t" + "\t" + "Spin: " + totalSpinTaxPaid);
        System.out.println("\t" + "\t" + "Ladder Climbing: " + totalLadderClimbingTaxPaid);
        System.out.println("==============================================================");
        System.out.println("==============================================================\n");
    }
}
