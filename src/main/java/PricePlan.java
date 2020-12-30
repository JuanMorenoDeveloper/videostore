import java.util.List;
import java.util.stream.Collectors;

public class PricePlan {

    private final LoyaltyPlan loyaltyPlan;
    private final Tariff tariff;
    private final DiscountCalculator discountCalculator = new DiscountCalculator();

    public PricePlan(LoyaltyPlan loyaltyPlan, Tariff tariff) {
        this.loyaltyPlan = loyaltyPlan;
        this.tariff = tariff;
    }

    public StatementRenderer.Statement statement(String name, List<Rental> rentals) {
        List<PriceRental> priced = rentals.stream().map(rental -> new PriceRental(
            tariff.calculatePrice(rental),
            loyaltyPlan.calculatePoints(rental),
            rental.getMovie().getTitle()
        )).collect(Collectors.toList());
        double discountPercentage = discountCalculator.calculateDiscountFor(priced);
        return new StatementRenderer.Statement(name, priced, discountPercentage);
    }

    private class DiscountCalculator {

        public double calculateDiscountFor(List<PriceRental> priced) {
            return priced.size() >= 5 ? 0.05 : 0.00;
        }
    }
}
