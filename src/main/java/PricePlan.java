import java.util.function.Function;

public class PricePlan implements Function<Rental, PricedRental> {
    private final Tariff tariff;
    private final LoyaltyPlan loyaltyPlan;

    public PricePlan(LoyaltyPlan loyaltyPlan, Tariff tariff) {
        this.tariff = tariff;
        this.loyaltyPlan = loyaltyPlan;
    }

    @Override
    public PricedRental apply(Rental r) {
        return new PricedRental(
                tariff.calculatePrice(r),
                loyaltyPlan.calculatePoints(r),
                r.getMovie().getTitle()
        );
    }
}
