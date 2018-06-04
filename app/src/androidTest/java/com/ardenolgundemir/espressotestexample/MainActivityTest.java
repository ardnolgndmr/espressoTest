package com.ardenolgundemir.espressotestexample;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;
import android.util.Log;

import com.ardenolgundemir.espressotestexample.api.WebServices;
import com.ardenolgundemir.espressotestexample.controller.activity.MainActivity;

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
public class MainActivityTest extends InstrumentationTestCase{

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    WebServices webServices;
    @Before
    public void setUp(){

        Log.i("MainActivityTest", getClass().getName().toLowerCase());
        webServices = mainActivityActivityTestRule.getActivity().getService();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());

    }

    //Visible Test
    @Test
    public void onHelloTestVisible() throws Exception{
        onView(withText("Hello World!")).check(matches(isDisplayed()));
        if (webServices != null){

        }
    }

    //Start Activity Test
    @Test
    public void onStartActivity() throws Exception{
        Intents.init();
        onView(withId(R.id.btn)).perform(click());
        intended(hasComponent(NewActivity.class.getName()));
        onView(withText("Hello Espresso!")).check(matches(isDisplayed()));
        Intents.release();
    }

}
