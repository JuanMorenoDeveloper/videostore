import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final List<Rental> rentals = new ArrayList<>();
    private final String name;
    private final PricePlan plan;

    public Customer(String name, PricePlan plan) {
        this.name = name;
        this.plan = plan;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String generateStatement(StatementGenerator generator) {
        return generator.generate(name, rentals, plan);
    }
}