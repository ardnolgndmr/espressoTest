package com.ardenolgundemir.espressotestexample;

import android.app.Activity;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.instanceOf;

/**
 * Created by ardenolgundemir on 29.05.2018.
 */

@RunWith(AndroidJUnit4.class)
public class NewActivityTest {

    public static final String STRING_TO_BE_TYPED = "Espresso";


    @Rule
    public ActivityTestRule<NewActivity> newActivityActivityTestRule = new ActivityTestRule<NewActivity>(NewActivity.class);

//    @Test
//    public void dialogShow() throws Exception {
//        onView(withId(R.id.btnShowDialog)).perform(click());
//        Thread.sleep(1000);
//        onView(withText("Test")).check(matches(isDisplayed()));
//        Thread.sleep(3000);
//        onData(anything()).atPosition(1).perform(click());
//        onView(withId(R.id.btnShowDialog)).check(matches(withText("Test 1")));
//    }
    @Test
    public void changeText_sameActivity() throws Exception{
        // Type text and then press the button.
        onView(withId(R.id.editTextUserInput))
                .perform(typeText("Arden"), closeSoftKeyboard());
        onView(withId(R.id.changeTextBt)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.textToBeChanged)).check(matches(withText(STRING_TO_BE_TYPED)));
        Thread.sleep(3000);
    }

//    @Test
//    public void changeText_newActivity() throws Exception{
//        // Type text and then press the button.
//        onView(withId(R.id.editTextUserInput)).perform(typeText(STRING_TO_BE_TYPED),
//                closeSoftKeyboard());
//        onView(withId(R.id.activityChangeTextBtn)).perform(click());
//
//        onView(withId(R.id.show_text_view)).check(matches(withText(STRING_TO_BE_TYPED)));
//
//        Thread.sleep(3000);
//    }
}
