import org.junit.Assert;
import org.junit.Test;

public class VideoStoreTest {

    private final Customer customer = new Customer("Fred");

    @Test
    public void testSingleNewReleaseStatement() {
        customer.addRental(new Rental(newRelease("The Cell"), 3));
        Assert.assertEquals(
            "Rental Record for Fred\n\tThe Cell\t9.0\nYou owed 9.0\nYou earned 2 frequent renter points\n",
            customer.statement());
    }

    private Movie newRelease(String title) {
        return new Movie(title, Movie.NEW_RELEASE);
    }

    @Test
    public void testDualNewReleaseStatement() {
        customer.addRental(new Rental(newRelease("The Cell"), 3));
        customer.addRental(new Rental(newRelease("The Tigger Movie"), 3));
        Assert.assertEquals(
            "Rental Record for Fred\n\tThe Cell\t9.0\n\tThe Tigger Movie\t9.0\nYou owed 18.0\nYou earned 4 frequent renter points\n",
            customer.statement());
    }

    @Test
    public void testSingleChildrensStatement() {
        customer.addRental(new Rental(newChildrens("The Tigger Movie"), 3));
        Assert.assertEquals(
            "Rental Record for Fred\n\tThe Tigger Movie\t1.5\nYou owed 1.5\nYou earned 1 frequent renter points\n",
            customer.statement());
    }

    private Movie newChildrens(String title) {
        return new Movie(title, Movie.CHILDRENS);
    }

    @Test
    public void testMultipleRegularStatement() {
        customer.addRental(new Rental(newRegular("Plan 9 from Outer Space"), 1));
        customer.addRental(new Rental(newRegular("8 1/2"), 2));
        customer.addRental(new Rental(newRegular("Eraserhead"), 3));

        Assert.assertEquals(
            "Rental Record for Fred\n\tPlan 9 from Outer Space\t2.0\n\t8 1/2\t2.0\n\tEraserhead\t3.5\nYou owed 7.5\nYou earned 3 frequent renter points\n",
            customer.statement());
    }

    private Movie newRegular(String title) {
        return new Movie(title, Movie.REGULAR);
    }
}