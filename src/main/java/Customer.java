import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String name;
    private final List<Rental> rentals = new ArrayList<>();

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
        String result = "Rental Record for " + getName() + "\n";

        for (Rental rental : rentals) {
            double rentalPrice = 0;

            // determines the amount for each line
            switch (rental.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    rentalPrice += 2;
                    if (rental.getDaysRented() > 2)
                        rentalPrice += (rental.getDaysRented() - 2) * 1.5;
                    break;
                case Movie.NEW_RELEASE:
                    rentalPrice += rental.getDaysRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    rentalPrice += 1.5;
                    if (rental.getDaysRented() > 3)
                        rentalPrice += (rental.getDaysRented() - 3) * 1.5;
                    break;
            }

            frequentRenterPoints++;

            if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE
                    && rental.getDaysRented() > 1)
                frequentRenterPoints++;

            result += "\t" + rental.getMovie().getTitle() + "\t"
                    + String.valueOf(rentalPrice) + "\n";
            totalAmount += rentalPrice;

        }

        result += "You owed " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points\n";


        return result;
    }
}