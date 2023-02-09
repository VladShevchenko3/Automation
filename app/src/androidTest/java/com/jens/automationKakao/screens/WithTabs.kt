package com.jens.automationKakao.screens

import com.jens.automation2.R
import io.github.kakaocup.kakao.image.KImageView

interface WithTabs {

    companion object {
        private val dashboard = KImageView { withId(android.R.id.tabs) }
        private val rulesBtn = KImageView { withText(R.string.rules) }
        private val profilesBtn = KImageView { withText(R.string.profiles) }
        private val locationsBtn = KImageView { withText(R.string.pois) }
        private val overviewBtn = KImageView { withText(R.string.overview) }
    }

    fun actionOpenRules() {
        dashboard.isVisible()
        rulesBtn.click()
    }

    fun actionOpenProfiles() {
        dashboard.isVisible()
        profilesBtn.click()
    }

    fun actionOpenLocation() {
        dashboard.isVisible()
        locationsBtn.click()
    }

    fun actionOpenOverview() {
        dashboard.isVisible()
        overviewBtn.click()
    }
}