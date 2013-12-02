/*

	To see the saved_data file : Window > ShowView >FileExplorer 
	and look for data > data > com.csce313... > files > saved_data
	click on file -> pull file from device
	
	Android won't allow us to modify any file (star_data.xml) inside this package at run time. 
	The only thing we can save data information is to use internal or external storage
	
	The file will be in MODE_APPEND. in order to clear everything -> wipe user data before stating 

*/
package com.csce315_team_e.constellationexplorer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

import org.xmlpull.v1.XmlSerializer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.Xml;
import android.view.View;

public class SaveGameActivity extends Activity {

	private Star star;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_save_game);
        
		//get current star from StarMapActivity
		Bundle data = getIntent().getExtras();
		
		star = (Star) data.getParcelable("current_star");
		

        try {
        	
        	
        	FileOutputStream saveFile = openFileOutput("saved_data", Context.MODE_APPEND);
            XmlSerializer output = Xml.newSerializer();
            StringWriter writer = new StringWriter();

            output.setOutput(writer);
            output.startTag(null,"saveData");
            output.startTag(null,"object");
            output.attribute(null,"id",star.getStarID());
            
            output.startTag(null,"type");
            output.text(star.getStarType());
            output.endTag(null, "type");
            
            output.startTag(null, "name");
            output.text(star.getStarName());
            output.endTag(null, "name");

            output.startTag(null, "catId");
            output.text(star.getStarCatID());
            output.endTag(null, "catId");
			
            output.startTag(null, "constellation");
            output.attribute(null,"id",star.getStarConstellationID());
            output.text(star.getStarConstellation());
            output.endTag(null, "constellation");

            output.startTag(null, "ra");
            output.attribute(null, "unit", "hour");
            output.text(star.getRa());
            output.endTag(null, "ra");

            output.startTag(null, "de");
            output.attribute(null, "unit", "degree");
            output.text(star.getDe());
            output.endTag(null, "de");
            
            output.startTag(null, "mag");
            output.text(star.getMag());
            output.endTag(null, "mag");
            
            output.startTag(null, "timeSaved");
            
            Date d = new Date();
            CharSequence s  = DateFormat.format("MM-dd-yyyy hh:mm:ss", d.getTime());
            output.text((String) s);
            output.endTag(null, "timeSaved");
            
            output.endTag(null,"object");
            output.endTag(null, "saveData");
            
            output.endDocument();
            output.flush();

            saveFile.write(writer.toString().getBytes());
            saveFile.close();
            
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        } catch (IllegalArgumentException e) {
        	e.printStackTrace();
        } catch (IllegalStateException e) {
        	e.printStackTrace();
        }
        
        //after saving, returning to MainActive
    }
	public void returnToMainActivity(View view){
    	Intent intent = new Intent(this, StarMapActivity.class);
    	startActivity(intent);
	}
    
}
