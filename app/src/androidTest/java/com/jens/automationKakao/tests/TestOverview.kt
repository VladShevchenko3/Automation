package com.jens.automationKakao.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jens.automationKakao.screens.overviewScreen.OverviewScreen
import com.jens.automationKakao.screens.rulesScreen.CreateNewRuleScreen
import com.jens.automationKakao.screens.rulesScreen.RulesScreen
import com.jens.automationKakao.screens.rulesScreen.TypeOfActionScreen
import com.jens.automationKakao.screens.rulesScreen.TypeOfTriggerScreen
import com.jens.automationKakao.tests.DataForTests.Companion.ACTION_PERCENT_SCREEN_BRIGHTNESS
import com.jens.automationKakao.tests.DataForTests.Companion.RULE_NAME
import com.jens.automationKakao.tests.DataForTests.Companion.SERVICE_ACTIVATING_MESSAGE
import com.jens.automationKakao.tests.DataForTests.Companion.SERVICE_STARTED_MESSAGE
import com.jens.automationKakao.tests.DataForTests.Companion.SERVICE_STOPPED_MESSAGE
import com.jens.automationKakao.tests.DataForTests.Companion.STATUS_ON
import com.jens.automationKakao.tests.DataForTests.Companion.TRIGGER_WIFI_NAME
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestOverview : BaseUITests {

    private val rulesScreen = RulesScreen()
    private val createNewRuleScreen = CreateNewRuleScreen()
    private val typeOfTrigger = TypeOfTriggerScreen()
    private val typeOfAction = TypeOfActionScreen()
    private val overviewScreen = OverviewScreen()

    /* Scenario
    Step 1. Open the Rules;
  * Step 2. Tap 'Add rule';
  * Step 3. Enter the name of the rule;
  * Step 4. Tap 'add trigger' (Wifi connection);
  * Step 5. Tap 'Add action' (USB Tethering, status - Activate);
  * Step 6. Save the rule;
  * Step 7. Open the Overview
  * Step 8. Tap service status
  * Step 9. Check that service status is "ON"
  * Step 10. Tap service status
  * Step 11. Delete the rule
       */
    @Test
    fun checkLaunchingService() {
        rulesScreen.actionOpenRules()
        rulesScreen.actionClickOnAddRuleButton()
        createNewRuleScreen.actionTypeRuleName(RULE_NAME)
        createNewRuleScreen.actionClickAddTriggerButton()
        typeOfTrigger.actionAddWifiConnectionTrigger(TRIGGER_WIFI_NAME)
        createNewRuleScreen.actionClickAddActionButton()
        typeOfAction.actionAddScreenBrightness(ACTION_PERCENT_SCREEN_BRIGHTNESS)
        createNewRuleScreen.actionClickOnSaveRuleButton()
        overviewScreen.actionOpenOverview()
        overviewScreen.actionClickOnServiceButton()
        overviewScreen.assertionServiceButtonHasText(STATUS_ON)
        overviewScreen.actionWaitForViewDoesNotExist(SERVICE_STARTED_MESSAGE)
        overviewScreen.actionWaitForViewDoesNotExist(SERVICE_ACTIVATING_MESSAGE)
        overviewScreen.actionClickOnServiceButton()
        overviewScreen.actionWaitForViewDoesNotExist(SERVICE_STOPPED_MESSAGE)
        rulesScreen.actionOpenRules()
        rulesScreen.actionDeleteRuleByName(RULE_NAME)
        rulesScreen.actionDeleteRuleByName(RULE_NAME)
        rulesScreen.assertionRuleIsNotDisplayed(RULE_NAME)
    }
}