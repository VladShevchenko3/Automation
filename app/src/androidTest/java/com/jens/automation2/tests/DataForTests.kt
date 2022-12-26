package com.jens.automation2.tests

object DataForTests {

    const val RULE_NAME = "New rule"
    const val LOCATION_NAME = "New location"
    const val PROFILE_NAME = "New profile"
    const val TRIGGER_WIFI_NAME = "network1.0.1"
    const val TRIGGER_WIFI_DESCRIPTION = "connected to wifi \"${TRIGGER_WIFI_NAME}\""
    const val ACTION_USB_NAME = "USB Tethering"
    const val ACTION_USB_STATUS: String = "Activate"
    const val ACTION_USB_DESCRIPTION = "turn $ACTION_USB_NAME on"
    const val TRIGGER_HEADSET_STATUS = "connected"
    const val TRIGGER_HEADSET_TYPE = "Headphone"
    const val TRIGGER_HEADSET_DESCRIPTION =
        "Headset (type: $TRIGGER_HEADSET_TYPE) $TRIGGER_HEADSET_STATUS"
    const val ACTION_PERCENT_SCREEN_BRIGHTNESS = 50
    const val LEVEL_VOLUME_NOTIFICATIONS = 2
    const val SOUND_MODE = "Normal"
    const val SERVICE_STARTED_MESSAGE = "Automation Service started."
    const val SERVICE_STOPPED_MESSAGE = "Automation service stopped."
    const val SERVICE_ACTIVATING_MESSAGE = "Activating rule $RULE_NAME"
}