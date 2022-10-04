package com.jens.automationKakao.screens.rulesScreen.triggersScreens

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.click
import com.jens.automation2.R
import com.jens.automationKakao.matches.actionItemWithName
import com.jens.automationKakao.matches.actionItemWithNameNew
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KTextView

class HeadsetConnectionScreen : Screen<HeadsetConnectionScreen>() {

    private val headsetConnectionView = KTextView { withText(R.string.triggerHeadsetPlugged) }
    private val headphoneView = KTextView { withText(R.string.headphoneSimple) }
    private val headsetConnectionString = "Headset connection"
    private val connectedView = KTextView { withText(R.string.connected) }

    fun actionClickOnTheHeadsetConnection() {
        // Need to remodel this feature by Kview
        onData(actionItemWithName(headsetConnectionString)).perform(click())
        //onData(actionItemWithNameNew(headsetConnectionView)).perform(click())
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