package com.jens.automationKakao.screens.rules.newRules.triggers

import androidx.test.espresso.Espresso
import com.jens.automation2.R
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton

class WifiConnectionScreen : Screen<WifiConnectionScreen>() {

    private val wifiTitle = KEditText { withId(R.id.etTriggerWifiName) }
    private val saveBtn = KButton { withId(R.id.bTriggerWifiSave) }

    fun actionTypeWifiName(text: String) {
        wifiTitle {
            clearText()
            typeText(text)
            Espresso.closeSoftKeyboard()
        }
    }

    fun actionClickOnSaveBtn() {
        saveBtn {
            click()
        }
    }
}