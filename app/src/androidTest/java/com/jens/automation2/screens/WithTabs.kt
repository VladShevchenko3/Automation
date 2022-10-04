package com.jens.automation2.screens

import android.R
import android.view.View
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.jens.automation2.espresso.findViewWithDescendantAndWithParent
import org.hamcrest.Matcher

interface WithTabs {

    companion object {
        private val dashboard: Matcher<View> = withId(R.id.tabs)
        private val rulesButton: Matcher<View> = withText("Rules")
        private val profilesButton: Matcher<View> = withText("Profiles")
        private val locationsButton: Matcher<View> = withText("Locations")
        private val overviewButton: Matcher<View> = withText("Overview")
    }

    fun actionOpenRules() {
        findViewWithDescendantAndWithParent(
            rulesButton,
            dashboard
        ).perform(click())
    }

    fun actionOpenProfiles() {
        findViewWithDescendantAndWithParent(
            profilesButton,
            dashboard
        ).perform(click())
    }

    fun actionOpenLocation() {
        findViewWithDescendantAndWithParent(
            locationsButton,
            dashboard
        ).perform(click())
    }

    fun actionOpenOverview(): ViewInteraction {
        return findViewWithDescendantAndWithParent(
            overviewButton,
            dashboard
        ).perform(click())
    }
}