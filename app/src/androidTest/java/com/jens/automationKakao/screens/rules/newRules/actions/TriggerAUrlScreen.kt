package com.jens.automationKakao.screens.rules.newRules.actions

import androidx.test.espresso.Espresso
import com.jens.automation2.R
import io.github.kakaocup.kakao.check.KCheckBox
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton

class TriggerAUrlScreen : Screen<TriggerAUrlScreen>() {

    private val urlTrigger = KEditText { withId(R.id.etTriggerUrl) }
    private val useAuthenticationChk =
        KCheckBox { withId(R.id.chkTriggerUrlUseAuthentication) }
    private val urlUserName = KEditText { withId(R.id.etTriggerUrlUsername) }
    private val urlPassword = KEditText { withId(R.id.etTriggerUrlPassword) }
    private val saveBtn = KButton { withId(R.id.bSaveSpeakText) }

    fun actionEditUrlTrigger(text: String) {
        urlTrigger {
            clearText()
            typeText(text)
            Espresso.closeSoftKeyboard()
        }
    }

    fun actionCheckUseAuthentication() {
        useAuthenticationChk {
            click()
        }
    }

    fun actionEditUserName(text: String) {
        urlUserName {
            clearText()
            typeText(text)
            Espresso.closeSoftKeyboard()
        }
    }

    fun actionEditPassword(text: String) {
        urlPassword {
            clearText()
            typeText(text)
            Espresso.closeSoftKeyboard()
        }
    }

    fun actionClickOnSaveBtn() {
        saveBtn {
            scrollTo()
            click()
        }
    }

    fun assertUrlTriggerName(text: String) {
        urlTrigger {
            hasText(text)
        }
    }

    fun assertUserName(text: String) {
        urlUserName {
            hasText(text)
        }
    }

    fun assertPassword(text: String) {
        urlTrigger {
            hasText(text)
        }
    }
}