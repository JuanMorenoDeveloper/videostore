import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class StatementGenerator {

    private final LoyaltyPlan plan = new LoyaltyPlan();
    private final Tariff tariff = new Tariff();

    public String statement(String name, List<Rental> rentals) {
        return new Statement(name, rentals.stream().map(
                r -> new StatementLineItem(
                        r.getMovie().getTitle(),
                        tariff.priceOf(r),
                        plan.pointsFor(r)
                )
        ).collect(Collectors.toList())).render();
    }
}