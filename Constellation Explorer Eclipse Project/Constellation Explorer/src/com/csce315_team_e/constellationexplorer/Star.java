package com.csce315_team_e.constellationexplorer;


import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;

public class Star extends Application implements Parcelable {
	
	private String star_id;
	private String star_type;
	private String name;
	private String catID;
	private String constellation;
	private String constellationID;
	private String ra;
	private String de;
	private String mag;
	private String timeSaved;
	public Star() {
		
	}
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
	public String getStarConstellationID() {
		return constellationID;
	}
	public String getRa() {
		return ra;
	}
	public String getDe() {
		return de;
	}
	public String getMag() {
		return mag;
	}
	public String getTimeSaved(){
		return timeSaved;
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
	public void setStarConstellationID(String val) {
		this.constellationID = val;
	}
	public void setRa(String val) {
		this.ra = val;
	}
	public void setDe(String val) {
		this.de = val;
	}
	public void setMag(String val) {
		this.mag = val;
	}
	public void setTimeSaved(String val) {
		this.timeSaved = val;
	}

	
    public Star(Parcel in){
        String[] data = new String[10];

        in.readStringArray(data);
        this.star_id = data[0];
        this.star_type = data[1];
        this.name = data[2];
        this.catID = data[3];
        this.constellation = data[4];
        this.constellationID = data[5];
        this.ra = data[6];
        this.de = data[7];
        this.mag = data[8];
        this.timeSaved = data[9];
    }

    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.star_id, this.star_type, this.name, this.catID, this.constellation,
                                            this.constellationID, this.ra, this.de,this.mag,this.timeSaved});
    }
    public static final Parcelable.Creator<Star> CREATOR = new Parcelable.Creator<Star>() {
        public Star createFromParcel(Parcel in) {
            return new Star(in); 
        }

        public Star[] newArray(int size) {
            return new Star[size];
        }
    };
}
