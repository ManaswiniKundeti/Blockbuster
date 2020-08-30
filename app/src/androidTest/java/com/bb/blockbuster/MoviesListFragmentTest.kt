package com.bb.blockbuster

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bb.blockbuster.view.fragment.MoviesListFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoviesListFragmentTest {

    @Test
    fun test_start_state() {
        launchFragmentInContainer<MoviesListFragment>()
        Espresso.onView(withId(R.id.movie_refresh_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.movies_recycler_view)).check(RecyclerViewItemCountAssertion(22))
    }



}