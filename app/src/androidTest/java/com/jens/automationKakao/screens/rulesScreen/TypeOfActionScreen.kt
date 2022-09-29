package com.jens.automationKakao.screens.rulesScreen

import androidx.test.espresso.action.ViewActions.click
import com.jens.automation2.R
import com.jens.automationKakao.espresso.findElementInList
import io.github.kakaocup.kakao.progress.KSeekBar
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton

enum class TypeOfAction(val title: String) {
    SetScreenBrightness("Set screen brightness")
}

class TypeOfActionScreen : Screen<TypeOfActionScreen>() {

    private val screenBrightnessSeekBar = KSeekBar { withId(R.id.sbBrightness) }
    private val screenBrightnessSaveButton = KButton { withId(R.id.bApplyBrightness) }

    fun actionAddScreenBrightness(progressScreenBrightness: Int) {
        findElementInList(TypeOfAction.SetScreenBrightness.title).perform(click())
        screenBrightnessSeekBar {
            isVisible()
            setProgress(progressScreenBrightness)
            hasProgress(progressScreenBrightness)
        }
        screenBrightnessSaveButton {
            isVisible()
            click()
        }
    }
}
