package com.ludussquare.mmonline.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ludussquare.mmonline.Mmonline;

public abstract class GameScreen implements Screen {
	
	protected Mmonline game;

	// Each screen will have its own stage.
	protected Stage stage;
	
	public GameScreen (Mmonline game) {
		this.game = game;
		this.stage = stage;

		// Get the viewport from game, and use it for stage.
		stage = new Stage(game.getView());
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
		// Enable input on this screen's stage.
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		// Take all stage actors and run their update.
		stage.act();
		
		// Then draw the actors.
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
		stage.clear();
	}
	
	public Stage getStage () {
		return stage;
	}

}
