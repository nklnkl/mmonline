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

	private Image background;
	private TextField usernameField, passwordField, confirmField;
	private Label usernameLabel, passwordLabel, confirmLabel;
	private TextButton registerButton, backButton;
	private Table table;
	
	public RegisterScreen(Mmonline game) {
		super(game);
		// TODO Auto-generated constructor stub
		setGraphics();
		setTable();
	}
	
	private void setGraphics() {
		
		background = new Image(new Texture("register/background.jpg"));
		background.setBounds(0, 0, game.getWidth(), game.getHeight());
		stage.addActor(background);
		
		usernameField = new TextField("", defaultSkin);
		passwordField = new TextField("", defaultSkin);
		passwordField.setPasswordMode(true);
		confirmField = new TextField("", defaultSkin);
		confirmField.setPasswordMode(true);
		
		usernameLabel = new Label("Username:", defaultSkin);
		passwordLabel = new Label("Password:", defaultSkin);
		confirmLabel = new Label("Confirm Password:", defaultSkin);
		
		registerButton = new TextButton("Register", defaultSkin);
		backButton = new TextButton("Go Back", defaultSkin);
		
		registerButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
			}
		});
		
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				screenTransition(game.getMenuScreen());
			}
		});
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
