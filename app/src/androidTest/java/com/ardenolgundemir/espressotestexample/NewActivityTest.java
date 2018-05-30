package com.ardenolgundemir.espressotestexample;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by ardenolgundemir on 29.05.2018.
 */

@RunWith(AndroidJUnit4.class)
public class NewActivityTest {
    @Rule
    public ActivityTestRule<NewActivity> newActivityActivityTestRule = new ActivityTestRule<NewActivity>(NewActivity.class);

    @Test
    public void newTestActivityTest(){
        onView(withText("New Activity")).check(matches(isDisplayed()));

    }
}
