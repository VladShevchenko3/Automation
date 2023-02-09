package com.jens.automationKakao.screens.profilesScreen

import com.jens.automation2.R
import com.jens.automationKakao.screens.WithTabs
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

class ProfilesScreen : Screen<ProfilesScreen>(), WithTabs {

    private val addProfileBtn = KButton { withId(R.id.bAddProfile) }
    private val deleteProfileView = KTextView { withText(R.string.deleteCapital) }
    private fun profileNameView(profileName: String) = KTextView { withText(profileName) }

    fun actionClickOnAddProfile() {
        addProfileBtn {
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

    fun assertProfileIsDisplayed(profileName: String) {
        profileNameView(profileName).invoke {
            isVisible()
        }
    }

    fun assertProfileIsNotDisplayed(profileName: String) {
        profileNameView(profileName).invoke {
            doesNotExist()
        }
    }
}