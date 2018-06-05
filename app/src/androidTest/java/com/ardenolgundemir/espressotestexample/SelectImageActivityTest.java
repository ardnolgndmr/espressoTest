package com.ardenolgundemir.espressotestexample;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.ImageView;

import com.ardenolgundemir.espressotestexample.controller.activity.TakeAPhotoActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.ardenolgundemir.espressotestexample.ImageViewHasDrawableMatcher.hasDrawable;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by ardenolgundemir on 5.06.2018.
 */

public class SelectImageActivityTest {

    private Instrumentation.ActivityResult mActivityResult;

    @Rule
    public final ActivityTestRule<TakeAPhotoActivity> rule =
            new ActivityTestRule<>(TakeAPhotoActivity.class);


    @Before
    public void setupImageUri() {

        Resources resources = InstrumentationRegistry.getTargetContext().getResources();
        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + resources
                .getResourcePackageName(
                        R.drawable.img_fincan_0_a) + '/' + resources.getResourceTypeName(
                R.drawable.img_fincan_0_a) + '/' + resources.getResourceEntryName(
                R.drawable.img_fincan_0_a));
        Intent resultData = new Intent();
        resultData.setData(imageUri);
        mActivityResult = new Instrumentation.ActivityResult(
                Activity.RESULT_OK, resultData);


    }

    @Test
    public void testSelectImage() throws Exception{

        //Check the image is not displayed
        onView(withId(R.id.imageView)).check(matches(not(hasDrawable())));

        //Setup the intent
        Intents.init();
        Matcher<Intent> expectedIntent = allOf(hasAction(Intent.ACTION_PICK),
                hasData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
        intending(expectedIntent).respondWith(mActivityResult);

        //Click the select button
        onView(withId(R.id.button_take_photo)).perform(click());
        intended(expectedIntent);
        Intents.release();

        //Check the image is displayed
        onView(withId(R.id.imageView)).check(matches(hasDrawable()));
        Thread.sleep(1000);

    }


}