package com.jens.automationKakao.screens.locationsScreen

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.jens.automation2.R
import com.jens.automation2.matchers.waitForView
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton

class AddLocationScreen : Screen<AddLocationScreen>() {

    private val locationNameTextField = KEditText { withId(R.id.etPoiName) }
    private val getCurrentLocationButton = KButton { withId(R.id.bGetPosition) }
    private val saveLocationButton = KButton { withId(R.id.bSavePoi) }
    private val yesView = KView { withText(R.string.yes) }
    private val okView = KView { withText(R.string.ok) }

    fun actionTypeTextOnLocationName(locationName: String) {
        locationNameTextField {
            isVisible()
            typeText(locationName)
            Espresso.closeSoftKeyboard()
        }
    }

    fun actionGetCurrentLocation() {
        getCurrentLocationButton {
            isVisible()
            click()
        }
        okView {
            isVisible()
            click()
        }
        waitForView(withText("Yes"), 8_000L, 100L).perform(click())
    }

    fun actionCLickOnSaveLocationButton() {
        saveLocationButton {
            isVisible()
            click()
        }
    }
}
