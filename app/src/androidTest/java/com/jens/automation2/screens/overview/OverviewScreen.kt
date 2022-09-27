package com.jens.automation2.screens.overview

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import com.jens.automation2.R
import com.jens.automation2.matchers.ToastMatcher
import com.jens.automation2.screens.WithTabs
import junit.framework.Assert.assertFalse
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.Matcher

class OverviewScreen : WithTabs {
    private val serviceButton: Matcher<View> = withId(R.id.tbArmMastListener)
    private val device: UiDevice
      get() = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    fun actionClickOnServiceButton() {
        onView(serviceButton).perform(click())
    }

    fun assertionServiceButtonHasText(text: String) {
        onView(serviceButton).check(matches(withText(text)))
    }

    fun assertionMessageIsDisplayed(message: String) {
        onView(withText(containsString(message))).inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }
    fun assertionMessageIsNotDisplayed(message: String) {
        onView(withText(containsString(message))).inRoot(ToastMatcher())
            .check(doesNotExist())
    }
    fun actionWaitForViewDoesNotExist(message: String) {
        device.waitForIdle()
        assertFalse(device.hasObject(By.text(message)))
    }
}
