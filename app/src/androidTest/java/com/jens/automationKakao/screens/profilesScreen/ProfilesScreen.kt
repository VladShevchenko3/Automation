package com.jens.automationKakao.screens.profilesScreen

import com.jens.automation2.R
import com.jens.automationKakao.screens.WithTabs
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton

class ProfilesScreen : Screen<ProfilesScreen>(), WithTabs {

    private val addProfileButton = KButton { withId(R.id.bAddProfile) }
    private val deleteProfileView = KView { withText(R.string.deleteCapital) }

    fun actionClickOnAddProfile() {
        addProfileButton {
            isVisible()
            click()
        }
    }

    fun actionDeleteProfileByName(profileName: String) {
        val profileNameView = KView { withText(profileName) }
        profileNameView {
            isVisible()
            longClick()
        }
        deleteProfileView {
            isVisible()
            click()
        }
    }

    fun assertionProfileIsDisplayed(profileName: String) {
        val profileNameView = KView { withText(profileName) }
        profileNameView {
            isVisible()
        }
    }

    fun assertionProfileIsNotDisplayed(profileName: String) {
        val profileNameView = KView { withText(profileName) }
        profileNameView {
            doesNotExist()
        }
    }
}