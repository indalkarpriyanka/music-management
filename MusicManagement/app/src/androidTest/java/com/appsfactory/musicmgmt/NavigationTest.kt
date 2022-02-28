package com.appsfactory.musicmgmt

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.appsfactory.musicmgmt.presentation.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class NavigationTest {

    @Test
    fun test_activity_navigation() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.navigation_mylist)).perform(ViewActions.click())
        onView(withId(R.id.layout_main_my_music_list))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.navigation_searchFragment)).perform(ViewActions.click())
        onView(withId(R.id.layout_main_search))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        pressBack()
        onView(withId(R.id.layout_main_my_music_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}