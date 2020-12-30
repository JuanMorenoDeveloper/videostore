import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class StatementRenderer {

    public String render(Statement statement) {
        StringBuilder result = new StringBuilder();
        result.append("Rental Record for ").append(statement.name()).append("\n");

        statement
            .eachRental(priceRental -> result.append("\t").append(priceRental.title).append("\t")
                .append(priceRental.price)
                .append("\n"));

        result.append("You owed ").append(statement.total()).append("\n");
        statement.discount().ifPresent(discount ->
            result.append("You got a discount of ").append(discount.discountAmount)
                .append(", so now you owe ").append(discount.discountTotal)
                .append("\n")
        );
        result.append("You earned ").append(statement.points())
            .append(" frequent renter points\n");

        return result.toString();
    }


    public static class Statement {

        private final String name;
        private final List<PriceRental> priced;
        private final double discountPercentage;

        public Statement(String name, List<PriceRental> priced, double discountPercentage) {
            this.name = name;
            this.priced = priced;
            this.discountPercentage = discountPercentage;
        }

        public Optional<Discount> discount() {
            if (discountPercentage == 0.0) {
                return Optional.empty();
            }
            double discountAmount = total() * discountPercentage;
            return Optional.of(new Discount(discountAmount, total() - discountAmount));
        }

        public double total() {
            return priced.stream().mapToDouble(pr -> pr.price).sum();
        }

        public void eachRental(Consumer<PriceRental> consumer) {
            priced.forEach(consumer);
        }

        public int points() {
            return priced.stream().mapToInt(pr -> pr.points).sum();
        }

        public String name() {
            return name;
        }

        private class Discount {

            public final double discountAmount;
            public final double discountTotal;

            public Discount(double discountAmount, double discountTotal) {
                this.discountAmount = discountAmount;
                this.discountTotal = discountTotal;
            }
        }
    }
}
