package com.jens.automationKakao.screens.locationsScreen

import com.jens.automation2.R
import com.jens.automationKakao.screens.WithTabs
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

class LocationsScreen : Screen<LocationsScreen>(), WithTabs {

    private val addLocationBtn = KButton { withId(R.id.bAddPoi) }
    private val deleteLocationView = KTextView { withText(R.string.deleteCapital) }
    private fun locationNameView(locationName: String) = KTextView { withText(locationName) }

    fun actionClickOnAddLocationBtn() {
        addLocationBtn {
            click()
        }
    }

    fun actionDeleteLocationByName(locationName: String) {
        locationNameView(locationName).invoke {
            longClick()
        }
        deleteLocationView {
            isVisible()
            click()
        }
    }

    fun assertLocationIsDisplayed(locationName: String) {
        locationNameView(locationName).invoke {
            isVisible()
        }
    }

    fun assertLocationIsNotDisplayed(locationName: String) {
        locationNameView(locationName).invoke {
            doesNotExist()
        }
    }
}