package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LonelyTwitterActivity extends Activity implements MyObserver{

	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;
	private ArrayList<Tweet> tweets;
	private ArrayAdapter<Tweet> adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {//view

		super.onCreate(savedInstanceState);//view
		setContentView(R.layout.main);//view

		bodyText = (EditText) findViewById(R.id.body);//view
		Button saveButton = (Button) findViewById(R.id.save);//view
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);//view

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString(); // controller
				tweets.add(new NormalTweet(text)); // controller
				saveInFile(); // model
				adapter.notifyDataSetChanged();// view
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		loadFromFile(); // model
		if (tweets == null){
			//throw new RuntimeException();
		}
		adapter = new ArrayAdapter<Tweet>(this, R.layout.list_item, tweets);// controller
		oldTweetsList.setAdapter(adapter);// controller
	}

	private void loadFromFile() {
		//For IO persistence
		try {// model
			FileInputStream fis = openFileInput(FILENAME); // model
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));// model
			Gson gson = new Gson(); // model
			// Following line based on https://google.gson.googlecode.com/svn
			Type listType = new TypeToken<ArrayList<NormalTweet>>() {}.getType(); // model
			tweets = gson.fromJson(in, listType);// model

		} catch (FileNotFoundException e) { // model
			tweets = new ArrayList<Tweet>(); // model
		} catch (IOException e) { // model
			throw new RuntimeException(e); // model
		}
	}
	
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					0);// model
			OutputStreamWriter writer = new OutputStreamWriter(fos);// model
			Gson gson = new Gson();// model
			gson.toJson(tweets, writer); // model
			writer.flush();// model
			fos.close();// model
		} catch (FileNotFoundException e) {// model
			throw new RuntimeException(e);// model
		} catch (IOException e) {// model
			throw new RuntimeException(e);// model
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_lonely, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}
		if (id == R.id.action_secondAct){
			Intent intent = new Intent(LonelyTwitterActivity.this, secondActivity.class);
			startActivity(intent);
		}

		return super.onOptionsItemSelected(item);
	}

	public void myNotify(MyObservable observable){
	}
}