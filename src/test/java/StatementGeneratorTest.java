import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StatementGeneratorTest {

    private final StatementGenerator statementGenerator = new StatementGenerator();

    @Test
    public void testSingleNewReleaseStatement() {
        assertEquals("Rental Record for Fred\n\tThe Cell\t9.0\nYou owed 9.0\nYou earned 2 frequent renter points\n",
                statementGenerator.statement("Fred", rentals(rentalForPeriod(3, newRelease("The Cell"))))
        );
    }

    @Test
    public void testDualNewReleaseStatement() {
        assertEquals("Rental Record for Fred\n\tThe Cell\t9.0\n\tThe Tigger Movie\t9.0\nYou owed 18.0\nYou earned 4 frequent renter points\n",
                statementGenerator.statement("Fred", rentals(
                        rentalForPeriod(3, newRelease("The Cell")),
                        rentalForPeriod(3, newRelease("The Tigger Movie"))
                ))
        );
    }

    @Test
    public void testSingleChildrensStatement() {
        assertEquals("Rental Record for Fred\n\tThe Tigger Movie\t1.5\nYou owed 1.5\nYou earned 1 frequent renter points\n",
                statementGenerator.statement("Fred", rentals(rentalForPeriod(3, childrensMovie("The Tigger Movie"))))
        );
    }

    @Test
    public void testMultipleRegularStatement() {
        assertEquals("Rental Record for Fred\n\tPlan 9 from Outer Space\t2.0\n\t8 1/2\t2.0\n\tEraserhead\t3.5\nYou owed 7.5\nYou earned 3 frequent renter points\n",
                statementGenerator.statement("Fred", rentals(
                rentalForPeriod(1, regularMovie("Plan 9 from Outer Space")),
                rentalForPeriod(2, regularMovie("8 1/2")),
                rentalForPeriod(3, regularMovie("Eraserhead"))
        )));
    }

    private List<Rental> rentals(Rental... rental) {
        return Arrays.asList(rental);
    }

    private Rental rentalForPeriod(int daysRented, Movie movie) {
        return new Rental(movie, daysRented);
    }

    private Movie regularMovie(String title) {
        return new Movie(title, Movie.REGULAR);
    }

    private Movie newRelease(String title) {
        return new Movie(title, Movie.NEW_RELEASE);
    }

    private Movie childrensMovie(String title) {
        return new Movie(title, Movie.CHILDRENS);
    }
}