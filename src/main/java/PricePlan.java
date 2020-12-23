import java.util.function.Function;

public class PricePlan implements Function<Rental, PriceRental> {

    private final LoyaltyPlan loyaltyPlan;
    private final Tariff tariff;

    public PricePlan(LoyaltyPlan loyaltyPlan, Tariff tariff) {
        this.loyaltyPlan = loyaltyPlan;
        this.tariff = tariff;
    }

    @Override
    public PriceRental apply(Rental rental) {
        return new PriceRental(
            tariff.calculatePrice(rental),
            loyaltyPlan.calculatePoints(rental),
            rental.getMovie().getTitle()
        );
    }
}
