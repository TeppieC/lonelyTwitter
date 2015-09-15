package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;
import java.util.Date;

/**
 * Created by teppie on 14/09/15.
 */
public class ImportantTweet extends Tweet implements Tweetable{
    public ImportantTweet(String tweet, Date date){
        super(tweet, date);
    }

    public ImportantTweet(String text) throws IOException{
        // this.text = text; as long as text in Tweet is protected
        super(text);
        this.setText(text);
        //Date date = new Date();
    }

    @Override
    public Boolean isImportant(){
        return Boolean.TRUE;
    }
}
