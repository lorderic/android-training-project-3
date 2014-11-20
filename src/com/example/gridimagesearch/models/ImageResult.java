package com.example.gridimagesearch.models;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageResult implements Serializable{
	private static final long serialVersionUID = 3696906178614240610L;
	public String thumbURL;
	public String fullURL;
	public String title;
	
	public ImageResult(JSONObject json){
		try {
			this.fullURL = json.getString("url");
			this.thumbURL = json.getString("tbUrl");
			this.title = json.getString("title");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<ImageResult> fromJSONArray(JSONArray array){
		ArrayList<ImageResult> results = new ArrayList<ImageResult>();
		for (int i = 0; i < array.length(); i++){
			try {
				results.add(new ImageResult(array.getJSONObject(i)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return results;
	}

}
