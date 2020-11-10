package com.mychef.mychef

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.assertion.ViewAssertions.matches

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.util.regex.Pattern.matches

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MoreFragmentTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.mychef.mychef", appContext.packageName)
    }
    fun checkSignoutButton(){
        onView(withId(R.id.sign_out_button)).perform(click())
        onView(withId(R.id.message)).check(matches(withText("MyChef")))
    }
}
