package com.ludussquare.mmonline.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ludussquare.mmonline.Mmonline;

public class MenuScreen extends GameScreen {
	
	// Each screen will have its own stage.
	private Stage stage;

	public MenuScreen(Mmonline game) {
		super(game);
		// TODO Auto-generated constructor stub
		
		// Get the viewport from game, and use it for stage.
		stage = new Stage(game.getView());
	}

}
