package com.ludussquare.mmonline.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponseListener;

public class HttpService {
	
	private HttpRequest request;
	private String host;
	
	public HttpService() {
		setRequest();
	}
	
	private void setRequest() {
		host = "";
		request = new HttpRequest();
		request.setHeader("Content-Type", "application/json");
		request.setHeader("Accept", "application/json");
	}
	
	// Process request, and run listener made by the object that calls this method.
	public void sendRequest(String uri, HttpResponseListener listener) {
		Gdx.net.sendHttpRequest(request, listener);
	}
}
