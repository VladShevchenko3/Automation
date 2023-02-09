package com.jens.automationKakao.screens.rules

import androidx.test.espresso.DataInteraction
import com.jens.automation2.R
import com.jens.automationKakao.screens.WithTabs
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.list.KAbsListView
import io.github.kakaocup.kakao.list.KAdapterItem
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

class RulesScreen : Screen<RulesScreen>(), WithTabs {

    private val addRuleBtn = KButton { withId(R.id.bAddRule) }
    private val deleteRuleView = KTextView { withText(R.string.deleteCapital) }

    private val ruleList = KAbsListView(
        builder = { withId(R.id.lvRuleList) },
        itemTypeBuilder = { itemType(::RuleItem) })

    class RuleItem(i: DataInteraction) : KAdapterItem<RuleItem>(i) {
        val name = KTextView(i) { withId(R.id.tvRuleName) }
        val image = KImageView(i) { withId(R.id.ivActiveInactive) }
    }

    fun actionClickOnAddRuleBtn() {
        addRuleBtn {
            click()
        }
    }

    fun actionDeleteFirstRule() {
        ruleList {
            firstChild<RuleItem> {
                name {
                    longClick()
                }
            }
        }
        deleteRuleView {
            click()
        }
    }

    fun assertFirstRuleIsDisplayed(nameRule: String) {
        ruleList {
            firstChild<RuleItem> {
                name {
                    hasText(nameRule)
                }
            }
        }
    }

    fun assertSizeOfRuleList(size: Int) {
        ruleList {
            hasSize(size)
        }
    }
}