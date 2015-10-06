package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Observer;

/**
 * Created by teppie on 28/09/15.
 */
public class TweetList implements MyObservable , MyObserver{
    private ArrayList<Tweet> tweets  = new ArrayList<Tweet>();
    private volatile ArrayList<MyObserver> observers = new ArrayList<MyObserver>();

    private void notifyAllObservers(){
        for (MyObserver observer : observers ){
            observer.myNotify(this);
        }
    }

    public void addTweet(Tweet tweet){
        if (!this.hasTweet(tweet)){
            tweets.add(tweet);
            notifyAllObservers();
        }else{
            throw new IllegalArgumentException();
        }
    }

    public ArrayList<Tweet> getTweets(){
        //Collections.sort(tweets, tweets.D);
        return tweets;
    }

    public void removeTweet(Tweet tweet){
        tweets.remove(tweet);
    }

    public boolean hasTweet(Tweet tweet){
        return  tweets.contains(tweet);
    }

    public int getCount(){
        return tweets.size();
    }

    public void addObserver(MyObserver observer){
        observers.add(observer);
    }

    public void myNotify(MyObservable observable){
        notifyAllObservers();
    }
}
