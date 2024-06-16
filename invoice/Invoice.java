package invoice;

import vehicleTypes.Car;
import vehicleTypes.CargoVan;
import vehicleTypes.Motorcycle;

public class Invoice {

    private String invoice;
    public void print() {
        System.out.println(this.invoice);
    }

    //invoice for car
    public Invoice(String customerName, Car car, String startDate, int rentalPeriod){
        CarInvoice temp = new CarInvoice(customerName, car, startDate, rentalPeriod);
        this.invoice = temp.getInvoice();
    }

    //invoice for motorcycle
    public Invoice(String customerName, Motorcycle motorcycle, int driverAge, String startDate, int rentalPeriod){
        MotorcycleInvoice temp = new MotorcycleInvoice(customerName, motorcycle, driverAge, startDate, rentalPeriod);
        this.invoice = temp.getInvoice();
    }

    //invoice for cargo van
    public Invoice(String customerName, CargoVan cargoVan, int driverExperience, String startDate, int rentalPeriod){
        CargoVanInvoice temp = new CargoVanInvoice(customerName, cargoVan, driverExperience, startDate, rentalPeriod);
        this.invoice = temp.getInvoice();
    }

}
