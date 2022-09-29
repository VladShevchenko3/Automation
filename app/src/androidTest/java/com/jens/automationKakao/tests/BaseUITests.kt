package com.jens.automationKakao.tests

import androidx.test.core.app.ActivityScenario
import com.jens.automation2.ActivityMainTabLayout
import org.junit.Before

interface BaseUITests {
    @Before
    fun setup(){
        ActivityScenario.launch(ActivityMainTabLayout::class.java)
    }
}