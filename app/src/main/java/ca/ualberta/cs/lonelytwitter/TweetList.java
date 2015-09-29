package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by teppie on 28/09/15.
 */
public class TweetList {
    private ArrayList<Tweet> tweets  = new ArrayList<Tweet>();

    public void addTweet(Tweet tweet){
        if (!this.hasTweet(tweet)){
            tweets.add(tweet);
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

}
