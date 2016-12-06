package com.ludussquare.mmonline.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.ludussquare.mmonline.Mmonline;

public class MenuScreen extends GameScreen {

	private Image background;
	private Skin skin;
	
	private TextButton loginButton;
	private TextButton registerButton;
	private TextButton exitButton;
	
	private Table table;

	public MenuScreen(Mmonline game) {
		super(game);
		// TODO Auto-generated constructor stub
		setGraphics();
		setButtons();
		setTable();
	}
	
	private void setGraphics() {
		background = new Image(new Texture("menu/background.jpg"));
		background.setBounds(0, 0, game.getWidth(), game.getHeight());
		skin = new Skin(Gdx.files.internal("menu/skin.json"));
		stage.addActor(background);
	}
	
	private void setButtons() {
		loginButton = new TextButton("Login", skin);
		registerButton = new TextButton("Register", skin);
		exitButton = new TextButton("Exit", skin);
		
		loginButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				screenTransition(game.getPlayScreen());
			}
		});
		
		registerButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				screenTransition(game.getPlayScreen());
			}
		});
		
		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				Gdx.app.exit();
			}
		});
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

}
