import org.hamcrest.Matcher;
import org.junit.Ignore;
import org.junit.Test;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class VideoStoreEndToEndTest {

    @Test
    public void basic_tariff() throws Exception {
        Customer basic = new Customer("basic", new PricePlan(new LoyaltyPlan(), new StandardTariff()));
        basic.addRental(new Rental(childrens("childrens"), 3));
        basic.addRental(new Rental(regularMovie("regular"), 3));
        basic.addRental(new Rental(newRelease("new"), 3));

        assertThat(basic.generateStatement(new StatementRenderer()),
                hasStatementLines(
                        "Rental Record for basic",
                        "\tchildrens\t1.5",
                        "\tregular\t3.5",
                        "\tnew\t9.0",
                        "You owed 14.0",
                        "You earned 4 frequent renter points"
                )
        );
    }

    @Test
    public void basic_tariff_with_discount() throws Exception {
        Customer basic = new Customer("basic", new PricePlan(new LoyaltyPlan(), new StandardTariff()));
        basic.addRental(new Rental(childrens("childrens"), 3));
        basic.addRental(new Rental(regularMovie("regular"), 3));
        basic.addRental(new Rental(newRelease("new"), 3));
        basic.addRental(new Rental(newRelease("new1"), 3));
        basic.addRental(new Rental(newRelease("new2"), 3));

        assertThat(basic.generateStatement(new StatementRenderer()),
                hasStatementLines(
                        "Rental Record for basic",
                        "\tchildrens\t1.5",
                        "\tregular\t3.5",
                        "\tnew\t9.0",
                        "\tnew1\t9.0",
                        "\tnew2\t9.0",
                        "You owed 32.0",
                        "You got a discount of 1.6, so now you owe 30.4",
                        "You earned 8 frequent renter points"
                )
        );
    }

    @Test
    public void vip_tariff() throws Exception {
        Customer vip = new Customer("vip", new PricePlan(new LoyaltyPlan(), new VipTariff()));
        vip.addRental(new Rental(childrens("childrens"), 3));
        vip.addRental(new Rental(regularMovie("regular"), 3));
        vip.addRental(new Rental(newRelease("new"), 3));

        assertThat(vip.generateStatement(new StatementRenderer()),
                hasStatementLines(
                        "Rental Record for vip",
                        "\tchildrens\t1.0",
                        "\tregular\t3.0",
                        "\tnew\t6.0",
                        "You owed 10.0",
                        "You earned 4 frequent renter points"
                )
        );
    }

    private Matcher<String> hasStatementLines(String... lines) {
        return equalTo(stream(lines).collect(joining("\n", "", "\n")));
    }

    private Movie childrens(String title) {
        return new Movie(title, Movie.CHILDRENS);
    }

    private Movie newRelease(String title) {
        return new Movie(title, Movie.NEW_RELEASE);
    }

    private Movie regularMovie(String title) {
        return new Movie(title, Movie.REGULAR);
    }
}