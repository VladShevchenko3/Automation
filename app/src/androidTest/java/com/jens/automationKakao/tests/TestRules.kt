package com.jens.automationKakao.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jens.automationKakao.screens.rulesScreen.CreateNewRuleScreen
import com.jens.automationKakao.screens.rulesScreen.RulesScreen
import com.jens.automationKakao.screens.rulesScreen.TypeOfActionScreen
import com.jens.automationKakao.screens.rulesScreen.TypeOfTriggerScreen
import com.jens.automationKakao.tests.DataForTests.Companion.ACTION_PERCENT_SCREEN_BRIGHTNESS
import com.jens.automationKakao.tests.DataForTests.Companion.RULE_NAME
import com.jens.automationKakao.tests.DataForTests.Companion.TRIGGER_HEADSET_STATUS
import com.jens.automationKakao.tests.DataForTests.Companion.TRIGGER_HEADSET_TYPE
import com.jens.automationKakao.tests.DataForTests.Companion.TRIGGER_WIFI_NAME
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestRules : BaseUITests {

    private val rulesScreen = RulesScreen()
    private val createNewRuleScreen = CreateNewRuleScreen()
    private val typeOfTrigger = TypeOfTriggerScreen()
    private val typeOfAction = TypeOfActionScreen()

    /*
*  Test 1. Scenario
* Step 1. Open the Rules;
* Step 2. Tap 'Add rule';
* Step 3. Enter the name of the rule;
* Step 4. Tap 'add trigger' (Wifi connection);
* Step 5. Find and check the added trigger;
* Step 6. Tap 'add trigger' (Headset connection, status - connected, type - Headphone);
* Step 7. Find and check the added action;
* Step 8. Tap 'Add action' (Set Screen brightness);
* Step 9. Save the rule;
* Step 10. Find and check the added rule;
* Step 11. Delete and check the added rule;
* */
    @Test
    fun checkAddingTheRule() {
        rulesScreen.actionOpenRules()
        rulesScreen.actionClickOnAddRuleButton()
        createNewRuleScreen.actionTypeRuleName(RULE_NAME)
        createNewRuleScreen.actionClickAddTriggerButton()
        typeOfTrigger.actionAddWifiConnectionTrigger(TRIGGER_WIFI_NAME)
        createNewRuleScreen.actionClickAddTriggerButton()
        typeOfTrigger.actionAddHeadsetConnection(TRIGGER_HEADSET_STATUS, TRIGGER_HEADSET_TYPE)
        createNewRuleScreen.actionClickAddActionButton()
        typeOfAction.actionAddScreenBrightness(ACTION_PERCENT_SCREEN_BRIGHTNESS)
        createNewRuleScreen.actionClickOnSaveRuleButton()
        rulesScreen.assertionRuleIsDisplayed(RULE_NAME)
        rulesScreen.actionDeleteRuleByName(RULE_NAME)
        rulesScreen.assertionRuleIsNotDisplayed(RULE_NAME)
    }
}