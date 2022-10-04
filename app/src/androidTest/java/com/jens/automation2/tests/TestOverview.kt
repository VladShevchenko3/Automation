package com.jens.automation2.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jens.automation2.ActivityMainTabLayout
import com.jens.automation2.screens.overview.OverviewScreen
import com.jens.automation2.screens.rules.AddRuleScreen
import com.jens.automation2.screens.rules.RulesScreen
import com.jens.automation2.screens.rules.TypeOfActionScreen
import com.jens.automation2.screens.rules.TypeOfTriggerScreen
import com.jens.automation2.tests.DataForTests.ACTION_USB_STATUS
import com.jens.automation2.tests.DataForTests.RULE_NAME
import com.jens.automation2.tests.DataForTests.SERVICE_ACTIVATING_MESSAGE
import com.jens.automation2.tests.DataForTests.SERVICE_STARTED_MESSAGE
import com.jens.automation2.tests.DataForTests.SERVICE_STOPPED_MESSAGE
import com.jens.automation2.tests.DataForTests.TRIGGER_WIFI_NAME
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestOverview {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<ActivityMainTabLayout>()

    /* Scenario
    * Step 1. Open the Rules;
    * Step 2. Tap 'Add rule';
    * Step 3. Enter the name of the rule;
    * Step 4. Tap 'add trigger' (Wifi connection)
    * Step 5. Tap 'Add action' (USB Tethering, status - Activate);
    * Step 6. Save the rule;
    * Step 7. Open the Overview;
    * Step 8. Tap service status;
    * Step 9. Check that service status is "ON";
    * Step 10. Tap service status;
    * Step 11. Delete the rule;
    */
    @Test
    fun checkLaunchingService() {
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
            actionClickAddActionButton()
        }
        with(TypeOfActionScreen) {
            actionAddUsbTethering(ACTION_USB_STATUS)
        }
        with(AddRuleScreen) {
            actionClickOnSaveRuleButton()
        }
        with(OverviewScreen) {
            actionOpenOverview()
            actionClickOnServiceButton()
            assertionServiceButtonHasText("ON")
            actionWaitForViewDoesNotExist(SERVICE_STARTED_MESSAGE)
            actionWaitForViewDoesNotExist(SERVICE_ACTIVATING_MESSAGE)
            actionClickOnServiceButton()
            actionWaitForViewDoesNotExist(SERVICE_STOPPED_MESSAGE)
        }
        with(RulesScreen) {
            actionOpenRules()
            actionDeleteRuleByName(RULE_NAME)
            actionDeleteRuleByName(RULE_NAME)
            assertionRuleIsNotDisplayed(RULE_NAME)
        }
    }
}