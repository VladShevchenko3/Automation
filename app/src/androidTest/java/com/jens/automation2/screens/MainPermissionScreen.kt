package com.jens.automation2.screens

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.jens.automation2.R
import com.jens.automation2.matchers.actionClickOnGPByText
import org.hamcrest.Matcher

class MainPermissionScreen {
    private val requestPermissions: Matcher<View> = withId(R.id.bRequestPermissions)

    fun allowMainPermissions() {
        onView(requestPermissions).perform(click())
        actionClickOnGPByText("ALLOW")
        actionClickOnGPByText("Yes")
    }
}
