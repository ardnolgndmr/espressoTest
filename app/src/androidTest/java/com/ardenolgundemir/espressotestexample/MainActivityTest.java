package com.ardenolgundemir.espressotestexample;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import com.ardenolgundemir.espressotestexample.controller.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by ardenolgundemir on 29.05.2018.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends InstrumentationTestCase{

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    @Test
    public void useAppContext() throws Exception {
        CountingIdlingResource mainActivityIdlingResource = mainActivityActivityTestRule.getActivity().getEspressoIdlingResourceForMainActivity();

        // registering MainActivity's idling resource for enabling Espresso sync with MainActivity's background threads
        Espresso.registerIdlingResources(mainActivityIdlingResource);

        onView(withText("Hello World!")).check(matches(isDisplayed()));
        onView(withId(R.id.tvUserName)).check(matches(isDisplayed()));
        onView(withId(R.id.btn)).perform(click());
        onView(withId(R.id.tvUserName)).check(matches(withText("ardnolgndmr")));
    }

//
//    //Visible Test
//    @Test
//    public void onHelloTestVisible() throws Exception{
//        onView(withText("Hello World!")).check(matches(isDisplayed()));
//        onView(withId(R.id.tvUserName)).check(matches(isDisplayed()));
//        onView(withId(R.id.btn)).perform(click());
//        if (webServices != null){
//
//            webServices.getUser("ardnolgndmr")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<UserModel>() {
//            @Override
//            public void call(UserModel userModel) {
//                if (userModel != null){
//                    onView(withId(R.id.tvUserName)).check(matches(withText("ardnolgndmr")));
//                }
//            }
//        }, throwable -> {});
//        }
//    }
//
////    //Start Activity Test
////    @Test
////    public void onStartActivity() throws Exception{
////        Intents.init();
////        onView(withId(R.id.btn)).perform(click());
////        intended(hasComponent(NewActivity.class.getName()));
////        onView(withText("Hello Espresso!")).check(matches(isDisplayed()));
////        Intents.release();
////    }

}
