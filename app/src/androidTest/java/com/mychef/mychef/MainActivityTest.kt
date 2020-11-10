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
class MainActivityTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.mychef.mychef", appContext.packageName)
    }
    fun checkFavoritesNavigation(){
        onView(withId(R.id.miFavorites)).perform(click())
        onView(withId(R.id.tvFavoritesFragment)).check(matches(withText("Favorites")))
        onView(withId(R.id.tvFavoritesFragment)).check(matches(withText("Lemon Pepper Chicken Breast")))
    }
    fun checkPantryNavigation(){
        onView(withId(R.id.miPantry)).perform(click())
        onView(withId(R.id.tvPantryFragment)).check(matches(withText("Pantry")))
    }
    fun checkListNavigation(){
        onView(withId(R.id.miList)).perform(click())
        onView(withId(R.id.tvListFragment)).check(matches(withText("List")))
    }
    fun checkMoreNavigation(){
        onView(withId(R.id.miMore)).perform(click())
        onView(withId(R.id.tvMoreFragment)).check(matches(withText("More")))
        onView(withId(R.id.sign_out_button)).check(matches(withText("Sign Out")))

    }
}
