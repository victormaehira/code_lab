package com.example.e_glicemia.ui.login


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.e_glicemia.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginAndAddGlicemiaTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun loginAndAddGlicemiaTest() {
        val appCompatEditText = onView(
            allOf(
                withId(R.id.editTextLoginEmailAddress),
                childAtPosition(
                    allOf(
                        withId(R.id.login),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText(""), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.editTextLoginEmailAddress),
                childAtPosition(
                    allOf(
                        withId(R.id.login),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(click())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.editTextLoginEmailAddress),
                childAtPosition(
                    allOf(
                        withId(R.id.login),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(replaceText("vymaehira@gmail.com"), closeSoftKeyboard())

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.editTextLoginPassword),
                childAtPosition(
                    allOf(
                        withId(R.id.login),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(replaceText("12345678"), closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(
                withId(R.id.buttonLoginEntrar), withText("Entrar"),
                childAtPosition(
                    allOf(
                        withId(R.id.login),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        Thread.sleep(1000);

        val appCompatImageButton = onView(
            allOf(
                withId(R.id.imageButtonMainAdcionaGlicemia),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.editTextDateGlicemia),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText5.perform(replaceText("07/04/2021"), closeSoftKeyboard())


        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.editTextTimeGlicemia),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText6.perform(replaceText("01:10"), closeSoftKeyboard())


        val appCompatEditText7 = onView(
            allOf(
                withId(R.id.editTextNumberGlicemia),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText7.perform(replaceText("120"), closeSoftKeyboard())

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.buttonGlicemiaAdicionar), withText("Salvar"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatButton2.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
