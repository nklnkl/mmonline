/*
 * 
 */
package com.ludussquare.mmonline.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
	
	// The accept sound.
	private Sound acceptSound;
	
	// A back button listener every sub class can use.
	// It inevitably calls a setScreen for the backScreen.
	@SuppressWarnings("unused")
	protected ClickListener backListener;
	
	// The screen this screen will go to if the backListener is invoked.
	// This defaults to null and must be set by a sub class.
	@SuppressWarnings("unused")
	protected GameScreen backScreen;
	
	public GameScreen (Mmonline game) {
		this.game = game;
		
		// Get the viewport from game, and use it for stage.
		stage = new Stage(game.getView());
		
		setAssets();
		
		setBackListener();
		
		setTransition();
	}
	
	private void setAssets() {
		defaultSkin = new Skin(Gdx.files.internal("system/skin.json"));
		acceptSound = Gdx.audio.newSound(Gdx.files.internal("system/accept.mp3"));
	}
	
	private void setTransition() {
		// Default transition values.
		transitionState = 0;
		transitionDelay = 1f;
		fadeInDelay = 0.25f;
		timer = new Timer();
	}
	
	private void setBackListener() {
		backListener = new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				// If a backScreen is defined, transition to it. Otherwise don't do anything.
				if (backScreen != null) screenTransition(backScreen);
			}
		};
	}
	
	protected void screenTransition(final Screen screen) {
		// Play accept sound.
		acceptSound.play();
		
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
		System.out.println("Screen: " + this.toString());
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
