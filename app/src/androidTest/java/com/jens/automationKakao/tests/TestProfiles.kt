package com.jens.automationKakao.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jens.automation2.ActivityMainTabLayout
import com.jens.automationKakao.screens.profilesScreen.AddProfileScreen
import com.jens.automationKakao.screens.profilesScreen.ProfilesScreen
import com.jens.automationKakao.tests.DataForTests.Companion.LEVEL_VOLUME_NOTIFICATIONS
import com.jens.automationKakao.tests.DataForTests.Companion.PROFILE_NAME
import com.jens.automationKakao.tests.DataForTests.Companion.SOUND_MODE
import io.github.kakaocup.kakao.screen.Screen.Companion.onScreen
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
        onScreen<ProfilesScreen> {
            actionOpenProfiles()
            actionClickOnAddProfile()
        }
        onScreen<AddProfileScreen> {
            actionTypeProfileName(PROFILE_NAME)
            actionChangeSoundMode(SOUND_MODE)
            actionChangeVolumeNotifications(LEVEL_VOLUME_NOTIFICATIONS)
            actionCLickOnSaveProfileButton()
        }
        onScreen<ProfilesScreen> {
            assertionProfileIsDisplayed(PROFILE_NAME)
            actionDeleteProfileByName(PROFILE_NAME)
            assertionProfileIsNotDisplayed(PROFILE_NAME)
        }
    }
}