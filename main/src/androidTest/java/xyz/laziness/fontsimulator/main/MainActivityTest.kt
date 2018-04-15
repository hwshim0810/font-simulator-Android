package xyz.laziness.fontsimulator.main

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.MediumTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class MainActivityTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun testInputText() {
        val testText = "Example Text"

        onView(withId(R.id.editText)).perform(clearText())
        onView(withId(R.id.editText)).perform(typeText(testText), closeSoftKeyboard())

        onView(withId(R.id.singleLineText)).check(matches(withText(testText)))
        onView(withId(R.id.multiLineText)).check(matches(withText(testText)))
    }

}
