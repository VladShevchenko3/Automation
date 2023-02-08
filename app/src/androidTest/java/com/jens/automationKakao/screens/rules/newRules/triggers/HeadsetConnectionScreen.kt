package com.jens.automationKakao.screens.rules.newRules.triggers

import com.jens.automation2.R
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KTextView

class HeadsetConnectionScreen : Screen<HeadsetConnectionScreen>() {

    private val headphoneView = KTextView { withText(R.string.headphoneSimple) }
    private val microphoneView = KTextView { withText(R.string.headphoneMicrophone) }
    private val eitherView = KTextView { withText(R.string.headphoneAny) }
    private val connectedView = KTextView { withText(R.string.connected) }
    private val disconnectedView = KTextView { withText(R.string.disconnected) }

    fun actionClickOnConnected() {
        connectedView {
            click()
        }
    }

    fun actionClickOnDisconnectedView() {
        disconnectedView {
            click()
        }
    }

    fun actionClickOnHeadphone() {
        headphoneView {
            click()
        }
    }

    fun actionClickOnMicrophone() {
        microphoneView {
            click()
        }
    }

    fun actionClickOnEither() {
        eitherView {
            click()
        }
    }
}