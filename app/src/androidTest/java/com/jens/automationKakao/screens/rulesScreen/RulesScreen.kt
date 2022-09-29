package com.jens.automationKakao.screens.rulesScreen

import com.jens.automation2.R
import com.jens.automation2.screens.WithTabs
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

class RulesScreen : Screen<RulesScreen>(), WithTabs {

    private val addRuleButton = KButton { withId(R.id.bAddRule) }
    private val deleteRuleView = KView { withText(R.string.deleteCapital) }

    fun actionClickOnAddRuleButton() {
        addRuleButton {
            isVisible()
            click()
        }
    }

    fun actionDeleteRuleByName(nameRule: String) {
        val nameRuleView = KTextView { withText(nameRule) }
        nameRuleView {
            isVisible()
            longClick()
        }
        deleteRuleView {
            isVisible()
            click()
        }
    }

    fun assertionRuleIsDisplayed(nameRule: String) {
        val nameRuleView = KTextView { withText(nameRule) }
        nameRuleView {
            isVisible()
            click()
        }
    }

    fun assertionRuleIsNotDisplayed(nameRule: String) {
        val nameRuleView = KTextView { withText(nameRule) }
        nameRuleView {
            doesNotExist()
        }
    }
}