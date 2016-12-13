package com.ludussquare.mmonline.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.ludussquare.mmonline.Mmonline;
import com.ludussquare.mmonline.entities.Block;
import com.ludussquare.mmonline.entities.Hunter;
import com.ludussquare.mmonline.entities.Player;

public class PlayScreen extends GameScreen {
	
	private Mmonline game;
	private World world;
	private Vector2 gravity;
	
	// 1. PlayScreen has its own separate sprite batch for physics entities.
	private SpriteBatch batch;
	
	// 2. PlayScreen also has a box2d renderer that can be visible for dev purposes.
	private Box2DDebugRenderer dRenderer;
	
	private Player player;
	private Label playerName;
	private Table table;
	
	private Block worldFloor;
	
	
	
	// Tiled Map
	private TiledMap tmx;
	private TiledMapRenderer mapRenderer;	
	private MapLayer platformLayer, blockLayer;
	private MapObjects platformObjects, blockObjects;
	private List<Rectangle> platformShapes;

	public PlayScreen(Mmonline game) {
		super(game);
		
		this.game = game;
		batch = new SpriteBatch();
		dRenderer = new Box2DDebugRenderer();
		platformShapes = new ArrayList<Rectangle>();
		
		setPhysics();
		player = new Player(new Vector2(100f, 100f), world);
		
		buildMap();
		
		worldFloor = new Block(new Vector2(0f, 52f), world);
	}
	
	
	
	private void buildMap () {
		tmx = new TmxMapLoader().load("maps/map3/playMap3.tmx");
		
		mapRenderer = new OrthogonalTiledMapRenderer(tmx);
		
		/*
		platformLayer = tmx.getLayers().get(3);
		blockLayer = tmx.getLayers().get(3);
		platformObjects = platformLayer.getObjects();
		blockObjects = blockLayer.getObjects();
		
		// Loop through platform objects and place down.
		for (int i = 0; i++ < platformObjects.getCount(); i++) {
			RectangleMapObject polyObject = (RectangleMapObject) blockObjects.get(0);
			Rectangle rectangle = polyObject.getRectangle();
			platformShapes.add(rectangle);
		}
		*/
	}
	
	private void setPhysics () {
		gravity = new Vector2(0f, -100f);
		world = new World(gravity, false);
	}

	@Override
	public void show() {
		super.show();
		Gdx.input.setInputProcessor(player);

		
		String username;
		if (game.getUsername() == null) username = "Unregistered";
		else username = game.getUsername();
		playerName = new Label(username, defaultSkin);
		
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		mapRenderer.setView( (OrthographicCamera) game.getView().getCamera());
		mapRenderer.render();
		
		// 1. Begin batch draw.
		batch.begin();
		player.render(batch);

		playerName.setPosition(10f, 10f);
		playerName.draw(batch, 1f);
		batch.end();
		
		// 2. Set camera to follow player.
		game.getView().getCamera().position.set(player.realP.x / 2, (player.realP.y / 2), 1f);
		game.getView().getCamera().update();
		
		// 3. Render box2d
		dRenderer.render(world, game.getView().getCamera().combined);
		
		// Draw stage.
		
		// ?.  	Advance physics for next frame.
		world.step(1/60f, 6, 2);
	}
	
	
}
