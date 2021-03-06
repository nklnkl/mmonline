package com.ludussquare.mmonline.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.ludussquare.mmonline.Mmonline;

public class MenuScreen extends GameScreen {

	// Events
	private ClickListener loginListener, registerListener, exitListener;
	
	// Graphics
	private Image background;
	private TextButton loginButton, registerButton, exitButton;
	private Label title;
	
	// UI format
	private Table table;
	
	// Audio
	private Music music;

	public MenuScreen(Mmonline game) {
		super(game);
		// TODO Auto-generated constructor stub
		setListeners();
		setGraphics();
		setButtons();
		setTable();
		music = Gdx.audio.newMusic(Gdx.files.internal("menu/music.wav"));
	}
	
	private void setListeners() {
		loginListener = new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				screenTransition(game.getLoginScreen());
			}
		};
		registerListener = new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				screenTransition(game.getRegisterScreen());
			}
		};
		exitListener = new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				screenTransition(game.getExitScreen());
			}
		};
	}
	
	private void setGraphics() {
		background = new Image(new Texture("menu/background.jpg"));
		background.setBounds(0, 0, game.getWidth(), game.getHeight());
		stage.addActor(background);
	}
	
	private void setButtons() {
		loginButton = new TextButton("Login", defaultSkin);
		loginButton.addListener(loginListener);
		
		registerButton = new TextButton("Register", defaultSkin);
		registerButton.addListener(registerListener);
		
		exitButton = new TextButton("Exit", defaultSkin);
		exitButton.addListener(exitListener);
	}
	
	private void setTable() {
		table = new Table();
		table.setFillParent(true);
		table.left();
		table.padLeft(40f);
		stage.addActor(table);
		
		table.row().padBottom(20f);
		table.add(loginButton).expandX().align(Align.left);
		table.row().padBottom(20f);
		table.add(registerButton).expandX().align(Align.left);
		table.row().padBottom(20f);
		table.add(exitButton).expandX().align(Align.left);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		game.playScreenMusic(music);
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		music.dispose();
	}

}
