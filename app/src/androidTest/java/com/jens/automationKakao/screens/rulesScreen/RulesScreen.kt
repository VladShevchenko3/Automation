package com.jens.automationKakao.screens.rulesScreen

import com.jens.automation2.R
import com.jens.automation2.screens.WithTabs
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

class RulesScreen : Screen<RulesScreen>(), WithTabs {

    private val addRuleButton = KButton { withId(R.id.bAddRule) }
    private val deleteRuleView = KTextView { withText(R.string.deleteCapital) }
    private fun nameRuleView(nameRule: String) = KTextView { withText(nameRule) }

    fun actionClickOnAddRuleButton() {
        addRuleButton {
            click()
        }
    }

    fun actionDeleteRuleByName(nameRule: String) {
        nameRuleView(nameRule).invoke {
            longClick()
        }
        deleteRuleView {
            click()
        }
    }

    fun assertionRuleIsDisplayed(nameRule: String) {
        nameRuleView(nameRule).invoke {
            click()
        }
    }

    fun assertionRuleIsNotDisplayed(nameRule: String) {
        nameRuleView(nameRule).invoke {
            doesNotExist()
        }
    }
}