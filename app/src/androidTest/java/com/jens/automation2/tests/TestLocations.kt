package com.jens.automation2.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.jens.automation2.ActivityMainTabLayout
import com.jens.automation2.screens.locations.AddLocationScreen
import com.jens.automation2.screens.locations.LocationsScreen
import com.jens.automation2.tests.DataForTests.LOCATION_NAME
import org.junit.Rule
import org.junit.Test


class TestLocations {

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
        with(LocationsScreen) {
            actionOpenLocation()
            actionClickOnAddLocationButton()
        }
        with(AddLocationScreen) {
            actionTypeTextOnLocationName(LOCATION_NAME)
            actionGetCurrentLocation()
            actionCLickOnSaveLocationButton()
        }
        with(LocationsScreen) {
            assertionLocationIsDisplayed(LOCATION_NAME)
            actionDeleteLocationByName(LOCATION_NAME)
            assertionLocationIsNotDisplayed(LOCATION_NAME)
        }
    }
}