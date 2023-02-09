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
    private val cloneRuleView = KTextView { withText(R.string.clone) }
    private val editRuleView = KTextView { withText(R.string.edit) }

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

    fun actionDeleteTheRuleByPosition(position: Int) {
        ruleList {
            childAt<RuleItem>(position) {
                name {
                    longClick()
                }
            }
        }
        deleteRuleView {
            click()
        }
    }

    fun actionCloneTheRuleByPosition(position: Int) {
        ruleList {
            childAt<RuleItem>(position) {
                name {
                    longClick()
                }
            }
        }
        cloneRuleView {
            click()
        }
    }

    fun actionEditTheRuleByPosition(position: Int) {
        ruleList {
            childAt<RuleItem>(position) {
                name {
                    longClick()
                }
            }
        }
        editRuleView {
            click()
        }
    }

    fun assertTheRuleIsDisplayedByPosition(position: Int, nameRule: String) {
        ruleList {
            childAt<RuleItem>(position) {
                name {
                    hasText(nameRule)
                }
            }
        }
    }

    fun assertTheRuleStatusIsActiveByPosition(position: Int) {
        ruleList {
            childAt<RuleItem>(position) {
                image {
                    hasDrawable(R.drawable.status_active)
                }
            }
        }
    }

    fun assertTheRuleStatusIsInactiveByPosition(position: Int) {
        ruleList {
            childAt<RuleItem>(position) {
                image {
                    hasDrawable(R.drawable.status_inactive)
                }
            }
        }
    }

    fun assertTheRuleStatusIsUnableByPosition(position: Int) {
        ruleList {
            childAt<RuleItem>(position) {
                image {
                    hasDrawable(R.drawable.status_unable)
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