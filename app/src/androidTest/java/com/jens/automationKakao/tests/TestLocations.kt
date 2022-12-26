package com.jens.automationKakao.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.jens.automation2.ActivityMainTabLayout
import com.jens.automationKakao.screens.locationsScreen.AddLocationScreen
import com.jens.automationKakao.screens.locationsScreen.LocationsScreen
import com.jens.automationKakao.tests.DataForTests.Companion.LOCATION_NAME
import io.github.kakaocup.kakao.screen.Screen.Companion.onScreen
import org.junit.Rule
import org.junit.Test


class TestLocations : BaseUITests {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<ActivityMainTabLayout>()

    @get:Rule
    var mRuntimePermissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION)

    /*
     * Scenario
     * Step 1. Open Location tab;
     * Step 2. Click 'Add location';
     * Step 3. Enter Location name;
     * Step 4. Click 'Get current location';
     * Step 5. Save location;
     * Step 6. Find and check the added Location;
     * Step 7. Delete location;
     */
    @Test
    fun checkAddingALocation() {
        onScreen<LocationsScreen> {
            actionOpenLocation()
            actionClickOnAddLocationButton()
        }
        onScreen<AddLocationScreen> {
            actionTypeTextOnLocationName(LOCATION_NAME)
            actionGetCurrentLocation()
            actionCLickOnSaveLocationButton()
        }
        onScreen<LocationsScreen> {
            assertionLocationIsDisplayed(LOCATION_NAME)
            actionDeleteLocationByName(LOCATION_NAME)
            assertionLocationIsNotDisplayed(LOCATION_NAME)
        }
    }
}