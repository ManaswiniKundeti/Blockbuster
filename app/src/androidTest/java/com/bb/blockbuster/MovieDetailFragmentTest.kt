package com.bb.blockbuster

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.bb.blockbuster.view.activity.MainActivity
import com.bb.blockbuster.view.adapter.MovieListItemViewHolder
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MovieDetailFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true,true)

    @Test
    fun test_start_state() {
        // Listing fragment launch
        Espresso.onView(withId(R.id.movie_refresh_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.movies_recycler_view)).check(RecyclerViewItemCountAssertion(20))

        // Click first item
        onView(withId(R.id.movies_recycler_view))
            .inRoot(
                RootMatchers.withDecorView(
                Matchers.`is`(activityRule.activity.window.decorView)
            )).perform(
                RecyclerViewActions.actionOnItemAtPosition<MovieListItemViewHolder>(0,
                ViewActions.click()
            ))

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
    }
}