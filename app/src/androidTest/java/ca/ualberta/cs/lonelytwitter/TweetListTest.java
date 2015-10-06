package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

import java.security.PublicKey;
import java.util.ArrayList;

/**
 * Created by teppie on 28/09/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 implements MyObservable{
    private boolean wasNotified = Boolean.FALSE;

    private volatile ArrayList<MyObserver> observers = new ArrayList<MyObserver>();

    public TweetListTest(){
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void addObserver(MyObserver observer){
        observers.add(observer);
    }

    public void myNotify(Object observervable){
        wasNotified = Boolean.TRUE;
    }

    public void testAddObserverr(){
        TweetList list = new TweetList();
        list.addObserver(this); // list is bounded with a observer
        wasNotified = Boolean.FALSE;
        list.addTweet(new NormalTweet("test")); //list was changed
        // at this point we expect to be notified
        assertTrue(wasNotified);
    }

    public void testTweetObserver(){
        TweetList list = new TweetList();
        list.addObserver(this);
        NormalTweet tweet = new NormalTweet("test");
        list.addTweet(tweet);
        wasNotified = Boolean.FALSE;
        tweet.setText("different");
        assertTrue(wasNotified);
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