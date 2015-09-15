package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by teppie on 14/09/15.
 */
public class BadMood extends CurrentMood {

    public BadMood(Date date) {
        super(date);
    }

    public BadMood() {
        super();
    }

    @Override
    public String isGood(){
        return "I am in a good mood";
    }
}
