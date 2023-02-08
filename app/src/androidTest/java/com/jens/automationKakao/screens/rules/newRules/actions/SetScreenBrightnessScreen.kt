package com.jens.automationKakao.screens.rules.newRules.actions

import com.jens.automation2.R
import io.github.kakaocup.kakao.progress.KSeekBar
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton

class SetScreenBrightnessScreen : Screen<SetScreenBrightnessScreen>() {

    private val screenBrightnessSeekBar = KSeekBar { withId(R.id.sbBrightness) }
    private val applyBtn = KButton { withId(R.id.bApplyBrightness) }


    fun actionSetProgressOnSeekBar(progress: Int) {
        screenBrightnessSeekBar {
            setProgress(progress)
            hasProgress(progress)
        }
    }

    fun actionClickOnApplyButton() {
        applyBtn {
            click()
        }
    }

    fun assertProgressPercent(progress: Int) {
        screenBrightnessSeekBar {
            hasProgress(progress)
        }
    }
}