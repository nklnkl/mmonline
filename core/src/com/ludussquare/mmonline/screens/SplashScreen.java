package com.ludussquare.mmonline.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.ludussquare.mmonline.Mmonline;

public class SplashScreen extends GameScreen {
	
	// Background
	private Image background;
	
	// Title and instructions table.
	private Table titleTable;
	
	// Splash screen label elements.
	private Label title, author1, author2, author3, pressToEnter;
	
	// The alpha state of the pressToEnter label.
	// 0. invisible state, 1: invisible > visible state, 2: visisble state, 3: visible state > invisible 
	private float pressToEnterState;
	
	// Label style to be used for labels.
	private LabelStyle labelStyle;
	
	private Music music;
	
	public SplashScreen(Mmonline game) {
		super(game);
		// TODO Auto-generated constructor stub
		
		// Set up labels.
		setLabels();
		
		// Set up textures and images.
		setGraphics();
		
		music = Gdx.audio.newMusic(Gdx.files.internal("splash/music.wav"));
	}
	
	private void setLabels () {
		labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
		title = new Label("Mega-Man Online", labelStyle);
		author1 = new Label("Niko Lagman", labelStyle);
		author2 = new Label("Jimmy Chen", labelStyle);
		author3 = new Label("Gabriel Batista", labelStyle);
		pressToEnter = new Label("Press Enter", labelStyle); 
		pressToEnterState = 0;
	}
	
	private void setGraphics () {
		// Background.
		background = new Image(new Texture("splash/background.jpg"));
		background.setBounds(0, 0, game.getWidth(), game.getHeight());
		stage.addActor(background);
		
		// Set up title table.
		titleTable = new Table();
		// Set table to start at top of screen.
		titleTable.top();
		// Set table to fill parent dimensions.
		titleTable.setFillParent(true);
		// Add table to stage.
		stage.addActor(titleTable);
		
		// In the first row, add the splashScreen label.
		titleTable.padTop(10f);
		titleTable.add().expandX();
		titleTable.add(title).expandX();
		titleTable.add().expandX();
		
		// next row
		titleTable.row().padTop(80f);
		// Add authors to row.
		titleTable.add(author1).expandX();
		titleTable.add(author2).expandX();
		titleTable.add(author3).expandX();
		
		titleTable.row().padTop(80f);
		titleTable.add().expandX();
		titleTable.add(pressToEnter).expandX();
		titleTable.add().expandX();
	}
	
	private void processInput () {
		
		// If enter is pressed and we're in the 'normal' state.
		if (Gdx.input.isKeyJustPressed(Keys.ENTER) && getTransitionState() == 0) {
			screenTransition(game.getMenuScreen());
		}
	}
	
	private void processEnterLabel() {
		// If invisible.
		if (pressToEnterState == 0) {
			// Set to transition to visibie.
			pressToEnterState = 1;
			pressToEnter.addAction(Actions.fadeIn(1f));
			timer.scheduleTask(new Task() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					// State to visible state.
					pressToEnterState = 2;
				}
			}, 1f);
		}
		// If invisible.
		if (pressToEnterState == 2) {
			// Set to transition to visible.
			pressToEnterState = 3;
			pressToEnter.addAction(Actions.fadeOut(1f));
			timer.scheduleTask(new Task() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					// State to visible state.
					pressToEnterState = 0;
				}
			}, 1f);
		}
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		game.playScreenMusic(music);
	}
	
	@Override
	public void render(float delta) {
		processInput();
		processEnterLabel();
		// TODO Auto-generated method stub
		super.render(delta);
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		music.stop();
	}
}
