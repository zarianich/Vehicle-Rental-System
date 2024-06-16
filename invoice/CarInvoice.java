package invoice;

import common.Prices;
import vehicleTypes.Car;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class CarInvoice implements InvoiceCalculator{

    private String invoice;

    @Override
    public String getInvoice() {
        return this.invoice;
    }

    public CarInvoice(String customerName, Car car, String startDate, int rentalPeriod) {

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate endDate = LocalDate.parse(startDate, formatter);
        //endDate is still the start date
        long actualRentalDays = ChronoUnit.DAYS.between(endDate, currentDate);
        //endDate is now actually the end date
        endDate = endDate.plusDays(rentalPeriod);

        StringBuilder sb = new StringBuilder("XXXXXXXXXX\n");

        sb.append("Date: ").append(currentDate).append("\n")
                .append("Customer Name: ").append(customerName).append("\n")
                .append("Rented Vehicle: ").append(car.getBrand()).append(" ").append(car.getModel()).append("\n\n")
                .append("Reservation start date: ").append(startDate).append("\n")
                .append("Reservation end date: ").append(endDate).append("\n")
                .append("Reserved rental days: ").append(rentalPeriod).append(" days").append("\n\n")
                .append("Actual return date: ").append(currentDate).append("\n")
                .append("Actual rental days: ").append(actualRentalDays).append("\n\n");

        double dailyRent, dailyInsurance;

        if (rentalPeriod <= 7) {
            sb.append("Rental cost per day: $").append(String.format("%.2f", Prices.carDailyCost)).append("\n");
            dailyRent = Prices.carDailyCost;
        } else {
            sb.append("Rental cost per day: $").append(String.format("%.2f", Prices.carDailyCostForMoreThanAWeek))
                    .append("\n");
            dailyRent = Prices.carDailyCostForMoreThanAWeek;
        }

        if (car.getSafetyRating() < 4) {
            dailyInsurance = car.getValue() * Prices.carInsuranceMultiplier;

            sb.append("Insurance per day: $")
                    .append(String.format("%.2f", car.getValue() * Prices.carInsuranceMultiplier)).append("\n\n");
        } else {
            dailyInsurance = car.getValue() * Prices.carInsuranceMultiplier * Prices.carAdditionalInsuranceMultiplier;

            sb.append("Initial insurance per day: $")
                    .append(String.format("%.2f", car.getValue() * Prices.carInsuranceMultiplier)).append("\n")
                    .append("Insurance discount per day: $")
                    .append(String.format("%.2f", car.getValue() * Prices.carInsuranceMultiplier -
                            car.getValue() * Prices.carInsuranceMultiplier * Prices.carAdditionalInsuranceMultiplier))
                    .append("\n").append("Insurance per day: $")
                    .append(String.format("%.2f", car.getValue() * Prices.carInsuranceMultiplier
                            * Prices.carAdditionalInsuranceMultiplier)).append("\n\n");

        }

        double totalRent, totalInsurance;

        totalRent = dailyRent * rentalPeriod;
        totalInsurance = dailyInsurance * rentalPeriod;

        if (actualRentalDays < rentalPeriod) {

            double totalRentDiscount = (rentalPeriod - actualRentalDays) * dailyRent / 2;
            double totalInsuranceDiscount = (rentalPeriod - actualRentalDays) * dailyInsurance;

            sb.append("Early return discount for rent: $")
                    .append(String.format("%.2f", totalRentDiscount)).append("\n")
                    .append("Early return discount for insurance: $")
                    .append(String.format("%.2f", totalInsuranceDiscount)).append("\n\n");

            totalRent -= totalRentDiscount;
            totalInsurance -= totalInsuranceDiscount;

        }

        sb.append("Total rent: $").append(String.format("%.2f", totalRent)).append("\n")
                .append("Total insurance: $").append(String.format("%.2f", totalInsurance)).append("\n")
                .append("Total: $").append(String.format("%.2f", totalRent + totalInsurance)).append("\n")
                .append("XXXXXXXXXX\n");

        this.invoice = sb.toString();

    }

}