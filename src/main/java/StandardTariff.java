import static java.lang.Math.max;

public class StandardTariff implements Tariff {

    @Override
    public double calculatePrice(Rental each) {
        int daysRented = each.getDaysRented();
        int priceCode = each.getMovie().getPriceCode();
        return switch (priceCode) {
            case Movie.REGULAR -> 2.0 + max(0, daysRented - 2) * 1.5;
            case Movie.NEW_RELEASE -> 3.0 * daysRented;
            case Movie.CHILDRENS -> 1.5 + max(0, daysRented - 3) * 1.5;
            default -> throw new IllegalArgumentException(
                "Unexpected value: " + priceCode);
        };
    }
}
