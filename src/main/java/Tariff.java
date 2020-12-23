public class Tariff {

    public double calculatePrice(Rental each) {
        double rentalPrice = 0;

        switch (each.getMovie().getPriceCode()) {
            // determines the amount for each line
            case Movie.REGULAR:
                rentalPrice += 2;
                if (each.getDaysRented() > 2) {
                    rentalPrice += (each.getDaysRented() - 2) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                rentalPrice += each.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                rentalPrice += 1.5;
                if (each.getDaysRented() > 3) {
                    rentalPrice += (each.getDaysRented() - 3) * 1.5;
                }
                break;
            default:
                break;
        }
        return rentalPrice;
    }
}
