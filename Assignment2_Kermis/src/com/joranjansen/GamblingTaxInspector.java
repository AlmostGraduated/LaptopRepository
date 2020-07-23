package com.joranjansen;

public class GamblingTaxInspector {
    private static double taxationFactor = 0.30;

    public void calculateTax(GamblingTaxable someObject){
        someObject.gamblingTax(taxationFactor);
    }
}
