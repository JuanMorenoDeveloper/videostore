import static java.lang.Integer.max;

public class Tariff {
    public double priceOf(Rental rental) {
        Movie movie = rental.getMovie();
        int daysRented = rental.getDaysRented();
        switch (movie.getPriceCode()) {
            case Movie.REGULAR:
                return 2 + 1.5 * max(0, daysRented - 2);
            case Movie.NEW_RELEASE:
                return daysRented * 3;
            case Movie.CHILDRENS:
                return 1.5 + 1.5 * max(0, daysRented - 3);
            default:
                throw new IllegalArgumentException("unsupported price code " + movie.getPriceCode());
        }
    }
}
