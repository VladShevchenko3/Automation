package com.jens.automationKakao.screens.rules.newRules

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.click
import com.jens.automationKakao.matches.actionItemWithName
import io.github.kakaocup.kakao.screen.Screen

class ActionList : Screen<ActionList>() {

    private val setScreenBrightnessString = "Set screen brightness"
    private val triggerAUrlString = "Trigger a URL"

    fun actionClickOnSetScreenBrightness() {
        onData(actionItemWithName(setScreenBrightnessString)).perform(click())
    }

    fun actionClickOnTriggerAUrl() {
        onData(actionItemWithName(triggerAUrlString)).perform(click())
    }
}