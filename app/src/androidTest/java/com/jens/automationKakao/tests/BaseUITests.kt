package com.jens.automationKakao.tests

import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.jens.automationKakao.matches.ActionWaitingTime
import io.github.kakaocup.kakao.Kakao
import org.junit.Before

const val DEFAULT_TIMEOUT_MILLIS = 15_000L
const val DEFAULT_INTERVAL_MILLIS = 250L

interface BaseUITests {

    @Before
    fun setup() {
        Kakao { // Kakao runtime
            intercept {
                onViewInteraction { // Intercepting calls on ViewInteraction classes across whole runtime
                    onPerform { interaction, _ ->
                        val startTime = System.currentTimeMillis()
                        val endTime = startTime + DEFAULT_TIMEOUT_MILLIS
                        var lastException: Throwable
                        do {
                            try {
                                interaction.check(matches(isDisplayed()))
                                return@onPerform
                            } catch (e: NoMatchingViewException) {
                                lastException = e
                                ActionWaitingTime(DEFAULT_INTERVAL_MILLIS)
                            }
                        } while (System.currentTimeMillis() < endTime)

                        throw lastException
                    }
                }
            }
        }
    }
}