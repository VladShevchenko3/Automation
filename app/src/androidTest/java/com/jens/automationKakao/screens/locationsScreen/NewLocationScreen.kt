package com.jens.automationKakao.screens.locationsScreen

import androidx.test.espresso.Espresso
import com.jens.automation2.R
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

class NewLocationScreen : Screen<NewLocationScreen>() {

    private val locationName = KEditText { withId(R.id.etPoiName) }
    private val getCurrentLocationBtn = KButton { withId(R.id.bGetPosition) }
    private val saveBtn = KButton { withId(R.id.bSavePoi) }
    private val yesView = KTextView { withText(R.string.yes) }
    private val okView = KTextView { withText(R.string.ok) }

    fun actionTypeTextOnLocationName(locationName: String) {
        locationName {
            typeText(locationName)
            Espresso.closeSoftKeyboard()
        }
    }

    fun actionGetCurrentLocation() {
        getCurrentLocationBtn {
            click()
        }
        okView {
            click()
        }
        yesView {
            click()
        }
    }

    fun actionCLickOnSaveBtn() {
        saveBtn {
            click()
        }
    }
}