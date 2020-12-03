package com.mychef.mychef

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.kizitonwose.calendarview.CalendarView
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import com.kizitonwose.calendarview.utils.yearMonth
import kotlinx.android.synthetic.main.fragment_list.*
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep
import java.time.DayOfWeek
import java.time.YearMonth

@RunWith(AndroidJUnit4::class)
class ListFragmentTest {

    @get:Rule
    val homeScreenRule = ActivityTestRule(MainActivity::class.java, true, false)
    private val currentMonth = YearMonth.now()

    @Before
    fun setup() {
        homeScreenRule.launchActivity(null)
    }

    @After
    fun teardown() {
    }

    // Tests to make sure when a user selects a new day, calendar binds to the new day
    @Test
    fun dayBinderIsCalledOnDayChanged() {
        onView(withId(R.id.miList)).perform(click())
        sleep(2000)
        class DayViewContainer(view: View) : ViewContainer(view)
        val calendarView = homeScreenRule.activity.listFragCalendar
        var boundDay: CalendarDay? = null
        val changedDate = currentMonth.atDay(4)
        homeScreenRule.runOnUiThread {
            calendarView.dayBinder = object : DayBinder<DayViewContainer> {
                override fun create(view: View) = DayViewContainer(view)
                override fun bind(container: DayViewContainer, day: CalendarDay) {
                    boundDay = day
                }
            }
        }
        // Allow time to pass for data to propagate
        sleep(2000)
        homeScreenRule.runOnUiThread {
            calendarView.notifyDateChanged(changedDate)
        }

        // Allow time to pass for data to propagate
        sleep(2000)
        assertTrue(boundDay?.date == changedDate)
        assertTrue(boundDay?.owner == DayOwner.THIS_MONTH)
    }

    // Tests to make sure when a user selects a new month, calendar binds to the new month
    @Test
    fun allBindersAreCalledOnMonthChanged() {
        onView(withId(R.id.miList)).perform(click())
        class TestViewContainer(view: View) : ViewContainer(view)
        val calendarView = homeScreenRule.activity.listFragCalendar
        val boundDays = mutableSetOf<CalendarDay>()
        var boundHeaderMonth: CalendarMonth? = null

        homeScreenRule.runOnUiThread {
            calendarView.dayBinder = object : DayBinder<TestViewContainer> {
                override fun create(view: View) = TestViewContainer(view)
                override fun bind(container: TestViewContainer, day: CalendarDay) {
                    boundDays.add(day)
                }
            }
            calendarView.monthHeaderBinder = object : MonthHeaderFooterBinder<TestViewContainer> {
                override fun create(view: View) = TestViewContainer(view)
                override fun bind(container: TestViewContainer, month: CalendarMonth) {
                    boundHeaderMonth = month
                }
            }
        }
        // Allow time to pass for data to propagate
        sleep(2000)
        homeScreenRule.runOnUiThread {
            boundDays.clear()
            boundHeaderMonth = null
            calendarView.notifyMonthChanged(currentMonth)
        }

        // Allow time to pass for data to propagate
        sleep(2000)
        assertTrue(boundHeaderMonth?.yearMonth == currentMonth)
        assertTrue(boundDays.count { it.owner == DayOwner.THIS_MONTH && it.date.yearMonth == currentMonth } == currentMonth.lengthOfMonth())
    }

    // Testing to see if all threads complete and are successful when creating CalendarView
    @Test
    fun completionBlocksAreCalledOnTheMainThread() {
        val calendarView = CalendarView(homeScreenRule.activity)
        homeScreenRule.runOnUiThread {
            val threadName = Thread.currentThread().name
            calendarView.setupAsync(YearMonth.now(), YearMonth.now().plusMonths(10), DayOfWeek.SUNDAY) {
                assertTrue(threadName == Thread.currentThread().name)
                calendarView.updateMonthConfigurationAsync {
                    assertTrue(threadName == Thread.currentThread().name)
                    calendarView.updateMonthRangeAsync {
                        assertTrue(threadName == Thread.currentThread().name)
                    }
                }
            }
        }
        sleep(3000)
    }

}