package com.ludussquare.mmonline.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer.Task;
import com.ludussquare.mmonline.Mmonline;

public class ExitScreen extends GameScreen {
	
	private float exitDelay;
	private Image background;

	public ExitScreen(Mmonline game) {
		super(game);
		// TODO Auto-generated constructor stub
		setGraphics();
		exitDelay = 3f;
	}
	
	private void setGraphics() {
		background = new Image(new Texture("exit/background.jpg"));
		background.setBounds(0, 0, game.getWidth(), game.getHeight());
		stage.addActor(background);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		timer.scheduleTask(new Task() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				game.getMusic().stop();
				Gdx.app.exit();
			}
		}, exitDelay);
	}
}
