package com.jens.automation2.matchers

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

class ActionWaitingTime(private val delay: Long) : ViewAction {
    override fun getConstraints(): Matcher<View> {
        return ViewMatchers.isRoot()
    }

    override fun getDescription() = "wait for " + delay + "milliseconds"

    override fun perform(uiController: UiController, view: View) {
        uiController.loopMainThreadForAtLeast(delay)
    }
}