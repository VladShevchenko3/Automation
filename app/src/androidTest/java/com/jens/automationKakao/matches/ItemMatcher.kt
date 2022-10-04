package com.jens.automationKakao.matches

import com.jens.automation2.ActivityManageRule.Item
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class ActionItemTextMatcherNew(private val itemView: KTextView) : TypeSafeMatcher<Item>() {
    override fun describeTo(description: Description) {
        description.appendText("action item with text")
    }

    override fun matchesSafely(item: Item): Boolean {
        return itemView.toString() == item.text
    }
}