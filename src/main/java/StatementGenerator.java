import java.util.List;
import java.util.stream.Collectors;

public class StatementGenerator {

    public String generate(String name, List<Rental> rentals, PricePlan plan) {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + name + "\n";

        List<PricedRental> priced = rentals.stream().map(plan).collect(Collectors.toList());

        for (PricedRental pricedRental : priced) {
            double rentalPrice = pricedRental.price;

            frequentRenterPoints += pricedRental.points;

            result += "\t" + pricedRental.title + "\t" + String.valueOf(rentalPrice) + "\n";
            totalAmount += rentalPrice;

        }

        result += "You owed " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points\n";

        return result;
    }
}
