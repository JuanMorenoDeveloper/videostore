import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class LoyaltyPlanTest {

    private final LoyaltyPlan plan = new LoyaltyPlan();

    @Test
    public void a_rental_earns_one_point() throws Exception {
        assertThat(plan.calculatePoints(new Rental(new Movie("title", Movie.REGULAR), 1)), equalTo(1));
    }

    @Test
    public void new_release_movie_for_one_day_earns_one_point() throws Exception {
        assertThat(plan.calculatePoints(new Rental(new Movie("title", Movie.NEW_RELEASE), 1)), equalTo(1));
    }

    @Test
    public void new_release_movie_for_more_than_one_day_earns_two_points() throws Exception {
        assertThat(plan.calculatePoints(new Rental(new Movie("title", Movie.NEW_RELEASE), 2)), equalTo(2));
        assertThat(plan.calculatePoints(new Rental(new Movie("title", Movie.NEW_RELEASE), 3)), equalTo(2));
    }
}