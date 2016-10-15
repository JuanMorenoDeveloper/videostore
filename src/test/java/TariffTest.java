import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class TariffTest {

    private final Tariff tariff = new Tariff();

    @Test
    public void regular_movie_pricing() throws Exception {
        assertThat(tariff.calculatePrice(new Rental(new Movie("title", Movie.REGULAR), 1)), equalTo(2.0));
        assertThat(tariff.calculatePrice(new Rental(new Movie("title", Movie.REGULAR), 2)), equalTo(2.0));
        assertThat(tariff.calculatePrice(new Rental(new Movie("title", Movie.REGULAR), 3)), equalTo(3.5));
    }

    @Test
    public void childrens_movie_pricing() throws Exception {
        assertThat(tariff.calculatePrice(new Rental(new Movie("title", Movie.CHILDRENS), 1)), equalTo(1.5));
        assertThat(tariff.calculatePrice(new Rental(new Movie("title", Movie.CHILDRENS), 2)), equalTo(1.5));
        assertThat(tariff.calculatePrice(new Rental(new Movie("title", Movie.CHILDRENS), 3)), equalTo(1.5));
        assertThat(tariff.calculatePrice(new Rental(new Movie("title", Movie.CHILDRENS), 4)), equalTo(3.0));
        assertThat(tariff.calculatePrice(new Rental(new Movie("title", Movie.CHILDRENS), 5)), equalTo(4.5));
    }

    @Test
    public void new_release_pricing() throws Exception {
        assertThat(tariff.calculatePrice(new Rental(new Movie("title", Movie.NEW_RELEASE), 1)), equalTo(3.0));
        assertThat(tariff.calculatePrice(new Rental(new Movie("title", Movie.NEW_RELEASE), 2)), equalTo(6.0));
        assertThat(tariff.calculatePrice(new Rental(new Movie("title", Movie.NEW_RELEASE), 3)), equalTo(9.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void unhandled_price_code() throws Exception {
        tariff.calculatePrice(new Rental(new Movie("title", 999), 1));
    }
}