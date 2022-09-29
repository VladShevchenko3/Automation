package com.jens.automation2.screens.rules

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.jens.automation2.R
import com.jens.automation2.espresso.editTextField
import com.jens.automation2.espresso.findElementInList
import org.hamcrest.Matcher

enum class TypeOfTrigger(val title: String) {
    WifiConnection("Wifi connection"),
    HeadsetConnection("Headset connection")
}

object TypeOfTriggerScreen {

    private val wifiTitleText: Matcher<View> = withId(R.id.etTriggerWifiName)
    private val wifiSaveButton: Matcher<View> = withId(R.id.bTriggerWifiSave)
    fun actionAddWifiConnectionTrigger(wifiName: String) {
        findElementInList(TypeOfTrigger.WifiConnection.title).perform(click())
        editTextField(wifiTitleText, wifiName)
        onView(wifiSaveButton).perform(click())
    }

    fun actionAddHeadsetConnection(headsetStatus: String, headsetType: String) {
        findElementInList(TypeOfTrigger.HeadsetConnection.title).perform(click())
        onView(withText(headsetStatus)).perform(click())
        onView(withText(headsetType)).perform(click())
    }
}