package com.jens.automation2.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jens.automation2.ActivityMainTabLayout
import com.jens.automation2.screens.rules.AddRuleScreen
import com.jens.automation2.screens.rules.RulesScreen
import com.jens.automation2.screens.rules.TypeOfActionScreen
import com.jens.automation2.screens.rules.TypeOfTriggerScreen
import com.jens.automation2.tests.DataForTests.ACTION_PERCENT_SCREEN_BRIGHTNESS
import com.jens.automation2.tests.DataForTests.ACTION_USB_DESCRIPTION
import com.jens.automation2.tests.DataForTests.ACTION_USB_STATUS
import com.jens.automation2.tests.DataForTests.RULE_NAME
import com.jens.automation2.tests.DataForTests.TRIGGER_HEADSET_DESCRIPTION
import com.jens.automation2.tests.DataForTests.TRIGGER_HEADSET_STATUS
import com.jens.automation2.tests.DataForTests.TRIGGER_HEADSET_TYPE
import com.jens.automation2.tests.DataForTests.TRIGGER_WIFI_DESCRIPTION
import com.jens.automation2.tests.DataForTests.TRIGGER_WIFI_NAME
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestRules {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<ActivityMainTabLayout>()

    /*
    *  Test 1. Scenario
    * Step 1. Open the Rules;
    * Step 2. Tap 'Add rule';
    * Step 3. Enter the name of the rule;
    * Step 4. Tap 'add trigger' (Wifi connection);
    * Step 5. Find and check the added trigger;
    * Step 6. Tap 'add trigger' (Headset connection, status - connected, type - Headphone);
    * Step 7. Find and check the added trigger;
    * Step 8. Tap 'Add action' (USB Tethering, status - Activate);
    * Step 9. Find and check the added action;
    * Step 10. Tap 'Add action' (Set Screen brightness);
    * Step 11. Find and check the added action;
    * Step 12. Save the rule;
    * Step 13. Find and check the added rule;
    * Step 14. Delete and check the added rule;
    * */
    @Test
    fun checkAddingARule() {
        with(RulesScreen) {
            actionOpenRules()
            actionClickOnAddRuleButton()
        }
        with(AddRuleScreen) {
            actionTypeRuleName(RULE_NAME)
            actionClickAddTriggerButton()
        }
        with(TypeOfTriggerScreen) {
            actionAddWifiConnectionTrigger(TRIGGER_WIFI_NAME)
        }
        with(AddRuleScreen) {
            assertionTriggerIsDisplayed(TRIGGER_WIFI_DESCRIPTION)
            actionClickAddTriggerButton()
        }
        with(TypeOfTriggerScreen) {
            actionAddHeadsetConnection(
                TRIGGER_HEADSET_STATUS,
                TRIGGER_HEADSET_TYPE
            )
        }
        with(AddRuleScreen) {
            assertionTriggerIsDisplayed(TRIGGER_HEADSET_DESCRIPTION)
            actionClickAddActionButton()
        }
        with(TypeOfActionScreen) {
            actionAddUsbTethering(ACTION_USB_STATUS)
        }
        with(AddRuleScreen) {
            assertionActionIsDisplayed(ACTION_USB_DESCRIPTION)
            actionClickAddActionButton()
        }
        with(TypeOfActionScreen) {
            actionAddScreenBrightness(ACTION_PERCENT_SCREEN_BRIGHTNESS)
        }
        with(AddRuleScreen) {
            actionClickOnSaveRuleButton()
        }
        with(RulesScreen) {
            assertionRuleIsDisplayed(RULE_NAME)
            actionDeleteRuleByName(RULE_NAME)
            assertionRuleIsNotDisplayed(RULE_NAME)
        }
    }
}