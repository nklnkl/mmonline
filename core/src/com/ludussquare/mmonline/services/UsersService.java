package com.ludussquare.mmonline.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpResponseListener;

public class UsersService extends HttpService {
	
	public UsersService() {
	}
	
	public void getUsers(HttpResponseListener listener) {}
	
	public void registerUser(String username, String password, HttpResponseListener listener) {
		
		// Set up request.
		request.setUrl(host + "/users");
		request.setMethod("POST");
		
		// Set up body content to be sent.
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		request.setContent( gson.toJson(user) );
		
		Gdx.net.sendHttpRequest(request, listener);
		
	}
	
	public void deleteUser(String sessionToken, HttpResponseListener listener) {}
	
	public void updateUser(HttpResponseListener listener) {}
	
}
