package com.jens.automation2.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jens.automation2.ActivityMainTabLayout
import com.jens.automation2.screens.profiles.AddProfileScreen
import com.jens.automation2.screens.profiles.ProfilesScreen
import com.jens.automation2.tests.DataForTests.LEVEL_VOLUME_NOTIFICATIONS
import com.jens.automation2.tests.DataForTests.PROFILE_NAME
import com.jens.automation2.tests.DataForTests.SOUND_MODE
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestProfiles {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<ActivityMainTabLayout>()

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
        with(ProfilesScreen) {
            actionOpenProfiles()
            actionClickOnAddProfile()
        }
        with(AddProfileScreen) {
            actionTypeProfileName(PROFILE_NAME)
            actionChangeSoundMode(SOUND_MODE)
            actionChangeVolumeNotifications(LEVEL_VOLUME_NOTIFICATIONS)
            actionCLickOnSaveProfileButton()
        }
        with(ProfilesScreen) {
            assertionProfileIsDisplayed(PROFILE_NAME)
            actionDeleteProfileByName(PROFILE_NAME)
            assertionProfileIsNotDisplayed(PROFILE_NAME)
        }
    }
}