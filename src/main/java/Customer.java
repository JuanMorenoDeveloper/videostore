import java.util.ArrayList;
import java.util.List;

public class Customer {

    private final LoyaltyPlan loyaltyPlan = new LoyaltyPlan();
    private final Tariff tariff = new Tariff();
    private final List<Rental> rentals = new ArrayList<>();
    private final String name;

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    private String getName() {
        return name;
    }

    public String generateStatement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        StringBuilder result = new StringBuilder();
        result.append("Rental Record for ").append(getName()).append("\n");

        for (Rental rental : rentals) {
            double rentalPrice = tariff.calculatePrice(rental);

            frequentRenterPoints += loyaltyPlan.calculatePoints(rental);

            result.append("\t").append(rental.getMovie().getTitle()).append("\t").append(rentalPrice)
                .append("\n");
            totalAmount += rentalPrice;

        }

        result.append("You owed ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints)
            .append(" frequent renter points\n");

        return result.toString();
    }

    public static class Tariff {

        public double calculatePrice(Rental each) {
            double rentalPrice = 0;

            switch (each.getMovie().getPriceCode()) {
            // determines the amount for each line
                case Movie.REGULAR:
                    rentalPrice+= 2;
                    if (each.getDaysRented() > 2) {
                        rentalPrice += (each.getDaysRented() - 2) * 1.5;
                    }
                    break;
                case Movie.NEW_RELEASE:
                    rentalPrice += each.getDaysRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    rentalPrice += 1.5;
                    if (each.getDaysRented() > 3) {
                        rentalPrice += (each.getDaysRented() - 3) * 1.5;
                    }
                    break;
                default:
                    break;
            }
            return rentalPrice;
        }
    }

    public static class LoyaltyPlan {

        public int calculatePoints(Rental each) {
            int frequentRenterPoints = 0;
            frequentRenterPoints++;
    
            if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE
                && each.getDaysRented() > 1) {
                frequentRenterPoints++;
            }
            return frequentRenterPoints;
        }
    }
}