package com.jens.automationKakao.screens.profilesScreen

import com.jens.automation2.R
import com.jens.automationKakao.screens.WithTabs
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

class ProfilesScreen : Screen<ProfilesScreen>(), WithTabs {

    private val addProfileButton = KButton { withId(R.id.bAddProfile) }
    private val deleteProfileView = KTextView { withText(R.string.deleteCapital) }
    private fun profileNameView(profileName: String) = KTextView { withText(profileName) }

    fun actionClickOnAddProfile() {
        addProfileButton {
            click()
        }
    }

    fun actionDeleteProfileByName(profileName: String) {
        profileNameView(profileName).invoke {
            longClick()
        }
        deleteProfileView {
            click()
        }
    }

    fun assertionProfileIsDisplayed(profileName: String) {
        profileNameView(profileName).invoke {
            isVisible()
        }
    }

    fun assertionProfileIsNotDisplayed(profileName: String) {
        profileNameView(profileName).invoke {
            doesNotExist()
        }
    }
}