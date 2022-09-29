package com.jens.automationKakao.screens.locationsScreen

import com.jens.automation2.R
import com.jens.automationKakao.screens.WithTabs
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

class LocationsScreen : Screen<LocationsScreen>(), WithTabs {
    private val addLocationButton = KButton { withId(R.id.bAddPoi) }
    private val deleteLocationView = KView { withText(R.string.deleteCapital) }

    fun actionClickOnAddLocationButton() {
        addLocationButton {
            isVisible()
            click()
        }
    }

    fun actionDeleteLocationByName(locationName: String) {
        val locationNameView = KTextView { withText(locationName) }
        locationNameView {
            isVisible()
            longClick()
        }
        deleteLocationView {
            isVisible()
            click()
        }
    }

    fun assertionLocationIsDisplayed(locationName: String) {
        val locationNameView = KTextView { withText(locationName) }
        locationNameView {
            isVisible()
        }
    }

    fun assertionLocationIsNotDisplayed(locationName: String) {
        val locationNameView = KTextView { withText(locationName) }
        locationNameView {
            doesNotExist()
        }
    }
}