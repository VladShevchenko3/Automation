package com.jens.automationKakao.screens.rulesScreen.actionsScreens

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.click
import com.jens.automation2.R
import com.jens.automationKakao.matches.actionItemWithName
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.progress.KSeekBar
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton

class SetScreenBrightnessScreen : Screen<SetScreenBrightnessScreen>() {

    private val setScreenBrightnessView = KView { withText(R.string.setScreenBrightness) }
    private val setScreenBrightnessString = "Set screen brightness"
    private val screenBrightnessSeekBar = KSeekBar { withId(R.id.sbBrightness) }
    private val applyButton = KButton { withId(R.id.bApplyBrightness) }

    fun actionClickOnSetScreenBrightness() {
        // Need to remodel this feature by Kview
        onData(actionItemWithName(setScreenBrightnessString)).perform(click())
    }

    fun actionSetProgressOnSeekBar(progressScreenBrightness: Int) {
        screenBrightnessSeekBar {
            setProgress(progressScreenBrightness)
            hasProgress(progressScreenBrightness)
        }
    }

    fun actionClickOnApplyButton() {
        applyButton {
            click()
        }
    }
}