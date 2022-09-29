package com.jens.automationKakao.screens

import android.R
import io.github.kakaocup.kakao.image.KImageView

interface WithTabs {
    companion object {
        private val dashboard = KImageView { withId(R.id.tabs) }
        private val rulesButton = KImageView { withText("Rules") }
        private val profilesButton = KImageView { withText("Profiles") }
        private val locationsButton = KImageView { withText("Locations") }
        private val overviewButton = KImageView { withText("Overview") }
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