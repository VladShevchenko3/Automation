package com.jens.automation2.matchers

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector

fun actionClickOnGPByText(text: String) {
    val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    val grandPermission = device.findObject(UiSelector().text(text))
    grandPermission.click()
}