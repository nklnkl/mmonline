package com.ludussquare.mmonline;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ludussquare.mmonline.screens.ExitScreen;
import com.ludussquare.mmonline.screens.GameScreen;
import com.ludussquare.mmonline.screens.LoginScreen;
import com.ludussquare.mmonline.screens.MenuScreen;
import com.ludussquare.mmonline.screens.PlayScreen;
import com.ludussquare.mmonline.screens.RegisterScreen;
import com.ludussquare.mmonline.screens.SplashScreen;

public class Mmonline extends Game {
	
	// In game pixel global width and height. Has nothing to do with cameras, or scales. Just overall game width/height.
	private float width = 320;
	private float height = 240;
	
	// Create game camera and view.
	private Viewport view;
	
	// The defined screens of this game.
	private SplashScreen splashScreen;
	private MenuScreen menuScreen;
	private PlayScreen playScreen;
	private LoginScreen loginScreen;
	private RegisterScreen registerScreen;
	private ExitScreen exitScreen;
	
	// The handler that should play music.
	private Music music;
	
	@Override
	public void create () {
		view = new FitViewport(width, height);
		view.setCamera(new OrthographicCamera());
		
		splashScreen = new SplashScreen(this);
		menuScreen = new MenuScreen(this);
		playScreen = new PlayScreen(this);
		loginScreen = new LoginScreen(this);
		registerScreen = new RegisterScreen(this);
		exitScreen = new ExitScreen(this);
		
		setScreen(splashScreen);
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
		splashScreen.dispose();
		menuScreen.dispose();
		playScreen.dispose();
		loginScreen.dispose();
		registerScreen.dispose();
		exitScreen.dispose();
	}

	public SplashScreen getSplashScreen() {
		return splashScreen;
	}

	public MenuScreen getMenuScreen() {
		return menuScreen;
	}

	public PlayScreen getPlayScreen() {
		return playScreen;
	}
	
	public LoginScreen getLoginScreen() {
		return loginScreen;
	}
	
	public RegisterScreen getRegisterScreen() {
		return registerScreen;
	}
	
	public ExitScreen getExitScreen() {
		return exitScreen;
	}
	
	public Music getMusic() {
		return music;
	}
	
	public void setMusic(Music music) {
		this.music = music;
	}
	
	public void playScreenMusic(Music music) {
		
		// If the music instance is already playing, return early.
		if (this.music == music) return;
		
		if (this.music != null) {
			this.music.stop();
			this.music.dispose();
		}
		setMusic(music);
		this.music.setLooping(true);
		this.music.play();
	}
	
	public Viewport getView() {
		return view;
	}
	public float getWidth () {
		return width;
	}
	public float getHeight () {
		return height;
	}
	
	
}
