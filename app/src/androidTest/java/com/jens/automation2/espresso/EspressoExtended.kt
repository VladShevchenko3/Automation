package com.jens.automation2.espresso

import android.view.View
import androidx.test.espresso.DataInteraction
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.TypeTextAction
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.jens.automation2.matchers.actionItemWithName
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

fun findElementWithParent(
    resource: Matcher<View>,
    resourceParent: Matcher<View>
): ViewInteraction {
    return onView(
        allOf(
            hasDescendant(resource),
            withParent(resourceParent)
        )
    )
}

fun typeText(resource: Matcher<View>, text: String): ViewInteraction {
    return onView(resource).perform(
        clearText(),
        TypeTextAction(text),
        closeSoftKeyboard()
    )
}

fun findElementInList(itemName: String): DataInteraction {
    return onData(actionItemWithName(itemName))
}
