package com.jens.automation2.screens.profiles

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

object ProfilesScreen : WithTabs {

    private val addProfileButton: Matcher<View> = withId(R.id.bAddProfile)
    private val deleteRuleButton: Matcher<View> = withText("Delete")

    fun actionClickOnAddProfile() {
        onView(addProfileButton).perform(click())
    }

    fun actionDeleteProfileByName(profileName: String) {
        onView(withText(profileName)).perform(longClick())
        onView(deleteRuleButton).perform(click())
    }

    fun assertionProfileIsDisplayed(profileName: String) {
        onView(withText(profileName)).check(matches(isDisplayed()))
    }

    fun assertionProfileIsNotDisplayed(profileName: String) {
        onView(withText(profileName)).check(doesNotExist())
    }
}