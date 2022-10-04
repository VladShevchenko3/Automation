package com.jens.automation2.screens.rules

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.jens.automation2.R
import com.jens.automation2.espresso.editTextField
import org.hamcrest.Matcher

object AddRuleScreen {

    private val ruleNameTextField: Matcher<View> = withId(R.id.etRuleName)
    private val saveRuleButton: Matcher<View> = withId(R.id.cmdSaveRule)
    private val addTriggerButton: Matcher<View> = withId(R.id.cmdTriggerAdd)
    private val addActionButton: Matcher<View> = withId(R.id.cmdActionAdd)

    fun actionTypeRuleName(ruleName: String) {
        editTextField(ruleNameTextField, ruleName)
    }

    fun actionClickAddTriggerButton() {
        onView(addTriggerButton).perform(click())
    }

    fun actionClickAddActionButton() {
        onView(addActionButton).perform(click())
    }

    fun actionClickOnSaveRuleButton() {
        onView(saveRuleButton).perform(click())
    }

    fun assertionTriggerIsDisplayed(description: String) {
        onView(withText(description)).check(matches(isDisplayed()))
    }

    fun assertionActionIsDisplayed(description: String) {
        onView(withText(description)).check(matches(isDisplayed()))
    }
}