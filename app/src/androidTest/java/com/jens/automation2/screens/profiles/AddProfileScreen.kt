package com.jens.automation2.screens.profiles

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.jens.automation2.R
import com.jens.automation2.espresso.editTextField
import com.jens.automation2.matchers.actionWithSeekBar
import org.hamcrest.Matcher

object AddProfileScreen {

    private val profileNameTextField: Matcher<View> = withId(R.id.etName)
    private val changeSoundModeCheckBox: Matcher<View> = withId(R.id.checkBoxChangeSoundMode)
    private val soundModeSpinner: Matcher<View> = withId(R.id.spinnerSoundMode)
    private val changeVolumeNotificationsCheckBox: Matcher<View> =
        withId(R.id.checkBoxChangeVolumeNotifications)
    private val volumeNotificationsSeekBar: Matcher<View> = withId(R.id.seekBarVolumeNotifications)
    private val saveProfileButton: Matcher<View> = withId(R.id.bSaveProfile)

    fun actionTypeProfileName(profileName: String) {
        editTextField(profileNameTextField, profileName)
    }

    fun actionChangeSoundMode(soundMode: String) {
        onView(changeSoundModeCheckBox).perform(click())
        onView(soundModeSpinner).perform(click())
        onView(withText(soundMode)).perform(click())
    }

    fun actionChangeVolumeNotifications(progressVolume: Int) {
        onView(changeVolumeNotificationsCheckBox).perform(scrollTo(), click())
        onView(volumeNotificationsSeekBar).perform(
            scrollTo(),
            actionWithSeekBar(progressVolume)
        )
    }

    fun actionCLickOnSaveProfileButton() {
        onView(saveProfileButton).perform(scrollTo(), click())
    }
}