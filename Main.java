import invoice.Invoice;
import vehicleTypes.Car;
import vehicleTypes.CargoVan;
import vehicleTypes.Motorcycle;

public class Main {

    public static void main(String[] args) {

        Car a = new Car("Mitsubishi", "Mirage", 15000, 3);
        Motorcycle b = new Motorcycle("Triumph", "Tiger Sport 660", 10000);
        CargoVan c = new CargoVan("Citroen", "Jumper", 20000);

        Invoice invA = new Invoice("John Doe", a, "2024-06-06", 10);
        invA.print();

        Invoice invB = new Invoice("Mary Johnson", b, 20, "2024-06-06", 10);
        invB.print();

        Invoice invC = new Invoice("John Markson", c, 8, "2024-06-06", 15);
        invC.print();

    }
}
