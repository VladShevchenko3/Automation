package com.jens.automationKakao.screens.rulesScreen

import androidx.test.espresso.Espresso
import com.jens.automation2.R
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton

class CreateNewRuleScreen : Screen<CreateNewRuleScreen>() {

    private val ruleNameTextField = KEditText { withId(R.id.etRuleName) }
    private val saveRuleButton = KButton { withId(R.id.cmdSaveRule) }
    private val addTriggerButton = KButton { withId(R.id.cmdTriggerAdd) }
    private val addActionButton = KButton { withId(R.id.cmdActionAdd) }

    fun actionTypeRuleName(ruleName: String) {
        ruleNameTextField {
            typeText(ruleName)
            Espresso.closeSoftKeyboard()
        }
    }

    fun actionClickAddTriggerButton() {
        addTriggerButton {
            isClickable()
            click()
        }
    }

    fun actionClickAddActionButton() {
        addActionButton {
            click()
        }
    }

    fun actionClickOnSaveRuleButton() {
        saveRuleButton {
            click()
        }
    }
}