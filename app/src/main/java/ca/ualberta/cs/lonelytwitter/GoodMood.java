package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by teppie on 14/09/15.
 */
public class GoodMood extends CurrentMood {

    public GoodMood(Date date) {
        super(date);
    }

    public GoodMood() {
        super();
    }

    @Override
    public String isGood(){
        return "I am in a good mood";
    }

}
