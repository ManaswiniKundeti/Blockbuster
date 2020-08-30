package com.bb.blockbuster

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
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
        Espresso.onView(ViewMatchers.withId(R.id.movie_detail_name_text_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}