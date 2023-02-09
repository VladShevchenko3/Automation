package com.jens.automationKakao.screens.rules.newRules

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
    private val saveBtn = KButton { withId(R.id.cmdSaveRule) }
    private val addTriggerBtn = KButton { withId(R.id.cmdTriggerAdd) }
    private val addActionBtn = KButton { withId(R.id.cmdActionAdd) }
    private val deleteBtn = KButton{withText("delete")}
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
        addTriggerBtn {
            isClickable()
            click()
        }
    }

    fun actionClickAddActionBtn() {
        addActionBtn {
            click()
        }
    }

    fun actionClickOnSaveBtn() {
        saveBtn {
            click()
        }
    }

    fun actionDeleteItemInTriggerListByPosition(position: Int){
        triggerList{
            childAt<Item>(position){
               longClick()
            }
        }
        deleteBtn{
            click()
        }
    }

    fun actionDoubleClickOnActionItemByPosition(position: Int) {
        actionList {
            childAt<Item>(position) {
                doubleClick()
            }
        }
    }

    fun assertSizeOfTriggerList(size: Int) {
        triggerList {
            hasSize(size)
        }
    }
    fun assertTriggerItem(position: Int) {
        triggerList {
            childAt<Item>(position) {
                //TODO()
            }
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