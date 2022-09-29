package com.jens.automation2.screens.rules

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

object RulesScreen : WithTabs {

    private val addRuleButton: Matcher<View> = withId(R.id.bAddRule)
    private val deleteRuleButton: Matcher<View> = withText("Delete")

    fun actionClickOnAddRuleButton() {
        onView(addRuleButton).perform(click())
    }

    fun actionDeleteRuleByName(nameRule: String) {
        onView(withText(nameRule)).perform(longClick())
        onView(deleteRuleButton).perform(click())
    }

    fun assertionRuleIsDisplayed(nameRule: String) {
        onView(withText(nameRule)).check(matches(isDisplayed()))
    }

    fun assertionRuleIsNotDisplayed(nameRule: String) {
        onView(withText(nameRule)).check(doesNotExist())
    }
}