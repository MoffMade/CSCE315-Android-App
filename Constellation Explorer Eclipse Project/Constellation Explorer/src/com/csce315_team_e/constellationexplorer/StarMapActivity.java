package com.csce315_team_e.constellationexplorer;


import java.util.Random;

import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class StarMapActivity extends Activity {

	private Star star;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_star_map);
		
		String[] star_names = {"polaris","segin","Cassiopeia","Taurus","Pictor"};
		
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
		Bundle data = getIntent().getExtras();
		if (data != null) {
		star = (Star) data.getParcelable("current_star");
		} else {
			Log.i("INFO","DATA is NULL");
			 XMLParser getData = new XMLParser();
			try {
				
				// Pick a random star in the list to be the current star
				Random r = new Random();
				int rand =r.nextInt(5);
				
				star = getData.getStarInfo(star_names[rand]);
				Log.i("Picked star: ",star.getStarName());

			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.star_map, menu);
		return true;
	}
	
	public void returnToMainActivity(View view){
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
	}
	
	public void goToGame(View view){
    	Intent intent = new Intent(this, GameActivity.class);
		intent.putExtra("current_star",star);
    	startActivity(intent);
	}
	public void goToSaveGame(View view) {
		Intent intent = new Intent(this, SaveGameActivity.class);
		intent.putExtra("current_star",star);
		startActivity(intent);
	}

}
