/*
*Author: Eralp Yiğit Boz
*Cs 101 Lab 01
*/
public class Lab01_Q3 {
    public static void main(String[] args) {
        int energyProduction = 4200000;

        double renewableSourcePercantage = 63.5;
        int worldRenewable = (int)(energyProduction * renewableSourcePercantage / 100);
        int worldNonRenewable = (int)(energyProduction * (100 - renewableSourcePercantage) / 100);

        double germanProductionRate = 2.3;
        double germanyProduction = energyProduction * germanProductionRate / 100;

        double germanRenewableMultiplier = 1.8;
        double germanRenewable = germanyProduction * germanRenewableMultiplier / (germanRenewableMultiplier + 1);
        double germanNonRenewable = germanyProduction - germanRenewable;

        double percentOfWorldRenewable = germanRenewable / worldRenewable * 100;
        double percentOfWorldNonRenewable = germanNonRenewable / worldNonRenewable * 100;
        System.out.println(
                "The world produces " + worldRenewable + " km2 renewable energy and "
                        + worldNonRenewable + " km2 non-renewable energy.");
        System.out.println("Germany produces " + (int) germanRenewable + " km2 renewable energy and "
                + (int) Math.round(germanNonRenewable) + " km2 non-renewable energy.");
        System.out.println("Germany produces " + percentOfWorldRenewable +
                " percent of the world's renewable energy and " +
                percentOfWorldNonRenewable +
                " percent of the world's non-renewable energy.");
    }
}
