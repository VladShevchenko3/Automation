package com.jens.automationKakao.screens

import com.jens.automation2.R
import io.github.kakaocup.kakao.image.KImageView

interface WithTabs {

    companion object {
        private val dashboard = KImageView { withId(android.R.id.tabs) }
        private val rulesButton = KImageView { withText(R.string.rules) }
        private val profilesButton = KImageView { withText(R.string.profiles) }
        private val locationsButton = KImageView { withText(R.string.pois) }
        private val overviewButton = KImageView { withText(R.string.overview) }
    }

    fun actionOpenRules() {
        dashboard.isVisible()
        rulesButton.click()
    }

    fun actionOpenProfiles() {
        dashboard.isVisible()
        profilesButton.click()
    }

    fun actionOpenLocation() {
        dashboard.isVisible()
        locationsButton.click()
    }

    fun actionOpenOverview() {
        dashboard.isVisible()
        overviewButton.click()
    }
}