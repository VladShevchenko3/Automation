package com.jens.automationKakao.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jens.automation2.ActivityMainTabLayout
import com.jens.automationKakao.screens.overviewScreen.OverviewScreen
import com.jens.automationKakao.screens.rulesScreen.CreateNewRuleScreen
import com.jens.automationKakao.screens.rulesScreen.RulesScreen
import com.jens.automationKakao.screens.rulesScreen.actionsScreens.SetScreenBrightnessScreen
import com.jens.automationKakao.screens.rulesScreen.triggersScreens.WifiConnectionScreen
import com.jens.automationKakao.tests.DataForTests.Companion.PERCENT_SCREEN_BRIGHTNESS
import com.jens.automationKakao.tests.DataForTests.Companion.RULE_NAME
import com.jens.automationKakao.tests.DataForTests.Companion.SERVICE_ACTIVATING_MESSAGE
import com.jens.automationKakao.tests.DataForTests.Companion.SERVICE_STARTED_MESSAGE
import com.jens.automationKakao.tests.DataForTests.Companion.SERVICE_STOPPED_MESSAGE
import com.jens.automationKakao.tests.DataForTests.Companion.STATUS_ON
import com.jens.automationKakao.tests.DataForTests.Companion.TRIGGER_WIFI_NAME
import io.github.kakaocup.kakao.screen.Screen.Companion.onScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestOverview : BaseUITests {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<ActivityMainTabLayout>()

    /* Scenario
    * Step 1. Open the Rules;
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
    * */
    @Test
    fun checkLaunchingService() {
        onScreen<RulesScreen> {
            actionOpenRules()
            actionClickOnAddRuleButton()
        }
        onScreen<CreateNewRuleScreen> {
            actionTypeRuleName(RULE_NAME)
            actionClickAddTriggerButton()
        }
        onScreen<WifiConnectionScreen> {
            actionClickOnTheWifiConnection()
            actionTypeWifiName(TRIGGER_WIFI_NAME)
            actionClickOnSaveWifiButton()
        }
        onScreen<CreateNewRuleScreen> {
            actionClickAddActionButton()
        }
        onScreen<SetScreenBrightnessScreen> {
            actionClickOnSetScreenBrightness()
            actionSetProgressOnSeekBar(PERCENT_SCREEN_BRIGHTNESS)
            actionClickOnApplyButton()
        }
        onScreen<CreateNewRuleScreen> {
            actionClickOnSaveRuleButton()
        }
        onScreen<OverviewScreen> {
            actionOpenOverview()
            actionClickOnServiceButton()
            assertionServiceButtonHasText(STATUS_ON)
            actionWaitForViewDoesNotExist(SERVICE_STARTED_MESSAGE)
            actionWaitForViewDoesNotExist(SERVICE_ACTIVATING_MESSAGE)
            actionClickOnServiceButton()
            actionWaitForViewDoesNotExist(SERVICE_STOPPED_MESSAGE)
        }
        onScreen<RulesScreen> {
            actionOpenRules()
            actionDeleteRuleByName(RULE_NAME)
            actionDeleteRuleByName(RULE_NAME)
            assertionRuleIsNotDisplayed(RULE_NAME)
        }
    }
}