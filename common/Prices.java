package common;

public final class Prices {

    private Prices(){
        throw new UnsupportedOperationException("This class holds prices and shouldn't be instantiated!");
    }

    public static final double carDailyCost = 20;

    public static final double carDailyCostForMoreThanAWeek = 15;

    public static final double carInsuranceMultiplier = 0.0001;

    public static final double carAdditionalInsuranceMultiplier = 0.9;

    public static final double motorcycleDailyCost = 15;

    public static final double motorcycleDailyCostForMoreThanAWeek = 10;

    public static final double motorcycleInsuranceMultiplier = 0.0002;

    public static final double motorcycleAdditionalInsuranceMultiplier = 1.2;

    public static final double cargoVanDailyCost = 50;

    public static final double cargoVanDailyCostForMoreThanAWeek = 40;

    public static final double cargoVanInsuranceMultiplier = 0.0003;

    public static final double cargoVanAdditionalInsuranceMultiplier = 0.85;

}
