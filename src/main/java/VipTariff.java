import static java.lang.Math.max;

public class VipTariff implements Tariff {

    @Override
    public double calculatePrice(Rental each) {
        int daysRented = each.getDaysRented();
        int priceCode = each.getMovie().getPriceCode();
        return switch (priceCode) {
            case Movie.REGULAR -> 1.0 + daysRented;
            case Movie.NEW_RELEASE -> 2.0 * daysRented;
            case Movie.CHILDRENS -> max(0, daysRented - 3) * 1.0;
            default -> throw new IllegalArgumentException(
                "Unexpected value: " + priceCode);
        };
    }
}
