/**
 * 
 */
package com.csce315_team_e.constellationexplorer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.StringReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.AsyncTask;
import android.util.Log;


public class XMLParser extends AsyncTask<String, Void, String> {
	
	private final String searchStarURL = "http://server1.sky-map.org/search?star=";
	
	public XMLParser() {

	}
	
	
	protected String doInBackground(String... url) {
		String xml = null;
		
		try {
			Log.i("XML Parser","CHECK POINT 3");

			HttpGet uri = new HttpGet(url[0]);    
			Log.i("XML Parser link",url[0]);

			DefaultHttpClient client = new DefaultHttpClient();
			
			HttpParams httpParameters = uri.getParams();
	        // Set the timeout in milliseconds until a connection is established.
	        int timeoutConnection = 150000;
	        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
	        // Set the default socket timeout (SO_TIMEOUT) 
	        // in milliseconds which is the timeout for waiting for data.
	        int timeoutSocket = 150000;
	        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
	        

			HttpResponse response = client.execute(uri);
			xml = EntityUtils.toString(response.getEntity());
			Log.i("XML STRING",xml);

			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace(); 
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return xml;
	}
	
	public Star getStarInfo(String star_name) throws XmlPullParserException {
		
		String star_data;
		
		Star star = new Star();

		String urlString = searchStarURL + star_name;

		try {
			
			//Parsing the returned xml string from server
			star_data = doInBackground(urlString);
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			
			xpp.setInput(new StringReader(star_data));
			String tagName = null;
			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch(eventType) {
				
					case XmlPullParser.START_DOCUMENT:
						break;
					
					case XmlPullParser.START_TAG:
						//get tag name
						tagName = xpp.getName();
						
						if(tagName.equals("status")) {
							if (xpp.nextText().equals("-1")) {
								//getting error response from server
								//handling error
								return null;
							}
						}
						
						//if tag is object
						if (tagName.equals("object")) {
							star.setStarID(xpp.getAttributeValue(null,"id"));
						}
						//if tag is type
						else if (tagName.equals("type")){
							star.setStarType(xpp.nextText());
						}
						//if tag is name
						else if (tagName.equals("request")) {
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
						if (tagName.equals("object") && star != null) {
							//return star 
							return star;
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
		
		return null;
		//return star_list;
	}
	
	
	

}
