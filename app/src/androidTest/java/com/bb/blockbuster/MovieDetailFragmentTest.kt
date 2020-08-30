package com.bb.blockbuster

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bb.blockbuster.view.fragment.MovieDetailFragment
import com.bb.blockbuster.view.fragment.MoviesListFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDetailFragmentTest {

    @Test
    fun test_start_state() {
        launchFragmentInContainer<MovieDetailFragment>()

        onView(withId(R.id.movie_detail_name_text_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.movie_detail_name_text_view))
            .check(ViewAssertions.matches(withText(MOVIE_NAME)))

        onView(withId(R.id.movie_detail_rating_bar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.vote_count_text_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.vote_count_text_view))
            .check(ViewAssertions.matches(withText(MOVIE_VOTES)))

        onView(withId(R.id.release_date_text_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.release_date_text_view))
            .check(ViewAssertions.matches(withText(MOVIE_RELEASE_DATE)))

        onView(withId(R.id.add_to_cart_button))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.add_to_cart_button))
            .check(ViewAssertions.matches(withText(ADD_TO_CART_TEXT)))



    }
}