public class LoyaltyPlan {
    public int calculatePoints(Rental rental) {
        int frequentRenterPoints = 0;
        frequentRenterPoints++;

        if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE
                && rental.getDaysRented() > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }
}
