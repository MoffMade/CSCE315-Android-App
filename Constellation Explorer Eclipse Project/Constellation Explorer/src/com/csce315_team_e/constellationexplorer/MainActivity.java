package com.csce315_team_e.constellationexplorer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    //called when load game button is clicked
    public void openLoadGameActivity(View view){
    	Intent intent = new Intent(this, LoadGameActivity.class);
    	startActivity(intent);
    }
  //called when new game button is clicked
   /* public void openStarMapActivity(View view){
    	Intent intent = new Intent(this, GameActivity.class);
    	startActivity(intent);
    } */
  //called when help button is clicked
    public void openHelpActivity(View view){
    	Intent intent = new Intent(this, HelpActivity.class);
    	startActivity(intent);
    }
}
