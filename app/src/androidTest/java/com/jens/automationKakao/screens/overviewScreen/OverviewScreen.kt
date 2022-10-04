package com.jens.automationKakao.screens.overviewScreen

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import com.jens.automation2.R
import com.jens.automation2.matchers.ToastMatcher
import com.jens.automationKakao.screens.WithTabs
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import junit.framework.Assert.assertFalse
import org.hamcrest.CoreMatchers

class OverviewScreen : Screen<OverviewScreen>(), WithTabs {

    private val serviceButton = KButton { withId(R.id.tbArmMastListener) }
    private val statusOnView = KTextView { withText("ON") }
    private val device: UiDevice
        get() = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    fun actionClickOnServiceButton() {
        serviceButton {
            click()
        }
    }

    fun assertionServiceButtonStatusON() {
        statusOnView {
            isVisible()
        }
    }

    fun assertionMessageIsDisplayed(message: String) {
        Espresso.onView(ViewMatchers.withText(CoreMatchers.containsString(message)))
            .inRoot(ToastMatcher())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    //with UI Device
    fun actionWaitForViewDoesNotExist(message: String) {
        device.waitForIdle()
        assertFalse(device.hasObject(By.text(message)))
    }
}