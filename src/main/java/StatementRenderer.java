import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class StatementRenderer {

    public String render(Statement statement) {

        StringBuilder result = new StringBuilder("Rental Record for " + statement.name() + "\n");

        statement.eachRental(pricedRental ->
            result.append("\t" + pricedRental.title + "\t" + String.valueOf(pricedRental.price) + "\n")
        );

        result.append("You owed " + String.valueOf(statement.total()) + "\n");

        statement.discount().ifPresent(discount -> result.append("You got a discount of " + String.valueOf(discount.discountAmount) +
            ", so now you owe " + String.valueOf(discount.discountedTotal) + "\n"
        ));

        result.append("You earned " + String.valueOf(statement.points()) + " frequent renter points\n");

        return result.toString();
    }

    public static class Statement {

        private final String name;
        private final List<PricedRental> priced;
        private final double discountPercentage;

        public Statement(String name, List<PricedRental> priced, double discountPercentage) {
            this.name = name;
            this.priced = priced;
            this.discountPercentage = discountPercentage;
        }

        public Optional<Discount> discount() {
            if ( discountPercentage == 0.0) {
                return Optional.empty();
            }
            else {
                double total = total();
                double discountAmount = total * discountPercentage;
                return Optional.of(new Discount(discountAmount, total - discountAmount));
            }
        }

        public void eachRental(Consumer<PricedRental> consumer) {
            priced.forEach(consumer);
        }

        public double total() {
            return priced.stream().collect(Collectors.summingDouble(pr -> pr.price));
        }

        public int points() {
            return priced.stream().collect(Collectors.summingInt(pr -> pr.points));
        }

        public String name() {
            return name;
        }

        private class Discount {
            public final double discountAmount;
            public final double discountedTotal;

            public Discount(double discountAmount, double discountedTotal) {
                this.discountAmount = discountAmount;
                this.discountedTotal = discountedTotal;
            }
        }
    }
}
