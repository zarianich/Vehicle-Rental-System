package invoice;

import common.Prices;
import vehicleTypes.CargoVan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class CargoVanInvoice implements InvoiceCalculator{

    private String invoice;

    @Override
    public String getInvoice() {
        return this.invoice;
    }

    public CargoVanInvoice(String customerName, CargoVan cargoVan, int driverExperience, String startDate, int rentalPeriod) {

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
                .append("Rented Vehicle: ").append(cargoVan.getBrand()).append(" ").append(cargoVan.getModel()).append("\n\n")
                .append("Reservation start date: ").append(startDate).append("\n")
                .append("Reservation end date: ").append(endDate).append("\n")
                .append("Reserved rental days: ").append(rentalPeriod).append(" days").append("\n\n")
                .append("Actual return date: ").append(currentDate).append("\n")
                .append("Actual rental days: ").append(actualRentalDays).append("\n\n");

        double dailyRent, dailyInsurance;

        if (rentalPeriod <= 7) {
            sb.append("Rental cost per day: $").append(String.format("%.2f", Prices.cargoVanDailyCost)).append("\n");
            dailyRent = Prices.cargoVanDailyCost;
        } else {
            sb.append("Rental cost per day: $").append(String.format("%.2f", Prices.cargoVanDailyCostForMoreThanAWeek))
                    .append("\n");
            dailyRent = Prices.cargoVanDailyCostForMoreThanAWeek;
        }

        if (driverExperience <= 5) {
            dailyInsurance = cargoVan.getValue() * Prices.cargoVanInsuranceMultiplier;

            sb.append("Insurance per day: $")
                    .append(String.format("%.2f", cargoVan.getValue() * Prices.cargoVanInsuranceMultiplier)).append("\n\n");
        } else {
            dailyInsurance = cargoVan.getValue() * Prices.cargoVanInsuranceMultiplier * Prices.cargoVanAdditionalInsuranceMultiplier;

            sb.append("Initial insurance per day: $")
                    .append(String.format("%.2f", cargoVan.getValue() * Prices.cargoVanInsuranceMultiplier)).append("\n")
                    .append("Insurance discount per day: $")
                    .append(String.format("%.2f", cargoVan.getValue() * Prices.cargoVanInsuranceMultiplier -
                            cargoVan.getValue() * Prices.cargoVanInsuranceMultiplier * Prices.cargoVanAdditionalInsuranceMultiplier))
                    .append("\n").append("Insurance per day: $")
                    .append(String.format("%.2f", cargoVan.getValue() * Prices.cargoVanInsuranceMultiplier
                            * Prices.cargoVanAdditionalInsuranceMultiplier)).append("\n\n");

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
