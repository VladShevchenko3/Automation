package com.jens.automation2.screens.rules

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.jens.automation2.R
import com.jens.automation2.espresso.findElementInList
import com.jens.automation2.matchers.actionWithSeekBar
import org.hamcrest.Matcher

enum class TypeOfAction(val title: String) {
    UsbTethering("USB Tethering"),
    SetScreenBrightness("Set screen brightness")
}

class TypeOfActionScreen {

    private val screenBrightnessSeekBar: Matcher<View> = withId(R.id.sbBrightness)
    private val screenBrightnessSaveButton: Matcher<View> = withId(R.id.bApplyBrightness)

    fun actionAddUsbTethering(usbStatus: String) {
        findElementInList(TypeOfAction.UsbTethering.title).perform(click())
        onView(withText("Ok")).perform(click())
        onView(withText(usbStatus)).perform(click())
    }

    fun actionAddScreenBrightness(progressScreenBrightness: Int) {
        findElementInList(TypeOfAction.SetScreenBrightness.title).perform(click())
        onView(screenBrightnessSeekBar).perform(actionWithSeekBar(progressScreenBrightness))
        onView(screenBrightnessSaveButton).perform(click())
    }
}
