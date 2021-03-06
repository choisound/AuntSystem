package com.cyx.pojo;

public class LocationInfo {  
    public static final double MINLAT = -90;  
    public static final double MAXLAT = 90;  
    public static final double MINLNG = -180;  
    public static final double MAXLNG = 180;  
    private double lat;//γ��[-90,90]  
    private double lng;//����[-180,180]  
    private String geoHash;
    public String getGeoHash() {
		return geoHash;
	}
	public void setGeoHash(String geoHash) {
		this.geoHash = geoHash;
	}
	public LocationInfo(double lat, double lng) {  
        this.lat = lat;  
        this.lng = lng;  
    }  
    public double getLat() {  
        return lat;  
    }  
    public void setLat(double lat) {  
        this.lat = lat;  
    }  
    public double getLng() {  
        return lng;  
    }  
    public void setLng(double lng) {  
        this.lng = lng;  
    }  
}  