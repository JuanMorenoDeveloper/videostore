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

    public String generateStatement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        StringBuilder result = new StringBuilder();
        result.append("Rental Record for ").append(name).append("\n");

        for (Rental rental : rentals) {
            double rentalPrice = tariff.calculatePrice(rental);

            frequentRenterPoints += loyaltyPlan.calculatePoints(rental);

            result.append("\t").append(rental.getMovie().getTitle()).append("\t")
                .append(rentalPrice)
                .append("\n");
            totalAmount += rentalPrice;

        }

        result.append("You owed ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints)
            .append(" frequent renter points\n");

        return result.toString();
    }

}