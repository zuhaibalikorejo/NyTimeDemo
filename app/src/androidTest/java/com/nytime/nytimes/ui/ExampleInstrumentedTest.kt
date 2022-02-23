package com.nytime.nytimes.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.nytime.common.EspressoIdlingResourceTest
import com.nytime.nytimes.R
import com.nytime.ui.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

   @Rule @JvmField
    val mIntentsRule = ActivityTestRule(MainActivity::class.java)
      @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResourceTest.idlingResource)
      }

    @Test
    fun testRecyclerView() {
        onView(withId(R.id.rv_mostViewArticle)).check(matches(isDisplayed()))
    }


    @Test
    fun testTitleInRecyclerViewItem() {
        onView(withRecyclerView(R.id.rv_mostViewArticle).atPositionOnView(1, R.id.titleTxtView)).check(matches(isDisplayed()))

    }

    @Test
    fun testArticleSnippetInRecyclerViewItem() {
        onView(withRecyclerView(R.id.rv_mostViewArticle).atPositionOnView(1, R.id.txtArticleSnippet)).check(matches(isDisplayed()))

    }

    @Test
    fun testpublishedDateTxtViewInRecyclerViewItem() {
        onView(withRecyclerView(R.id.rv_mostViewArticle).atPositionOnView(1,
            R.id.publishedDateTxtView
        )).check(matches(isDisplayed()))
    }

    fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }

    @Test
    fun testClick() {
        onView(withRecyclerView(R.id.rv_mostViewArticle)
                .atPosition(2)
        ).perform(click())
    }

    @Test
    fun testDetailsScreen() {
        onView(
            withRecyclerView(R.id.rv_mostViewArticle)
                .atPosition(2)
        ).perform(click())
        onView(withId(R.id.webView)).check(matches(isDisplayed()))
    }


}