package com.jens.automationKakao.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.jens.automation2.ActivityMainTabLayout
import com.jens.automationKakao.screens.rules.RulesScreen
import com.jens.automationKakao.screens.rules.newRules.ActionList
import com.jens.automationKakao.screens.rules.newRules.NewRuleScreen
import com.jens.automationKakao.screens.rules.newRules.TriggerList
import com.jens.automationKakao.screens.rules.newRules.actions.SetScreenBrightnessScreen
import com.jens.automationKakao.screens.rules.newRules.actions.TriggerAUrlScreen
import com.jens.automationKakao.screens.rules.newRules.triggers.HeadsetConnectionScreen
import com.jens.automationKakao.screens.rules.newRules.triggers.WifiConnectionScreen
import com.jens.automationKakao.tests.DataForTests.Companion.PASSWORD
import com.jens.automationKakao.tests.DataForTests.Companion.RULE_NAME
import com.jens.automationKakao.tests.DataForTests.Companion.TRIGGER_WIFI_NAME
import com.jens.automationKakao.tests.DataForTests.Companion.URL_TRIGGER_DEV
import com.jens.automationKakao.tests.DataForTests.Companion.URL_TRIGGER_TESTER
import com.jens.automationKakao.tests.DataForTests.Companion.USERNAME
import io.github.kakaocup.kakao.screen.Screen.Companion.onScreen
import org.junit.Rule
import org.junit.Test


class TestRules {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<ActivityMainTabLayout>()

    /*
    *  Test 1. Scenario
    * Step 1. Open the Rules
    * Step 2. Click 'Add rule'
    * Step 3. Enter the name of the rule
    * Step 4. Click 'Add trigger'
    * Step 5. Select 'Headset connection' trigger
    * Step 6. Select the parameters and type of headphone, name and click 'Save'
    * Step 7. Check the size of trigger list is equal to 1
    * Step 8. Click 'Add trigger'
    * Step 9. Select 'Wifi connection' trigger
    * Step 10. Select the stage, set the name and click 'Save'
    * Step 11. Check the size of trigger list is equal to 2
    * Step 12. Click 'Add action'
    * Step 13. Select 'Trigger a URL' action
    * Step 14. Set the URL, check 'Use authentication', set username, password and click 'save'
    * Step 15. Check the size of action list is equal to 1
    * Step 16. Click 'Add action'
    * Step 17. Select 'Set screen brightness' action
    * Step 18. Set the progress of screen brightness
    * Step 19. Check the size of action list is equal to 2
    * Step 20. Click 'Save the rule'
    * Step 21. Check the rule is displayed
    * Step 22. Delete the rule
    * Step 23. Check the rule is not displayed
    * */
    @Test
    fun addTheRuleWithTriggersAndActions() {
        onScreen<RulesScreen> {
            actionOpenRules()
            actionClickOnAddRuleBtn()
        }
        onScreen<NewRuleScreen> {
            actionTypeRuleName(RULE_NAME)
            actionClickAddTriggerBtn()
        }
        onScreen<TriggerList> {
            actionClickOnTheHeadsetConnection()
        }
        onScreen<HeadsetConnectionScreen> {
            actionClickOnConnected()
            actionClickOnHeadphone()
        }
        onScreen<NewRuleScreen> {
            assertSizeOfTriggerList(1)
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
            assertSizeOfTriggerList(2)
            actionClickAddActionBtn()
        }
        onScreen<ActionList> {
            actionClickOnTriggerAUrl()
        }
        onScreen<TriggerAUrlScreen> {
            actionEditUrlTrigger(URL_TRIGGER_DEV)
            actionCheckUseAuthentication()
            actionEditUserName(USERNAME)
            actionEditPassword(PASSWORD)
            actionClickOnSaveButton()
        }
        onScreen<NewRuleScreen> {
            assertSizeOfActionList(1)
            actionClickAddActionBtn()
        }
        onScreen<ActionList> {
            actionClickOnSetScreenBrightness()
        }
        onScreen<SetScreenBrightnessScreen> {
            actionSetProgressOnSeekBar(30)
            actionClickOnApplyButton()
        }
        onScreen<NewRuleScreen> {
            assertSizeOfActionList(2)
            actionClickOnSaveRuleButton()
        }
        onScreen<RulesScreen> {
            assertionRuleIsDisplayed(RULE_NAME)
            actionDeleteRuleByName(RULE_NAME)
            assertionRuleIsNotDisplayed(RULE_NAME)
        }
    }

    /*
        *  Test 2. Scenario
        * Step 1. Open the Rules
        * Step 2. Click 'Add rule'
        * Step 3. Enter the name of the rule
        * Step 4. Click 'Add trigger'
        * Step 5. Select 'Headset connection' trigger
        * Step 6. Select the parameters and type of headphone, name and click 'Save'
        * Step 7. Check the size of trigger list is equal to 1
        * Step 8. Click 'Add action'
        * Step 9. Select 'Trigger a URL' action
        * Step 10. Set the URL, check 'Use authentication', set username, password and click 'save'
        * Step 11. Double click on the created action
        * Step 12. Edit the URL and click 'save'
        * Step 13. Check the size of action list is equal to 1
        * Step 14. Click 'Save the rule'
        * Step 15. Check the rule is displayed
        * Step 16. Delete the rule
        * Step 17. Check the rule is not displayed
        * */
    @Test
    fun addTheRuleWithURLTriggerActionAndEditIt() {
        onScreen<RulesScreen> {
            actionOpenRules()
            actionClickOnAddRuleBtn()
        }
        onScreen<NewRuleScreen> {
            actionTypeRuleName(RULE_NAME)
            actionClickAddTriggerBtn()
        }
        onScreen<TriggerList> {
            actionClickOnTheHeadsetConnection()
        }
        onScreen<HeadsetConnectionScreen> {
            actionClickOnConnected()
            actionClickOnHeadphone()
        }
        onScreen<NewRuleScreen> {
            assertSizeOfTriggerList(1)
            actionClickAddActionBtn()
        }
        onScreen<ActionList> {
            actionClickOnTriggerAUrl()
        }
        onScreen<TriggerAUrlScreen> {
            actionEditUrlTrigger(URL_TRIGGER_DEV)
            actionCheckUseAuthentication()
            actionEditUserName(USERNAME)
            actionEditPassword(PASSWORD)
            actionClickOnSaveButton()
        }
        onScreen<NewRuleScreen> {
            actionDoubleClickOnActionItem(0)
        }
        onScreen<TriggerAUrlScreen> {
            actionEditUrlTrigger(URL_TRIGGER_TESTER)
            actionClickOnSaveButton()
        }
        onScreen<NewRuleScreen> {
            assertSizeOfActionList(1)
            actionClickOnSaveRuleButton()
        }
        onScreen<RulesScreen> {
            assertionRuleIsDisplayed(RULE_NAME)
            actionDeleteRuleByName(RULE_NAME)
            assertionRuleIsNotDisplayed(RULE_NAME)
        }
    }
}