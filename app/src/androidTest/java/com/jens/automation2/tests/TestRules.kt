package com.jens.automation2.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jens.automation2.ActivityMainTabLayout
import com.jens.automation2.screens.rules.AddRuleScreen
import com.jens.automation2.screens.rules.RulesScreen
import com.jens.automation2.screens.rules.TypeOfActionScreen
import com.jens.automation2.screens.rules.TypeOfTriggerScreen
import com.jens.automation2.tests.DataForTests.Companion.ACTION_PERCENT_SCREEN_BRIGHTNESS
import com.jens.automation2.tests.DataForTests.Companion.ACTION_USB_DESCRIPTION
import com.jens.automation2.tests.DataForTests.Companion.ACTION_USB_STATUS
import com.jens.automation2.tests.DataForTests.Companion.RULE_NAME
import com.jens.automation2.tests.DataForTests.Companion.TRIGGER_HEADSET_DESCRIPTION
import com.jens.automation2.tests.DataForTests.Companion.TRIGGER_HEADSET_STATUS
import com.jens.automation2.tests.DataForTests.Companion.TRIGGER_HEADSET_TYPE
import com.jens.automation2.tests.DataForTests.Companion.TRIGGER_WIFI_DESCRIPTION
import com.jens.automation2.tests.DataForTests.Companion.TRIGGER_WIFI_NAME
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestRules {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<ActivityMainTabLayout>()

    private val ruleScreen = RulesScreen()
    private val addRuleScreen = AddRuleScreen()
    private val typeOfTriggerScreen = TypeOfTriggerScreen()
    private val typeOfActionScreen = TypeOfActionScreen()

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
//    @Before
//    fun mainPermissionConfirm(){
//        with(MainPermissionScreen()) {
//            allowMainPermissions()
//        }
//    }
    @Test
    fun checkAddingARule() {

        ruleScreen.actionOpenRules()
        ruleScreen.actionClickOnAddRuleButton()
        addRuleScreen.actionTypeRuleName(RULE_NAME)
        addRuleScreen.actionClickAddTriggerButton()
        typeOfTriggerScreen.actionAddWifiConnectionTrigger(TRIGGER_WIFI_NAME)
        addRuleScreen.assertionTriggerIsDisplayed(TRIGGER_WIFI_DESCRIPTION)
        addRuleScreen.actionClickAddTriggerButton()
        typeOfTriggerScreen.actionAddHeadsetConnection(
            TRIGGER_HEADSET_STATUS,
            TRIGGER_HEADSET_TYPE
        )
        addRuleScreen.assertionTriggerIsDisplayed(TRIGGER_HEADSET_DESCRIPTION)
        addRuleScreen.actionClickAddActionButton()
        typeOfActionScreen.actionAddUsbTethering(ACTION_USB_STATUS)
        addRuleScreen.assertionActionIsDisplayed(ACTION_USB_DESCRIPTION)
        addRuleScreen.actionClickAddActionButton()
        typeOfActionScreen.actionAddScreenBrightness(ACTION_PERCENT_SCREEN_BRIGHTNESS)
        addRuleScreen.actionClickOnSaveRuleButton()
        ruleScreen.assertionRuleIsDisplayed(RULE_NAME)
        ruleScreen.actionDeleteRuleByName(RULE_NAME)
        ruleScreen.assertionRuleIsNotDisplayed(RULE_NAME)
    }
}
