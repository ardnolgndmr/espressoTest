package com.ardenolgundemir.espressotestexample;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.action.ViewActions.click;
import static org.hamcrest.Matchers.not;

/**
 * Created by ardenolgundemir on 29.05.2018.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void init(){
        Log.i("Test", getClass().getName().toLowerCase());
    }

    @Test
    public void onHelloTestVisible() throws Exception{
        Thread.sleep(2000);

        Intents.init();
        onView(withText("Hello World!")).check(matches(isDisplayed()));
        onView(withId(R.id.btn)).perform(click());
        //onView(withText("Hello World!")).check(matches(not(isDisplayed())));
        intended(hasComponent(NewActivity.class.getName()));
        Intents.release();
        onView(withText("New Activity")).check(matches(isDisplayed()));

        Thread.sleep(5000);

    }

}
