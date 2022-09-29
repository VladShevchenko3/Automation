package com.jens.automationKakao.screens.profilesScreen

import androidx.test.espresso.Espresso
import com.jens.automation2.R
import io.github.kakaocup.kakao.check.KCheckBox
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.progress.KSeekBar
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton

class AddProfileScreen : Screen<AddProfileScreen>() {

    private val profileNameTextField = KEditText { withId(R.id.etName) }
    private val changeSoundModeCheckBox = KCheckBox { withId(R.id.checkBoxChangeSoundMode) }
    private val soundModeSpinner = KView { withId(R.id.spinnerSoundMode) }
    private val changeVolumeNotificationsCheckBox = KCheckBox {
        withId(R.id.checkBoxChangeVolumeNotifications)
    }
    private val volumeNotificationsSeekBar = KSeekBar { withId(R.id.seekBarVolumeNotifications) }
    private val saveProfileButton = KButton { withId(R.id.bSaveProfile) }

    fun actionTypeProfileName(profileName: String) {
        profileNameTextField {
            isVisible()
            typeText(profileName)
            Espresso.closeSoftKeyboard()
        }
    }

    fun actionChangeSoundMode(soundMode: String) {
        changeSoundModeCheckBox {
            isVisible()
            click()
        }
        soundModeSpinner {
            isVisible()
            click()
        }
        val soundModeView = KView { withText(soundMode) }
        soundModeView {
            isVisible()
            click()
        }
    }

    fun actionChangeVolumeNotifications(progressVolume: Int) {
        changeVolumeNotificationsCheckBox {
            scrollTo()
            isVisible()
            click()
        }
        volumeNotificationsSeekBar {
            scrollTo()
            isVisible()
            setProgress(progressVolume)
        }
    }

    fun actionCLickOnSaveProfileButton() {
        saveProfileButton {
            scrollTo()
            isVisible()
            click()
        }
    }
}