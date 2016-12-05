package com.ludussquare.mmonline.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.ludussquare.mmonline.Mmonline;

public class SplashScreen extends GameScreen {
	
	// Background
	private Image background;
	
	// Formating of screen ui.
	private Table table;
	
	// Splash screen label elements.
	private Label title, author1, author2, author3;
	
	// Label style to be used for labels.
	private LabelStyle labelStyle;
	
	public SplashScreen(Mmonline game) {
		super(game);
		// TODO Auto-generated constructor stub
		
		// Set up labels.
		setLabels();
		
		// Set up textures and images.
		setGraphics();
	}
	
	private void setLabels () {
		labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
		title = new Label("Mega-Man Online", labelStyle);
		author1 = new Label("Niko Lagman", labelStyle);
		author2 = new Label("Jimmy Chen", labelStyle);
		author3 = new Label("Gabriel Batista", labelStyle);
	}
	
	private void setGraphics () {
		// Background.
		background = new Image(new Texture("splashBackground.jpg"));
		background.setBounds(0, 0, game.getWidth(), game.getHeight());
		stage.addActor(background);
		
		// Table UI.
		table = new Table();
		// Set table to start at top of screen.
		table.top();
		// Set table to fill parent dimensions.
		table.setFillParent(true);
		// Add table to stage.
		stage.addActor(table);
		
		// In the first row, add the splashScreen label.
		table.add().expandX();
		table.add(title).expandX();
		table.add().expandX();
		
		// next row
		table.row();
		// Add authors to row.
		table.add(author1).expandX();
		table.add(author2).expandX();
		table.add(author3).expandX();
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
	
	
}
