package com.jens.automationKakao.screens.overviewScreen

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import com.jens.automation2.R
import com.jens.automationKakao.screens.WithTabs
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import junit.framework.Assert.assertFalse

class OverviewScreen : Screen<OverviewScreen>(), WithTabs {

    private val serviceButton = KButton { withId(R.id.tbArmMastListener) }
    private val device: UiDevice
        get() = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    fun actionClickOnServiceButton() {
        serviceButton {
            isVisible()
            click()
        }
    }

    fun assertionServiceButtonHasText(text: String) {
        serviceButton {
            isVisible()
            hasText(text)
        }
    }
    //with UI Device
    fun actionWaitForViewDoesNotExist(message: String) {
        device.waitForIdle()
        assertFalse(device.hasObject(By.text(message)))
    }
}