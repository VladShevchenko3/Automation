package com.jens.automationKakao.screens.rulesScreen

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import com.jens.automation2.R
import com.jens.automationKakao.espresso.findElementInList
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton

enum class TypeOfTrigger(val title: String) {
    WifiConnection("Wifi connection"),
    HeadsetConnection("Headset connection")
}

enum class HeadsetStatus {
    connected,
    disconnected
}

class TypeOfTriggerScreen : Screen<TypeOfTriggerScreen>() {
    private val wifiTitleText = KEditText { withId(R.id.etTriggerWifiName) }
    private val wifiSaveButton = KButton { withId(R.id.bTriggerWifiSave) }
    private val connectedView = KView { withText(R.string.connected) }
    private val disconnectedView = KView { withText(R.string.disconnected) }

    fun actionAddWifiConnectionTrigger(wifiName: String) {
        findElementInList(TypeOfTrigger.WifiConnection.title).perform(click())
        wifiTitleText {
            isVisible()
            typeText(wifiName)
            Espresso.closeSoftKeyboard()
        }
        wifiSaveButton {
            isVisible()
            click()
        }
    }

    fun actionAddHeadsetConnection(headsetStatus: String, headsetType: String) {
        findElementInList(TypeOfTrigger.HeadsetConnection.title).perform(click())
        if (headsetStatus == HeadsetStatus.connected.toString()) {
            connectedView {
                isVisible()
                click()
            }
        } else
            if (headsetStatus == HeadsetStatus.disconnected.toString()) {
                disconnectedView {
                    isVisible()
                    click()
                }
            }
        val headsetTypeView = KView { withText(headsetType) }
        headsetTypeView {
            isVisible()
            click()
        }
    }
}