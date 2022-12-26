package com.jens.automation2.matchers

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import org.hamcrest.Matcher

class ActionWaitingTime(private val delayMillis: Long) : ViewAction {

    override fun getConstraints(): Matcher<View> {
        return isRoot()
    }

    override fun getDescription() = "wait for " + delayMillis + "milliseconds"

    override fun perform(uiController: UiController, view: View) {
        uiController.loopMainThreadForAtLeast(delayMillis)
    }
}