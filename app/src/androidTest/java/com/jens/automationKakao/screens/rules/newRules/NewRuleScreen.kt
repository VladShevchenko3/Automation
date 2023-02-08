package com.jens.automationKakao.screens.rules.newRules

import android.util.Log
import androidx.test.espresso.DataInteraction
import androidx.test.espresso.Espresso
import com.jens.automation2.R
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.list.KAbsListView
import io.github.kakaocup.kakao.list.KAdapterItem
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton

class NewRuleScreen : Screen<NewRuleScreen>() {

    private val ruleNameTextField = KEditText { withId(R.id.etRuleName) }
    private val saveRuleButton = KButton { withId(R.id.cmdSaveRule) }
    private val addTriggerButton = KButton { withId(R.id.cmdTriggerAdd) }
    private val addActionButton = KButton { withId(R.id.cmdActionAdd) }
    private val actionList = KAbsListView(
        builder = { withId(R.id.lvActionListView) },
        itemTypeBuilder = { itemType(::Item) })
    private val triggerList = KAbsListView(
        builder = { withId(R.id.lvTriggerListView) },
        itemTypeBuilder = { itemType(::Item) })

    class Item(i: DataInteraction) : KAdapterItem<Item>(i) {
        val title = String
    }

    fun actionTypeRuleName(ruleName: String) {
        ruleNameTextField {
            typeText(ruleName)
            Espresso.closeSoftKeyboard()
        }
    }

    fun actionClickAddTriggerBtn() {
        addTriggerButton {
            isClickable()
            click()
        }
    }

    fun actionClickAddActionBtn() {
        addActionButton {
            click()
        }
    }

    fun actionClickOnSaveRuleButton() {
        saveRuleButton {
            click()
        }
    }

    fun actionDoubleClickOnActionItem(position: Int) {
        actionList {
            childAt<Item>(position) {
                doubleClick()
            }
        }
    }

    fun assertTriggerItem(position: Int) {
        triggerList {
            childAt<Item>(position) {
                //TODO()
            }
        }
    }

    fun assertSizeOfTriggerList(size: Int) {
        triggerList {
            hasSize(size)
        }
    }

    fun assertSizeOfActionList(size: Int) {
        actionList {
            hasSize(size)
        }
    }

    fun assertActionItem(position: Int, title1: String) {
        actionList {
            childAt<Item>(position) {
             //TODO()
            }
        }
    }
}