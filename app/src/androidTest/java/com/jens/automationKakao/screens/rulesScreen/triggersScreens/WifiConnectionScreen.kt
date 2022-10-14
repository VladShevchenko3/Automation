package com.jens.automationKakao.screens.rulesScreen.triggersScreens

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.click
import com.jens.automation2.R
import com.jens.automationKakao.matches.actionItemWithName
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton

class WifiConnectionScreen : Screen<WifiConnectionScreen>() {

    private val wifiConnectionString = "Wifi connection"
    private val wifiTitleText = KEditText { withId(R.id.etTriggerWifiName) }
    private val wifiSaveButton = KButton { withId(R.id.bTriggerWifiSave) }

    fun actionClickOnTheWifiConnection() {
        // Need to remodel this feature by Kview
        onData(actionItemWithName(wifiConnectionString)).perform(click())
    }

    fun actionTypeWifiName(wifiName: String) {
        wifiTitleText {
            typeText(wifiName)
            Espresso.closeSoftKeyboard()
        }
    }

    fun actionClickOnSaveWifiButton() {
        wifiSaveButton {
            click()
        }
    }
}