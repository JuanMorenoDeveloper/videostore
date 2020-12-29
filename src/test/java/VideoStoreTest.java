import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;

public class VideoStoreTest {

    private final Customer customer = new Customer("Fred",
        new PricePlan(new LoyaltyPlan(), new Tariff()));

    @Test
    public void basic_tariff() {
        Customer basic = new Customer("basic", new PricePlan(new LoyaltyPlan(), new Tariff()));
        basic.addRental(new Rental(newChildrens("childrens"), 3));
        basic.addRental(new Rental(newRegular("regular"), 3));
        basic.addRental(new Rental(newRelease("new"), 3));

        assertThat(basic.generateStatement(new StatementGenerator()),
            hasStatementElement("Rental Record for basic",
                "\tchildrens\t1.5",
                "\tregular\t3.5",
                "\tnew\t9.0",
                "You owed 14.0",
                "You earned 4 frequent renter points"));
    }

    private Matcher<String> hasStatementElement(String... lines) {
        return equalTo(stream(lines).collect(joining("\n", "", "\n")));
    }

    private Movie newRegular(String title) {
        return new Movie(title, Movie.REGULAR);
    }

    private Movie newRelease(String title) {
        return new Movie(title, Movie.NEW_RELEASE);
    }

    private Movie newChildrens(String title) {
        return new Movie(title, Movie.CHILDRENS);
    }
}