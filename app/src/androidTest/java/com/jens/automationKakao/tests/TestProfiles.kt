package com.jens.automationKakao.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jens.automationKakao.screens.profilesScreen.AddProfileScreen
import com.jens.automationKakao.screens.profilesScreen.ProfilesScreen
import com.jens.automationKakao.tests.DataForTests.Companion.LEVEL_VOLUME_NOTIFICATIONS
import com.jens.automationKakao.tests.DataForTests.Companion.PROFILE_NAME
import com.jens.automationKakao.tests.DataForTests.Companion.SOUND_MODE
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestProfiles : BaseUITests {

    private val profilesScreen = ProfilesScreen()
    private val addProfileScreen = AddProfileScreen()

    /*Test 2. Scenario
  * Step 1. Open the Profiles;
  * Step 2. Tap 'Add profile';
  * Step 3. Enter the name of the rule;
  * Step 4. Change sound mode;
  * Step 5. Change volumes (Ringtone and notifications);
  * Step 6. Save the profile;
  * Step 7. Find and check the added profile;
  * Step 8. Delete and check the added profile;
  * */
    @Test
    fun checkAddingAProfile() {
        profilesScreen.actionOpenProfiles()
        profilesScreen.actionClickOnAddProfile()
        addProfileScreen.actionTypeProfileName(PROFILE_NAME)
        addProfileScreen.actionChangeSoundMode(SOUND_MODE)
        addProfileScreen.actionChangeVolumeNotifications(LEVEL_VOLUME_NOTIFICATIONS)
        addProfileScreen.actionCLickOnSaveProfileButton()
        profilesScreen.assertionProfileIsDisplayed(PROFILE_NAME)
        profilesScreen.actionDeleteProfileByName(PROFILE_NAME)
        profilesScreen.assertionProfileIsNotDisplayed(PROFILE_NAME)
    }
}