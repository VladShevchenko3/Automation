package com.jens.automationKakao.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.jens.automation2.ActivityMainTabLayout
import com.jens.automationKakao.screens.overviewScreen.OverviewScreen
import com.jens.automationKakao.screens.rules.RulesScreen
import com.jens.automationKakao.screens.rules.newRules.ActionList
import com.jens.automationKakao.screens.rules.newRules.NewRuleScreen
import com.jens.automationKakao.screens.rules.newRules.TriggerList
import com.jens.automationKakao.screens.rules.newRules.actions.SetScreenBrightnessScreen
import com.jens.automationKakao.screens.rules.newRules.triggers.WifiConnectionScreen
import com.jens.automationKakao.tests.DataForTests.Companion.PERCENT_SCREEN_BRIGHTNESS
import com.jens.automationKakao.tests.DataForTests.Companion.RULE_NAME
import com.jens.automationKakao.tests.DataForTests.Companion.SERVICE_ACTIVATING_MESSAGE
import com.jens.automationKakao.tests.DataForTests.Companion.SERVICE_STARTED_MESSAGE
import com.jens.automationKakao.tests.DataForTests.Companion.SERVICE_STOPPED_MESSAGE
import com.jens.automationKakao.tests.DataForTests.Companion.TRIGGER_WIFI_NAME
import io.github.kakaocup.kakao.screen.Screen.Companion.onScreen
import org.junit.Rule
import org.junit.Test


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
            actionClickOnAddRuleBtn()
        }
        onScreen<NewRuleScreen> {
            actionTypeRuleName(RULE_NAME)
            actionClickAddTriggerBtn()
        }
        onScreen<TriggerList> {
            actionClickOnTheWifiConnection()
        }
        onScreen<WifiConnectionScreen> {
            actionTypeWifiName(TRIGGER_WIFI_NAME)
            actionClickOnSaveBtn()
        }
        onScreen<NewRuleScreen> {
            actionClickAddActionBtn()
        }
        onScreen<ActionList> {
            actionClickOnSetScreenBrightness()
        }
        onScreen<SetScreenBrightnessScreen> {
            actionSetProgressOnSeekBar(PERCENT_SCREEN_BRIGHTNESS)
            actionClickOnApplyButton()
        }
        onScreen<NewRuleScreen> {
            actionClickOnSaveBtn()
        }
        onScreen<OverviewScreen> {
            actionOpenOverview()
            actionClickOnServiceButton()
            assertionServiceButtonStatusON()
            actionWaitForViewDoesNotExist(SERVICE_STARTED_MESSAGE)
            actionWaitForViewDoesNotExist(SERVICE_ACTIVATING_MESSAGE)
            actionClickOnServiceButton()
            actionWaitForViewDoesNotExist(SERVICE_STOPPED_MESSAGE)
        }
        onScreen<RulesScreen> {
            actionOpenRules()
            actionDeleteFirstRule()
            actionDeleteFirstRule()
           assertSizeOfRuleList(0)
        }
    }
}