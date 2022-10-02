package com.jens.automationKakao.screens.rulesScreen.triggersScreens

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.click
import com.jens.automation2.R
import com.jens.automationKakao.matches.actionItemWithName
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KTextView

class HeadsetConnectionScreen : Screen<HeadsetConnectionScreen>() {

    private val headsetConnectionView = KView { withText(R.string.triggerHeadsetPlugged) }
    private val headphoneView = KTextView { withText(R.string.headphoneSimple) }
    private val headsetConnectionString = "Headset connection"
    private val connectedView = KTextView { withText(R.string.connected) }

    fun actionClickOnTheHeadsetConnection() {
        // Need to remodel this feature by Kview
        onData(actionItemWithName(headsetConnectionString)).perform(click())
    }

    fun actionClickOnConnectedView() {
        connectedView {
            click()
        }
    }

    fun actionClickOnHeadphoneView() {
        headphoneView {
            click()
        }
    }
}