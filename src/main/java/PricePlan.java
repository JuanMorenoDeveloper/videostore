import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PricePlan {
    private final Tariff tariff;
    private final LoyaltyPlan loyaltyPlan;
    private final DiscountCalculator discountCalculator = new DiscountCalculator();

    public PricePlan(LoyaltyPlan loyaltyPlan, Tariff tariff) {
        this.tariff = tariff;
        this.loyaltyPlan = loyaltyPlan;
    }

    public StatementRenderer.Statement statement(String name, List<Rental> rentals) {
        List<PricedRental> priced = rentals.stream().map(r -> new PricedRental(
                tariff.calculatePrice(r),
                loyaltyPlan.calculatePoints(r),
                r.getMovie().getTitle()
        )).collect(Collectors.toList());

        double discountPercentage = discountCalculator.calculateDiscountFor(priced);

        return new StatementRenderer.Statement(name, priced, discountPercentage);
    }

    private class DiscountCalculator {
        public double calculateDiscountFor(List<PricedRental> priced) {
            return priced.size() >= 5 ? 0.05 : 0.00;
        }
    }
}
