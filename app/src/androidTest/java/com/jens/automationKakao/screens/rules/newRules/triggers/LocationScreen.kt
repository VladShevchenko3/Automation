package com.jens.automationKakao.screens.rules.newRules.triggers

import com.jens.automation2.R
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KTextView

class LocationScreen : Screen<LocationScreen>() {

    private val enteringView = KTextView { withText(R.string.entering) }
    private val leavingView = KTextView { withText(R.string.leaving) }
    private val noneView = KTextView { withText("< none >") }

    fun actionClickOnEnteringView() {
        enteringView {
            click()
        }
    }

    fun actionClickOnLeavingView() {
        leavingView {
            click()
        }
    }

    fun actionClickOnNoneView() {
        noneView {
            click()
        }
    }

}