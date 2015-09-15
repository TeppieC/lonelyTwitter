package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;
import java.util.Date;

/**
 * Created by teppie on 14/09/15.
 * We dont want to allow someone to go tweet.txt
 * We want to force them to call a method instead
 */
public abstract class Tweet extends Object{
    protected String text; // give access to child class
    private Date date;

    public Tweet(String text, Date date) {
        //super();
        this.text = text;
        this.date = date;
    }

    public Tweet(String text) {
        this.text = text;
        date = new Date();
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    //x.y in java is basically x.>y in C

    public void setText(String text) throws IOException{
        if (text.length() <= 140) {
            this.text = text;
        } else {
            throw new IOException("Tweet was too long");
        }
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // every class should have this method
    public abstract Boolean isImportant();
}
