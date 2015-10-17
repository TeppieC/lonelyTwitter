package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import junit.framework.TestCase;

/**
 * Created by wz on 14/09/15.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {
    EditText bodyText;
    Button saveButton;
    String tweetText;
    ListView oldTweetList;
    EditText editText;


    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();

    }

    public void testEditTweet(){
        LonelyTwitterActivity activity = (LonelyTwitterActivity) getActivity();

        activity.getTweets().clear();

        // set up an activity monitor
        Instrumentation.ActivityMonitor recieverActivityMonitor =
                getInstrumentation().addMonitor(EditTweetActivity.class.getName(), null, false);

        tweetText = "Hello!";
        bodyText = activity.getBodyText();
        saveButton = activity.getSaveButton();


        //CalledFromWrongThreadException occurs, so...
        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText(tweetText);
            }
        });
        getInstrumentation().waitForIdleSync();/////

        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        assertTrue(activity.getTweets().get(0).getText().equals(tweetText));


        // get the list of tweets from the view
        oldTweetList = activity.getOldTweetsList();
        Tweet newestTweet = (Tweet) oldTweetList.getItemAtPosition(0); // have to cast
        assertEquals(tweetText, newestTweet.getText());


        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetList.getChildAt(0);
                oldTweetList.performItemClick(v, 0, v.getId());
            }
        });
        getInstrumentation().waitForIdleSync();

        //validate that the recieverActivity is started
        EditTweetActivity recieverActivity = (EditTweetActivity)
                recieverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("Reciver Activity is null", recieverActivity);
        assertEquals("Monitor for ReveriverActivity has not been called", 1, recieverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type", EditTweetActivity.class, recieverActivity.getClass());

        //Remove the ActivityMonitor
        getInstrumentation().removeMonitor(recieverActivityMonitor);

        editText = (EditText)recieverActivity.getEditText();
        // test that the editor starts up with the right tweet in it to edit
        assertEquals("Not showed right tweet", editText.getText(), activity.getTweets().get(0).getText());
        // test that we can edit that tweet
        activity.runOnUiThread(new Runnable() {
            public void run() {
                editText.setText("Heyyy");
            }
        });
        getInstrumentation().waitForIdleSync();
        // test that we can push some kind of save button for that tweet

        // the new modified tweet text was actually saved
        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText(tweetText);
                saveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        assertTrue(activity.getTweets().get(0).getText().equals(tweetText));

        // the new modified tweet text is displayed on the other activity

        // clean up our activities at the end of our test
        recieverActivity.finish();
    }
}