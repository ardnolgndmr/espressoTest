package com.ardenolgundemir.espressotestexample;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.ardenolgundemir.espressotestexample.controller.activity.MainActivity;
import com.ardenolgundemir.espressotestexample.controller.activity.TakeAPhotoActivity;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.Instrumentation.ActivityResult;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.ardenolgundemir.espressotestexample.ImageViewHasDrawableMatcher.hasDrawable;
import static org.hamcrest.EasyMock2Matchers.equalTo;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

/**
 * Created by ardenolgundemir on 4.06.2018.
 */

@RunWith(AndroidJUnit4.class)
public class TakeAPhotoActivityTest {
    @Rule
    public IntentsTestRule<TakeAPhotoActivity> mIntentsRule = new IntentsTestRule<>(
            TakeAPhotoActivity.class);
    Instrumentation.ActivityResult result;

    @Before
    public void setupImageUri() {

        Resources resources = InstrumentationRegistry.getTargetContext().getResources();
        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + resources
                .getResourcePackageName(
                        R.mipmap.ic_launcher) + '/' + resources.getResourceTypeName(
                R.mipmap.ic_launcher) + '/' + resources.getResourceEntryName(
                R.mipmap.ic_launcher));
        Intent resultData = new Intent();
        resultData.setData(imageUri);
        result = new Instrumentation.ActivityResult(
                Activity.RESULT_OK, resultData);

    }

//    @Test
//    public void openSelectImageGallery(){
//        onView(withId(R.id.button_take_photo)).perform(click());
//        intended(hasAction(equalTo(Intent.ACTION_PICK)));
//    }
    @Test
    public void testSelectImage() throws Exception{

        Thread.sleep(1000);
        //Check the image is not displayed
        onView(withId(R.id.imageView)).check(matches(not(hasDrawable())));
        Thread.sleep(1000);

        onView(withId(R.id.button_take_photo)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.imageView)).check(matches(hasDrawable()));
        Thread.sleep(1000);
//        //Setup the intent
////        Intents.init();
////        Matcher<Intent> expectedIntent = allOf(hasAction(Intent.ACTION_PICK),
////                hasData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
////        intending(expectedIntent).respondWith(result);
//
//        //Click the select button
////        intended(expectedIntent);
////        Intents.release();
//
//        //Check the image is displayed
//        onView(withId(R.id.imageView)).check(matches(hasDrawable()));

    }



    // STEP 1: CAMERA TESTING
//    @Before
//    public void stubCameraIntent(){
//       result = createImageCaptureActivityResultStub();
//        // Stub the Intent.
//        intending(hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(result);
//    }
//
////    @Test
////    public void takePhoto_shouldOpenCamera() {
////        onView(withId(R.id.button_take_photo)).perform(click());
////        intended(hasAction(equalTo(MediaStore.ACTION_IMAGE_CAPTURE)));
////    }
//
//    @Test
//    public void takePhoto_imageViewHasDrawable() {
//        // check the image view should not has drawable at the first time.
//        onView(withId(R.id.imageView)).check(matches(not(hasDrawable())));
//        // click on "Take a Photo" button, it will trigger the stubbed intend.
//        onView(withId(R.id.button_take_photo)).perform(click());
//        // now the image view should has drawable.
//        onView(withId(R.id.imageView)).check(matches(hasDrawable()));
//    }
//
//    private ActivityResult createImageCaptureActivityResultStub() {
//        // Put the drawable in a bundle.
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("data", BitmapFactory.decodeResource(
//                mIntentsRule.getActivity().getResources(), R.drawable.ic_launcher_foreground));
//
//        // Create the Intent that will include the bundle.
//        Intent resultData = new Intent();
//        resultData.putExtras(bundle);
//
//        // Create the ActivityResult with the Intent.
//        return new ActivityResult(Activity.RESULT_OK, resultData);
//    }


}
