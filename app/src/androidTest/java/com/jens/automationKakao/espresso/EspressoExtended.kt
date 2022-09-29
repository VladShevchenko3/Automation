package com.jens.automationKakao.espresso

import androidx.test.espresso.DataInteraction
import androidx.test.espresso.Espresso.onData
import com.jens.automationKakao.matches.actionItemWithName

fun findElementInList(itemName: String): DataInteraction {
    return onData(actionItemWithName(itemName))
}