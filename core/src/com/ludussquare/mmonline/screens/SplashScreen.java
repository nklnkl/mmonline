package com.ludussquare.mmonline.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.ludussquare.mmonline.Mmonline;

public class SplashScreen extends GameScreen {
	
	// Background
	private Image background;
	
	public SplashScreen(Mmonline game) {
		super(game);
		// TODO Auto-generated constructor stub
		
		// Set up textures and images.
		setGraphics();
	}
	
	private void setGraphics () {
		background = new Image(new Texture("splashBackground.jpg"));
		background.setBounds(0, 0, game.getWidth(), game.getHeight());
		stage.addActor(background);
	}
	
	
}
