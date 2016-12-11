package com.ludussquare.mmonline.services;

import com.badlogic.gdx.Net.HttpRequest;
import com.google.gson.Gson;

public class HttpService {
	
	protected HttpRequest request;
	protected String host;
	protected Gson gson;
	
	public HttpService() {
		gson = new Gson();
		setRequest();
	}
	
	private void setRequest() {
		host = "https://mm-online-server.herokuapp.com";
		request = new HttpRequest();
		request.setHeader("Content-Type", "application/json");
		request.setHeader("Accept", "application/json");
	}
}
