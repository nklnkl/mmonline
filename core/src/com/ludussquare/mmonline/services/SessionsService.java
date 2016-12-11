package com.ludussquare.mmonline.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpResponseListener;

public class SessionsService extends HttpService {
	
	public SessionsService() {
		
	}
	
	public void registerSession (String username, String password, HttpResponseListener listener) {
		
		// Set up request.
		request.setUrl(host + "/sessions");
		request.setMethod("POST");
		
		// Set up body content to be sent.
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		request.setContent( gson.toJson(user) );
		
		Gdx.net.sendHttpRequest(request, listener);
		
	}
	
	public void deleteSession (String sessionToken, HttpResponseListener listener) {
		
	}
}
