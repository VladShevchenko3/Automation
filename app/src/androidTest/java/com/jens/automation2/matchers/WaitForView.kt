package com.jens.automation2.matchers

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Matcher
import java.lang.System.currentTimeMillis

const val DEFAULT_TIMEOUT_MILLIS = 10_000L
const val DEFAULT_INTERVAL_MILLIS = 250L
fun waitForView(
    matcher: Matcher<View>,
    timeoutMillis: Long = DEFAULT_TIMEOUT_MILLIS,
    intervalMillis: Long = DEFAULT_INTERVAL_MILLIS
): ViewInteraction {
    val startTime = currentTimeMillis()
    val endTime = startTime + timeoutMillis

    var lastException: Throwable
    do {
        try {
            return onView(matcher).check(matches(isDisplayed()))
        } catch (e: NoMatchingViewException) {
            lastException = e
            ActionWaitingTime(intervalMillis)
        }
    } while (currentTimeMillis() < endTime)

    throw lastException
}