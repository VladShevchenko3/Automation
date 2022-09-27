package com.jens.automation2.matchers

import android.view.View
import android.widget.SeekBar

import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher


class ActionWithSeekBar(private val progress: Int) : ViewAction {
    override fun getConstraints(): Matcher<View> {
        return ViewMatchers.isAssignableFrom(SeekBar::class.java)
    }

    override fun getDescription() = "Set a progress on a $progress"

    override fun perform(uiController: UiController, view: View) {
        val seekBar: SeekBar = view as SeekBar
        if (progress !in seekBar.min..seekBar.max)
            throw IllegalArgumentException(
                "progress should be in range " +
                        "${seekBar.min}..${seekBar.max} but got $progress"
            )
        seekBar.progress = progress
    }
}