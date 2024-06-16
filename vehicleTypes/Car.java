package vehicleTypes;

import baseVehicle.BaseVehicle;

public class Car extends BaseVehicle{

    private int safetyRating;

    public int getSafetyRating(){
        return this.safetyRating;
    }

    public Car(String brand, String model, int value, int safetyRating){
        super(brand, model, value);
        this.safetyRating = safetyRating;
    }

}
