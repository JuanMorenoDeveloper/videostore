public class LoyaltyPlan {

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
