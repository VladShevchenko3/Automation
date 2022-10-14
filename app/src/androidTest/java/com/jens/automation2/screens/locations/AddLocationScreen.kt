package com.jens.automation2.screens.locations

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.jens.automation2.R
import com.jens.automation2.espresso.editTextField
import com.jens.automation2.matchers.waitForView
import org.hamcrest.Matcher

object AddLocationScreen {

    private val locationNameTextField: Matcher<View> = withId(R.id.etPoiName)
    private val getCurrentLocationButton: Matcher<View> = withId(R.id.bGetPosition)
    private val saveLocationButton: Matcher<View> = withId(R.id.bSavePoi)
    private val yesButton: Matcher<View> = withText(R.string.yes)
    private val okButton: Matcher<View> = withText(R.string.ok)

    fun actionTypeTextOnLocationName(locationName: String) {
        editTextField(locationNameTextField, locationName)
    }

    fun actionGetCurrentLocation() {
        onView(getCurrentLocationButton).perform(click())
        onView(okButton).perform(click())
        waitForView(yesButton, 8_000L, 100L).perform(click())
    }

    fun actionCLickOnSaveLocationButton() {
        onView(saveLocationButton).perform(click())
    }
}