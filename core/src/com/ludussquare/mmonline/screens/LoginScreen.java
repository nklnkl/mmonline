package com.ludussquare.mmonline.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ludussquare.mmonline.Mmonline;

public class LoginScreen extends GameScreen {
	
	private Image background;
	private TextField usernameField, passwordField;
	private Label usernameLabel, passwordLabel;
	private TextButton loginButton, backButton;
	private Skin skin;
	private Table table;
	
	public LoginScreen(Mmonline game) {
		super(game);
		setGraphics();
		setTable();
	}
	
	private void setGraphics() {
		skin = new Skin(Gdx.files.internal("login/skin.json"));
		
		background = new Image(new Texture("login/background.jpg"));
		background.setBounds(0, 0, game.getWidth(), game.getHeight());
		stage.addActor(background);
		
		usernameField = new TextField("", skin);
		passwordField = new TextField("", skin);
		
		usernameLabel = new Label("Username:", skin);
		passwordLabel = new Label("Password:", skin);
		
		loginButton = new TextButton("Login", skin);
		backButton = new TextButton("Go Back", skin);
		
		loginButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
			}
		});
		
		backButton.addListener(new ClickListener(){@Override
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
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		skin.dispose();
	}
}
