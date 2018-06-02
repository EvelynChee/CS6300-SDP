package edu.gatech.seclass.tccart;
/**
 * Created by changliu on 2016/3/21.
 */
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.Button;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class MainMenuTest extends
        ActivityInstrumentationTestCase2<MainMenuActivity> {

    private MainMenuActivity mActivity;
    private Button button;

    public MainMenuTest(){
        super(MainMenuActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        //start the activity
        mActivity = getActivity();
        button = (Button) mActivity
                .findViewById(R.id.buttonNewCustomer);
    }
    @Test
    public void testButtonNewCustomer() {
          ActivityMonitor am = getInstrumentation().addMonitor( //must include the package!
                          "edu.gatech.seclass.tccart.CustomerManagementEditActivity", null, false);


        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.performClick();
            }
        });

       //onView(withId(R.id.buttonNewCustomer)).perform(click()); //line 2

        am.waitForActivityWithTimeout(5000);
        //if activity is created, getHit()=1
        assertEquals(1, am.getHits());

    }
}
