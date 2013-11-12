package com.csce315_team_e.constellationexplorer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class StarMapActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_star_map);
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
    	startActivity(intent);
	}
}
