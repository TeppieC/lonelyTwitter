package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

import java.security.PublicKey;

/**
 * Created by teppie on 28/09/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 {
    public TweetListTest(){
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testAddTweet(){
        // test the functionality of add method
        TweetList list = new TweetList();
        list.addTweet(new NormalTweet("test"));
    }

    public void testGetTweets(){
        TweetList list = new TweetList();
        Tweet tweet1 = new NormalTweet("test1");
        list.addTweet(tweet1);
        Tweet tweet2 = new NormalTweet("test2");
        list.addTweet(tweet2);
        Tweet tweet3 = new NormalTweet("test3");
        list.addTweet(tweet3);
        Tweet tweet4 = new NormalTweet("test4");
        list.addTweet(tweet4);
        System.out.print(String.valueOf(list.getTweets()));
        assertTrue((String.valueOf(list.getTweets().get(0).getText()) == "test1")
                &&(String.valueOf(list.getTweets().get(1).getText()) == "test2")
                &&(String.valueOf(list.getTweets().get(2).getText()) == "test3")
                &&(String.valueOf(list.getTweets().get(3).getText()) == "test4"));
    }

    public void testHasTweet(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.addTweet(tweet);
        assertTrue(list.hasTweet(tweet));
    }

    public void testRemoveTweet(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.addTweet(tweet);
        list.removeTweet(tweet);
        assertFalse(list.hasTweet(tweet));
    }

    public void testGetCount(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.addTweet(tweet);
        assertTrue(list.getCount()==1);
    }

}