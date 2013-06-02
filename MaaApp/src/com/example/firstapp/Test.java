package com.example.firstapp;

import org.apache.http.NameValuePair;

public class Test implements NameValuePair {

	private String name;
	private String value;
	
	public Test(String name, String value){
		this.name = name;
		this.value = value;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getValue() {
		return value;
	}

}
