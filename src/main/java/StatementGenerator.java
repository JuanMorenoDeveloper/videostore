import java.util.List;
import java.util.stream.Collectors;

public class StatementGenerator {

    public String generate(String name,
        List<Rental> rentals, PricePlan plan) {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        StringBuilder result = new StringBuilder();
        result.append("Rental Record for ").append(name).append("\n");

        List<PriceRental> priced = rentals.stream().map(plan)
            .collect(Collectors.toList());

        for (PriceRental priceRental : priced) {

            double rentalPrice = priceRental.price;

            frequentRenterPoints += priceRental.points;

            result.append("\t").append(priceRental.title).append("\t")
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
