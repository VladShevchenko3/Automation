package com.jens.automationKakao.matches

import io.github.kakaocup.kakao.text.KTextView

fun actionItemWithName(itemName: String) = ActionItemTextMatcher(itemName)

fun actionItemWithNameNew(itemName: KTextView) = ActionItemTextMatcherNew(itemName)