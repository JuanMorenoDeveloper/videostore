import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final Tariff tariff = new Tariff();
    private final LoyaltyPlan loyaltyPlan = new LoyaltyPlan();
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
        String result = "Rental Record for " + name + "\n";

        for (Rental rental : rentals) {
            double rentalPrice = tariff.calculatePrice(rental);

            frequentRenterPoints += loyaltyPlan.calculatePoints(rental);

            result += "\t" + rental.getMovie().getTitle() + "\t"
                    + String.valueOf(rentalPrice) + "\n";
            totalAmount += rentalPrice;

        }

        result += "You owed " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points\n";

        return result;
    }
}