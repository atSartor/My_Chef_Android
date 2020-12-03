package com.mychef.mychef

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.rule.ActivityTestRule

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import java.util.regex.Pattern.matches

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.mychef.mychef", appContext.packageName)
    }
    @Test
    fun checkFavoritesNavigation(){
        onView(withId(R.id.miFavorites)).perform(click())
        onView(withId(R.id.tvFavoritesFragment)).check(matches(withText("Favorites")))
        onView(withId(R.id.tvFavoritesFragment)).check(matches(withText("Lemon Pepper Chicken Breast")))
    }
    @Test
    fun checkPantryNavigation(){
        onView(withId(R.id.miPantry)).perform(click())
        onView(withId(R.id.tvPantryFragment)).check(matches(withText("Pantry")))
    }
//    @Test
//    fun checkListNavigation(){
//        onView(withId(R.id.miList)).perform(click())
//        onView(withId(R.id.tv)).check(matches(withText("List")))
//    }
    @Test
    fun checkMoreNavigation(){
        onView(withId(R.id.miMore)).perform(click())
        onView(withId(R.id.tvMoreFragment)).check(matches(withText("More")))
    }
    @Test
    fun checkSearchNavigation(){
        onView(withId(R.id.miSearch)).perform(click())
        onView(withId(R.id.searchText)).perform(typeText("Testing123"))
        onView(withId(R.id.message)).check(matches(withText("Search Results Loading...")))
    }
    @Test
    fun checkSearchResults(){
        onView(withId(R.id.miSearch)).perform(click())
        onView(withId(R.id.searchText)).perform(typeText("Banana"))
        onView(withId(R.id.message)).check(matches(withText("Search Results Loading...")))
        onView(withId(R.id.message)).check(matches(withText("Banana")))

    }

}
