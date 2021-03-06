package com.ludussquare.mmonline.screens;

import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.google.gson.Gson;
import com.ludussquare.mmonline.Mmonline;
import com.ludussquare.mmonline.services.Session;
import com.ludussquare.mmonline.services.SessionsService;

public class LoginScreen extends GameScreen {
	
	private Image background;
	private TextField usernameField, passwordField;
	private Label usernameLabel, passwordLabel;
	private TextButton loginButton, backButton;
	private Table table;

	// The listener for when the login button is clicked.
	private ClickListener loginListener;
	
	// The service to access the sessions server api.
	private SessionsService sessionsService;
	
	// The http listener for when we get a response from the server.
	private HttpResponseListener loginResponseListner;
	
	public LoginScreen(Mmonline game) {
		super(game);
		setListeners();
		setGraphics();
		setTable();
	}
	
	private void setGraphics() {
		background = new Image(new Texture("login/background.jpg"));
		background.setBounds(0, 0, game.getWidth(), game.getHeight());
		stage.addActor(background);
		
		usernameLabel = new Label("Username:", defaultSkin);
		
		usernameField = new TextField("", defaultSkin);
		
		passwordLabel = new Label("Password:", defaultSkin);
		
		passwordField = new TextField("", defaultSkin);
		passwordField.setPasswordCharacter('*');
		passwordField.setPasswordMode(true);
		
		loginButton = new TextButton("Login", defaultSkin);
		loginButton.addListener(loginListener);
		
		backButton = new TextButton("Go Back", defaultSkin);
		backButton.addListener(backListener);
	}
	
	private void setTable() {
		table = new Table();
		table.setFillParent(true);
		table.left();
		stage.addActor(table);
		
		table.row();
		table.add(usernameLabel).expandX();
		table.add(usernameField).expandX();
		
		table.row().padTop(20f);
		table.add(passwordLabel).expandX();
		table.add(passwordField).expandX();
		
		table.row().pad(20f);
		table.add(backButton).expandX();
		table.add(loginButton).expandX();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		usernameField.setText("");
		passwordField.setText("");
		stage.setKeyboardFocus(usernameField);
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
	
	private void setListeners() {
		
		sessionsService =  new SessionsService();
		
		backScreen = game.getMenuScreen();
		
		loginListener = new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				
				// Send request to register session, and use class http listener.
				sessionsService.registerSession(usernameField.getText(), passwordField.getText(), loginResponseListner);
			}
		};
		
		loginResponseListner = new HttpResponseListener() {
			
			@Override
			public void handleHttpResponse(HttpResponse httpResponse) {
				
				if (httpResponse.getStatus().getStatusCode() == 200) {
					// Use gson for parsing.
					Gson gson = new Gson();
					
					// Get string body.
					String body = httpResponse.getResultAsString();
					
					// Create session object from string body.
					Session session = gson.fromJson(body, Session.class);
					
					// Save username
					game.setUsername(usernameField.getText());
					
					// Save in Mmonline game
					game.setSession(session);
					
					// Stop menu music.
					game.getMusic().stop();
					
					game.setScreen(game.getPlayScreen());
				} else {
					// "Wrong login credentials" logic here.
				}
			}
			
			@Override
			public void failed(Throwable t) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void cancelled() {
				// TODO Auto-generated method stub
				
			}
		};
	}
}
