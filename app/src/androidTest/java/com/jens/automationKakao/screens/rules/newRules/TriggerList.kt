package com.jens.automationKakao.screens.rules.newRules

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.click
import com.jens.automationKakao.matches.actionItemWithName
import io.github.kakaocup.kakao.screen.Screen

class TriggerList : Screen<TriggerList>() {

    private val headsetConnectionString = "Headset connection"
    private val wifiConnectionString = "Wifi connection"

    fun actionClickOnTheHeadsetConnection() {
        onData(actionItemWithName(headsetConnectionString)).perform(click())
    }

    fun actionClickOnTheWifiConnection() {
        onData(actionItemWithName(wifiConnectionString)).perform(click())
    }
}