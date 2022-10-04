package com.jens.automationKakao.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jens.automation2.ActivityMainTabLayout
import com.jens.automationKakao.screens.rulesScreen.CreateNewRuleScreen
import com.jens.automationKakao.screens.rulesScreen.RulesScreen
import com.jens.automationKakao.screens.rulesScreen.actionsScreens.SetScreenBrightnessScreen
import com.jens.automationKakao.screens.rulesScreen.triggersScreens.HeadsetConnectionScreen
import com.jens.automationKakao.screens.rulesScreen.triggersScreens.WifiConnectionScreen
import com.jens.automationKakao.tests.DataForTests.Companion.PERCENT_SCREEN_BRIGHTNESS
import com.jens.automationKakao.tests.DataForTests.Companion.RULE_NAME
import com.jens.automationKakao.tests.DataForTests.Companion.TRIGGER_WIFI_NAME
import io.github.kakaocup.kakao.screen.Screen.Companion.onScreen
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
    * Step 7. Tap 'Add action' (Set Screen brightness);
    * Step 8. Find and check the added action;
    * Step 9. Save the rule;
    * Step 10. Find and check the added rule;
    * Step 11. Delete and check the added rule;
    * */
    @Test
    fun checkAddingTheRule() {
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
            actionClickAddTriggerButton()
        }
        onScreen<HeadsetConnectionScreen> {
            actionClickOnTheHeadsetConnection()
            actionClickOnConnectedView()
            actionClickOnHeadphoneView()
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
        onScreen<RulesScreen> {
            assertionRuleIsDisplayed(RULE_NAME)
            actionDeleteRuleByName(RULE_NAME)
            assertionRuleIsNotDisplayed(RULE_NAME)
        }
    }
}