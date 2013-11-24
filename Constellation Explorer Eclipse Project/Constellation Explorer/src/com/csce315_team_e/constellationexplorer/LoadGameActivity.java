package com.csce315_team_e.constellationexplorer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.support.v4.app.NavUtils;

public class LoadGameActivity extends Activity {

	private List<Star> star_list;
	private List<String> stars_name;
	private Spinner saved_game_spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load_game);
		// Show the Up button in the action bar.
		setupActionBar();

		saved_game_spinner = (Spinner) findViewById(R.id.spinner1);
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			
			XmlPullParser xpp = factory.newPullParser();
			Log.i("check 1","SS");

		    StringBuffer buffer = new StringBuffer();

        	FileInputStream input = openFileInput("saved_data");
        	String line;
        	BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            if (input!=null) {                            
                while ((line = reader.readLine()) != null) {    
                    buffer.append(line + "\n" );
                }               
            }
            input.close();

			xpp.setInput(new StringReader(buffer.toString()));

			parseXML(xpp);

			stars_name = new ArrayList<String>();
			
			for (int i=0; i<star_list.size() ; i++) {
				Log.i("Star: ",star_list.get(i).getStarName());
				stars_name.add(star_list.get(i).getStarName());
			}
			//Set up list of saved games in the spinner1 widget
			// Create an ArrayAdapter using the string array and a default spinner layout
			

			 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,stars_name);
			saved_game_spinner.getSelectedItemPosition();

			// Specify the layout to use when the list of choices appears
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			// Apply the adapter to the spinner
			saved_game_spinner.setAdapter(adapter);
			
			
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private void parseXML(XmlPullParser xpp) {
		star_list = new ArrayList<Star>();
		Star star = new Star();
		Log.i("GOT parseXML","SS");
		try {
			
			//Parsing the returned xml string from file
			String tagName = null;
			int eventType = xpp.getEventType();
			
			while (eventType != XmlPullParser.END_DOCUMENT) {
				
				switch(eventType) {
				
					case XmlPullParser.START_DOCUMENT:
						break;
					
					case XmlPullParser.START_TAG:
						//get tag name
						tagName = xpp.getName();
						
						//if tag is object
						if (tagName.equals("object")) {
							star.setStarID(xpp.getAttributeValue(null,"id"));
						}
						//if tag is type
						else if (tagName.equals("type")){
							star.setStarType(xpp.nextText());
						}
						//if tag is name
						else if (tagName.equals("name")) {
							star.setStarName(xpp.nextText());
						}
						//if tag is catID
						else if (tagName.equals("catId")) {
							star.setStarCatID(xpp.nextText());
						}
						//if tag is constellation
						else if (tagName.equals("constellation")) {
							star.setStarConstellationID(xpp.getAttributeValue(null, "id"));
							star.setStarConstellation(xpp.nextText());
						}
						//if tag is ra
						else if (tagName.equals("ra")) {
							star.setRa(xpp.nextText());
						}
						//if tag is de
						else if (tagName.equals("de")) {
							star.setDe(xpp.nextText());
						}
						//if tag is mag
						else if (tagName.equals("mag")) {
							star.setMag(xpp.nextText());
						}	
						break;
					
					case XmlPullParser.END_TAG:
						tagName = xpp.getName();
						if (tagName.equals("saveData")) {
							//push star into arraylist
							star_list.add(star);
							Log.i("Done 1 star: ",star.getStarName());
							star = null;
							star = new Star();
						}
						break;
				} 
				// move to next 
				eventType = xpp.next();
			}
		
		} catch (XmlPullParserException e) {
			star = null;
		} catch (IOException e) {
			star = null;
		}
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.load_game, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void openStarMapActivity(View view){
		//get selected star
		int index = 0;

		index = saved_game_spinner.getSelectedItemPosition();
		Log.i("Picked index: ",Integer.toString(index));
		Log.i("star is: ",star_list.get(index).getStarName());

    	Intent intent = new Intent(this, StarMapActivity.class);
		intent.putExtra("current_star",star_list.get(index));
    	startActivity(intent);
    }
	public void returnToMainActivity(View view){
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
	}
}
