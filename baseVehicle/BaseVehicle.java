package baseVehicle;

public abstract class BaseVehicle implements Vehicle {

    private String brand;
    private String model;
    private double value;

    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    public BaseVehicle(String brand, String model, int value) {
        this.brand = brand;
        this.model = model;
        this.value = value;
    }

}
