package com.ludussquare.mmonline.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ludussquare.mmonline.Mmonline;

public class RegisterScreen extends GameScreen {

	// Graphics
	private Image background;
	private TextField usernameField, passwordField, confirmField;
	private Label usernameLabel, passwordLabel, confirmLabel;
	private TextButton registerButton, backButton;
	
	// Event handlers/listeners
	private ClickListener registerListener;
	
	// UI format
	private Table table;
	
	public RegisterScreen(Mmonline game) {
		super(game);
		setListeners();
		setGraphics();
		setTable();
	}

	private void setListeners() {
		backScreen = game.getMenuScreen();
		registerListener = new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
			}
		};
	}
	
	private void setGraphics() {
		
		// Set up background and add to stage.
		background = new Image(new Texture("register/background.jpg"));
		background.setBounds(0, 0, game.getWidth(), game.getHeight());
		stage.addActor(background);

		// Set up fields and don't add to stage.
		usernameLabel = new Label("Username:", defaultSkin);
		usernameField = new TextField("", defaultSkin);
		passwordLabel = new Label("Password:", defaultSkin);
		passwordField = new TextField("", defaultSkin);
		passwordField.setPasswordMode(true);
		confirmLabel = new Label("Confirm Password:", defaultSkin);
		confirmField = new TextField("", defaultSkin);
		confirmField.setPasswordMode(true);
		
		// Set up register button and its corresponding listener object.
		registerButton = new TextButton("Register", defaultSkin);
		registerButton.addListener(registerListener);
		
		// Set up back button and its corresponding listener object.
		backButton = new TextButton("Go Back", defaultSkin);
		// In this case, it is the GameScreen's inherited backListener.
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
		
		table.row().padTop(20f);
		table.add(confirmLabel).expandX();
		table.add(confirmField).expandX();
		
		table.row().pad(20f);
		table.add(backButton).expandX();
		table.add(registerButton).expandX();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		usernameField.setText("");
		passwordField.setText("");
		confirmField.setText("");
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

}
