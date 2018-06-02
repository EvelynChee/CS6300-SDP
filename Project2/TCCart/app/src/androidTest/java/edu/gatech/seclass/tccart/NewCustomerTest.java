package edu.gatech.seclass.tccart;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.Button;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.seclass.tccart.db.CustomerDao;
import edu.gatech.seclass.tccart.db.DaoSession;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by changliu on 2016/3/24.
 */

//@RunWith(AndroidJUnit4.class)
//@LargeTest
public class NewCustomerTest  extends ActivityInstrumentationTestCase2<CustomerManagementEditActivity> {

    public NewCustomerTest(){
        super(CustomerManagementEditActivity.class);
    }
    private CustomerManagementEditActivity mActivity;

    private String name = "John Smith";
    private String email = "JSmith@gmail.com";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        //start the activity
        mActivity = getActivity();

    }
    /*
    @Rule
    public ActivityTestRule<CustomerManagementEditActivity> mActivityRule = new ActivityTestRule<>(
            CustomerManagementEditActivity.class);
    */
    @Test
    public void testNormalInput() {
        onView(withId(R.id.et_Name)).perform(typeText(name), closeSoftKeyboard()); //type name
        onView(withId(R.id.et_Email)).perform(typeText(email), closeSoftKeyboard()); //type email

        onView(withId(R.id.btn_EditCustomer)).perform(click()); //press SAVE COSTUMER INFORMATION

    /*
        DaoSession session = mActivity.daoTCCartMaster.newSession();
        CustomerDao customerDao = session.getCustomerDao();
        //TODO : Check Customer exists with same name before insert/update record.

        String existingCustomerUniqueID = getUniqueIDByEmail(mActivity.customerDao, currentCustomer);
        //String expectedText = STRING_TO_BE_TYPED;
        //System.out.println("due");
        //onView(withId(R.id.totalENOB)).check(matches(withText(expectedText))); //line 3
    */
    }

}
