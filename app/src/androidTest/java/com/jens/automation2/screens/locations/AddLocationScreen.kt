package com.jens.automation2.screens.locations

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.jens.automation2.R
import com.jens.automation2.espresso.typeText
import com.jens.automation2.matchers.waitForView
import org.hamcrest.Matcher

class AddLocationScreen {

    private val locationNameTextField: Matcher<View> = withId(R.id.etPoiName)
    private val getCurrentLocationButton: Matcher<View> = withId(R.id.bGetPosition)
    private val saveLocationButton: Matcher<View> = withId(R.id.bSavePoi)

    fun actionTypeTextOnLocationName(locationName: String) {
        typeText(locationNameTextField, locationName)
    }

    fun actionGetCurrentLocation() {
        onView(getCurrentLocationButton).perform(click())
        onView(withText("Ok")).perform(click())
        waitForView(withText("Yes"), 8_000L, 100L).perform(click())
    }

    fun actionCLickOnSaveLocationButton() {
        onView(saveLocationButton).perform(click())
    }
}
