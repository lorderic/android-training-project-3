package com.example.gridimagesearch.models;

import java.io.Serializable;

public class SearchFilters implements Serializable{

	private static final long serialVersionUID = 7524683363546742751L;
	private String size;
	private String color;
	private String type;
	private String site;
	
	public SearchFilters(){
		size = "";
		color = "";
		type = "";
		site = "";
	}
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	
	

}
