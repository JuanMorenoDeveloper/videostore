public class LoyaltyPlan {
    public int pointsFor(Rental rental) {
        if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE && rental.getDaysRented() > 1)
            return 2;
        return 1;
    }
}
