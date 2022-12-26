package com.jens.automation2.matchers

fun actionItemWithName(itemName: String) = ActionItemTextMatcher(itemName)

fun actionWithSeekBar(progress: Int) = ActionWithSeekBar(progress)