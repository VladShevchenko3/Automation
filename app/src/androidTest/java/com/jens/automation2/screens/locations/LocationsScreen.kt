package com.jens.automation2.screens.locations

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.jens.automation2.R
import com.jens.automation2.screens.WithTabs
import org.hamcrest.Matcher

class LocationsScreen : WithTabs {
    private val addLocationButton: Matcher<View> = withId(R.id.bAddPoi)
    private val deleteLocationButton: Matcher<View> = withText("Delete")

    fun actionClickOnAddLocationButton() {
        onView(addLocationButton).perform(click())
    }

    fun actionDeleteLocationByName(locationName: String) {
        onView(withText(locationName)).perform(longClick())
        onView(deleteLocationButton).perform(click())
    }

    fun assertionLocationIsDisplayed(locationName: String) {
        onView(withText(locationName)).check(matches(isDisplayed()))
    }

    fun assertionLocationIsNotDisplayed(locationName: String) {
        onView(withText(locationName)).check(doesNotExist())
    }
}