package com.jens.automationKakao.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import com.jens.automationKakao.screens.locationsScreen.AddLocationScreen
import com.jens.automationKakao.screens.locationsScreen.LocationsScreen
import com.jens.automationKakao.tests.DataForTests.Companion.LOCATION_NAME
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestLocations : BaseUITests {

    @get:Rule
    var mRuntimePermissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION)
    private val locationsScreen = LocationsScreen()
    private val addLocationScreen = AddLocationScreen()

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
        locationsScreen.actionOpenLocation()
        locationsScreen.actionClickOnAddLocationButton()
        addLocationScreen.actionTypeTextOnLocationName(LOCATION_NAME)
        addLocationScreen.actionGetCurrentLocation()
        addLocationScreen.actionCLickOnSaveLocationButton()
        locationsScreen.assertionLocationIsDisplayed(LOCATION_NAME)
        locationsScreen.actionDeleteLocationByName(LOCATION_NAME)
        locationsScreen.assertionLocationIsNotDisplayed(LOCATION_NAME)
    }
}