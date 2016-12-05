package com.ludussquare.mmonline;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ludussquare.mmonline.screens.MenuScreen;
import com.ludussquare.mmonline.screens.PlayScreen;
import com.ludussquare.mmonline.screens.SplashScreen;

public class Mmonline extends Game {
	
	// In game pixel global width and height. Has nothing to do with cameras, or scales. Just overall game width/height.
	private float width = 320;
	private float height = 240;
	
	// Create game camera and view.
	private OrthographicCamera camera;
	private Viewport view;
	
	private SplashScreen splashScreen;
	private MenuScreen menuScreen;
	private PlayScreen playScreen;
	
	@Override
	public void create () {
		view = new FitViewport(width, height);
		camera = new OrthographicCamera();
		
		splashScreen = new SplashScreen();
		menuScreen = new MenuScreen();
		playScreen = new PlayScreen();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}
	
	
	
	@Override
	public void resize(int width, int height) {
		view.update(width, height);
		super.resize(width, height);
	}

	@Override
	public void dispose () {
		super.dispose();
	}
}
