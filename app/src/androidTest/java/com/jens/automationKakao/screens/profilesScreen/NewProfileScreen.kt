package com.jens.automationKakao.screens.profilesScreen

import androidx.test.espresso.Espresso
import com.jens.automation2.R
import io.github.kakaocup.kakao.check.KCheckBox
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.progress.KSeekBar
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

class NewProfileScreen : Screen<NewProfileScreen>() {

    private val profileNameTextField = KEditText { withId(R.id.etName) }
    private val changeSoundModeChk = KCheckBox { withId(R.id.checkBoxChangeSoundMode) }
    private val soundModeSpinner = KTextView { withId(R.id.spinnerSoundMode) }
    private val changeVolumeNotificationsChk = KCheckBox {
        withId(R.id.checkBoxChangeVolumeNotifications)
    }
    private val volumeNotificationsSeekBar = KSeekBar { withId(R.id.seekBarVolumeNotifications) }
    private val saveProfileBtn = KButton { withId(R.id.bSaveProfile) }
    private fun soundModeView(soundMode: String) = KTextView { withText(soundMode) }

    fun actionEditProfileName(profileName: String) {
        profileNameTextField {
            typeText(profileName)
            Espresso.closeSoftKeyboard()
        }
    }

    fun actionChangeSoundMode(soundMode: String) {
        changeSoundModeChk {
            click()
        }
        soundModeSpinner {
            click()
        }
        soundModeView(soundMode).invoke {
            click()
        }
    }

    fun actionChangeVolumeNotifications(progressVolume: Int) {
        changeVolumeNotificationsChk {
            scrollTo()
            click()
        }
        volumeNotificationsSeekBar {
            scrollTo()
            setProgress(progressVolume)
        }
    }

    fun actionCLickOnSaveBtn() {
        saveProfileBtn {
            scrollTo()
            isVisible()
            click()
        }
    }
}