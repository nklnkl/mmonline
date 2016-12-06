package com.ludussquare.mmonline.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.ludussquare.mmonline.Mmonline;

public abstract class GameScreen implements Screen {
	
	protected Mmonline game;
	
	protected Timer timer;
	
	// The amount of time it takes before an activity is exited.
	protected float transitionDelay, fadeInDelay;
	
	// State of splash screen.
	// 0: normal, 1: leaving, 2: left.
	private int transitionState;

	// Each screen will have its own stage.
	protected Stage stage;
	
	// The default skin ui.
	protected Skin defaultSkin;
	
	public GameScreen (Mmonline game) {
		this.game = game;
		
		setTransition();
		
		defaultSkin = new Skin(Gdx.files.internal("system/skin.json"));

		// Get the viewport from game, and use it for stage.
		stage = new Stage(game.getView());
	}
	
	private void setTransition() {
		// Default transition values.
		transitionState = 0;
		transitionDelay = 1f;
		fadeInDelay = 0.25f;
		timer = new Timer();
	}
	
	protected void screenTransition(final Screen screen) {
		// Change state.
		transitionState = 1;
		
		// Fade
		stage.addAction(Actions.sequence(Actions.fadeOut(transitionDelay)));
		
		// Start timer for leaving this activity.
		timer.scheduleTask(new Task() {
			@Override
			public void run() {
				transitionState = 2;
				// TODO Auto-generated method stub
				game.setScreen(screen);
			}
		}, transitionDelay);
	}
	
	protected int getTransitionState() {
		return transitionState;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		stage.addAction(Actions.fadeOut(0f));
		stage.addAction(Actions.fadeIn(fadeInDelay));
		
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
		defaultSkin.dispose();
	}
	
	public Stage getStage () {
		return stage;
	}

}
