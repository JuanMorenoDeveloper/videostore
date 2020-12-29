public class LoyaltyPlan {

    public int calculatePoints(Rental each) {
        if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE
            && each.getDaysRented() > 1) {
            return 2;
        }
        return 1;
    }
}
