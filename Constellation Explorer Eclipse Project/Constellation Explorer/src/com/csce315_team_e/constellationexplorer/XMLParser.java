/**
 * 
 */
package com.csce315_team_e.constellationexplorer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


class Star {
	public String star_id;
	public String star_type;
	public String name;
	public String catID;
	public String constellation;
	public int constellationID;
	public double ra;
	public double de;
	public double mag;
	
	
}
public class XMLParser {
	
	private final String searchStarURL = "http://server1.sky-map.org/search?star=";
	
	public XMLParser() {

	}
	
	
	private String retrieveXMLFromURL(String url) {
		String xml = null;
		
		try {
			
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity httpEntity = response.getEntity();
			
			xml = EntityUtils.toString(httpEntity);
		
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace(); 
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return xml;
	}
	
	public ArrayList<Star> getStarInfo(String star_name) throws XmlPullParserException {
		
		String url = searchStarURL + star_name;
		String star_data;
		
		ArrayList<Star> star_list = new ArrayList<Star>();

		
		//retrieve the XML String from searching on server
		star_data = retrieveXMLFromURL(url);

		Star star = null;
		try {
			
			//Parsing the returned xml string from server
			
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
							}
						}
						
						//if tag is object
						if (tagName.equals("object")) {
							star = new Star();
							star.star_id = xpp.getAttributeValue(null,"id");
						}
						//if tag is type
						else if (tagName.equals("type")){
							star.star_type = xpp.nextText();
						}
						//if tag is name
						else if (tagName.equals("name")) {
							star.name = xpp.nextText();
						}
						//if tag is catID
						else if (tagName.equals("catID")) {
							star.catID = xpp.nextText();
						}
						//if tag is constellation
						else if (tagName.equals("constellation")) {
							star.constellationID = Integer.parseInt(xpp.getAttributeValue(null, "id"));
							star.constellation = xpp.nextText();
						}
						//if tag is ra
						else if (tagName.equals("ra")) {
							star.ra = Double.parseDouble(xpp.nextText());
						}
						//if tag is de
						else if (tagName.equals("de")) {
							star.de = Double.parseDouble(xpp.nextText());
						}
						//if tag is mag
						else if (tagName.equals("mag")) {
							star.mag = Double.parseDouble(xpp.nextText());
						}	
						break;
					
					case XmlPullParser.END_TAG:
						tagName = xpp.getName();
						if (tagName.equals("object") && star != null) {
							//add the star to the lsit
							star_list.add(star);
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
		
		
		return star_list;
	}
	
	
	

}
