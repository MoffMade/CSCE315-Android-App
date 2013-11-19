package com.csce315_team_e.constellationexplorer;

import android.app.Application;

public class Star extends Application {
	
	private String star_id;
	private String star_type;
	private String name;
	private String catID;
	private String constellation;
	private int constellationID;
	private double ra;
	private double de;
	private double mag;
	
	public String getStarID() {
		return star_id;
	}
	public String getStarType() {
		return star_type;
	}
	public String getStarName() {
		return name;
	}
	public String getStarCatID() {
		return catID;
	}
	public String getStarConstellation() {
		return constellation;
	}
	public int getStarConstellationID() {
		return constellationID;
	}
	public double getRa() {
		return ra;
	}
	public double getDe() {
		return de;
	}
	public double getMag() {
		return mag;
	}
	
	public void setStarID(String val) {
		this.star_id = val;
	}
	public void setStarType(String val) {
		this.star_type = val;
	}
	public void setStarName(String val) {
		this.name = val;
	}
	public void setStarCatID(String val) {
		this.catID = val;
	}
	public void setStarConstellation(String val) {
		this.constellation = val;
	}
	public void setStarConstellationID(int val) {
		this.constellationID = val;
	}
	public void setRa(double val) {
		this.ra = val;
	}
	public void setDe(double val) {
		this.de = val;
	}
	public void setMag(double val) {
		this.mag = val;
	}

}
