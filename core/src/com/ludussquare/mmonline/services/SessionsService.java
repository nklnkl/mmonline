package com.ludussquare.mmonline.services;

import com.badlogic.gdx.Net.HttpResponseListener;

public class SessionsService extends HttpService {
	public SessionsService() {
		
	}
	
	public String registerSession (String username, String password, HttpResponseListener listener) {
		return "";
	}
	
	public void deleteSession (String sessionToken, HttpResponseListener listener) {
		
	}
}
