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
import com.jens.automationKakao.screens.rules.newRules.triggers.LocationScreen
import com.jens.automationKakao.screens.rules.newRules.triggers.WifiConnectionScreen
import com.jens.automationKakao.tests.DataForTests.Companion.PASSWORD
import com.jens.automationKakao.tests.DataForTests.Companion.RULE_NAME
import com.jens.automationKakao.tests.DataForTests.Companion.RULE_NAME_CLONE
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
    * Step 22. Check the rule status is active
    * Step 23. Delete the rule
    * Step 24. Check the rule is not displayed
    * */
    @Test
    fun addTheActiveRule() {
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
            // assertTriggerItemTitle(0)
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
            actionClickOnSaveBtn()
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
            actionClickOnSaveBtn()
        }
        onScreen<RulesScreen> {
            assertTheRuleIsDisplayedByPosition(0, RULE_NAME)
            assertTheRuleStatusIsActiveByPosition(0)
            actionDeleteTheRuleByPosition(0)
            assertSizeOfRuleList(0)
        }
    }

    /*
        *  Test 2. Scenario
        * Step 1. Open the Rules
        * Step 2. Click 'Add rule'
        * Step 3. Click 'Add action'
        * Step 4. Select 'Trigger a URL' action
        * Step 5. Set the URL, check 'Use authentication', set username, password and click 'save'
        * Step 6. Double click on the created action
        * Step 7. Edit the URL and click 'save'
        * Step 8. Check the size of action list is equal to 1
        * */
    @Test
    fun addActionAndEditIt() {
        onScreen<RulesScreen> {
            actionOpenRules()
            actionClickOnAddRuleBtn()
        }
        onScreen<NewRuleScreen> {
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
            actionClickOnSaveBtn()
        }
        onScreen<NewRuleScreen> {
            actionDoubleClickOnActionItemByPosition(0)
        }
        onScreen<TriggerAUrlScreen> {
            actionEditUrlTrigger(URL_TRIGGER_TESTER)
            actionClickOnSaveBtn()
        }
        onScreen<NewRuleScreen> {
            assertSizeOfActionList(1)
        }
    }

    /*
        *  Test 3. Scenario
        * Step 1. Open the Rules
        * Step 2. Click 'Add rule'
        * Step 3. Click 'Add trigger'
        * Step 4. Select 'Wifi connection' trigger
        * Step 5. Select the stage, set the name and click 'Save'
        * Step 6. Check the size of trigger list is equal to 1
        * Step 7. Long click on the trigger in trigger list
        * Step 8. Select 'delete'
        * Step 9. Check the size of trigger list is equal to 0
        * */
    @Test
    fun addAndDeleteTheTrigger() {
        onScreen<RulesScreen> {
            actionOpenRules()
            actionClickOnAddRuleBtn()
        }
        onScreen<NewRuleScreen> {
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
            actionDeleteItemInTriggerListByPosition(0)
            assertSizeOfTriggerList(0)
        }
    }

    /*
    *  Test 4. Scenario
    * Step 1. Open the Rules
    * Step 2. Click 'Add rule'
    * Step 3. Enter the name of the rule
    * Step 4. Click 'Add trigger'
    * Step 5. Select 'Headset connection' trigger
    * Step 6. Select the parameters and type of headphone, name and click 'Save'
    * Step 7. Click 'Add action'
    * Step 8. Select 'Trigger a URL' action
    * Step 9. Set the URL, check 'Use authentication', set username, password and click 'save'
    * Step 10. Uncheck 'Rule active'
    * Step 11. Click 'Save the rule'
    * Step 12. Check the rule is displayed
    * Step 13. Check the rule status is inactive
    * Step 14. Delete the rule
    * Step 15. Check the rule is not displayed
    * */
    @Test
    fun addTheInactiveRule() {
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
            actionClickOnSaveBtn()
        }
        onScreen<NewRuleScreen> {
            actionClickOnRuleActiveCheckBox()
            actionClickOnSaveBtn()
        }
        onScreen<RulesScreen> {
            assertTheRuleIsDisplayedByPosition(0, RULE_NAME)
            assertTheRuleStatusIsInactiveByPosition(0)
            actionDeleteTheRuleByPosition(0)
            assertSizeOfRuleList(0)
        }
    }

    /*
*  Test 5. Scenario
* Step 1. Open the Rules
* Step 2. Click 'Add rule'
* Step 3. Enter the name of the rule
* Step 4. Click 'Add trigger'
* Step 5. Select 'Location' trigger
* Step 6. Select the parameters'
* Step 7. Click 'Add action'
* Step 8. Select 'Trigger a URL' action
* Step 9. Set the URL, check 'Use authentication', set username, password and click 'save'
* Step 10. Click 'Save the rule'
* Step 11. Check the rule is displayed
* Step 12. Check the rule status is unable
* Step 13. Clone the rule
* Step 14. Check the rule is cloned (check name and image)
* Step 15. Edit the cloned rule
* Step 16. Uncheck 'Rule active' and click 'save
* Step 17. Check the image of cloned rule is updated
* Step 18. Delete the rule and cloned rule
* Step 19. Check the rule and cloned rule are not displayed
* */
    @Test
    fun cloneTheUnableRuleAndChangeToInactive() {
        onScreen<RulesScreen> {
            actionOpenRules()
            actionClickOnAddRuleBtn()
        }
        onScreen<NewRuleScreen> {
            actionTypeRuleName(RULE_NAME)
            actionClickAddTriggerBtn()
        }
        onScreen<TriggerList> {
            actionClickOnTheLocation()
        }
        onScreen<LocationScreen> {
            actionClickOnEnteringView()
            actionClickOnNoneView()
        }
        onScreen<NewRuleScreen> {
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
            actionClickOnSaveBtn()
        }
        onScreen<NewRuleScreen> {
            actionClickOnSaveBtn()
        }
        onScreen<RulesScreen> {
            assertTheRuleIsDisplayedByPosition(0, RULE_NAME)
            assertTheRuleStatusIsUnableByPosition(0)
            actionCloneTheRuleByPosition(0)
            assertTheRuleIsDisplayedByPosition(1, RULE_NAME_CLONE)
            assertTheRuleStatusIsUnableByPosition(1)
            actionEditTheRuleByPosition(1)
        }
        onScreen<NewRuleScreen> {
            actionClickOnRuleActiveCheckBox()
            actionClickOnSaveBtn()
        }
        onScreen<RulesScreen> {
            assertTheRuleIsDisplayedByPosition(1, RULE_NAME_CLONE)
            assertTheRuleStatusIsInactiveByPosition(1)
            actionDeleteTheRuleByPosition(0)
            assertSizeOfRuleList(1)
            actionDeleteTheRuleByPosition(0)
            assertSizeOfRuleList(0)
        }
    }
}