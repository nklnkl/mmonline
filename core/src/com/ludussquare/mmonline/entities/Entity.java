package com.ludussquare.mmonline.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Entity {
	
	// The physics world that this entity belongs to.
	protected World world;
	
	// The actor's physics body defintion.
	protected BodyDef bodyDef;
	
	// The entity's life time after creation.
	protected float stateTime;
	
	public Entity (Vector2 position, World world) {
		
		// Connect this entity to the "world".
		this.world = world;
		
		// Set new body definition.
		bodyDef = new BodyDef();
		
		// Set body definition to start at given position.
		bodyDef.position.set(position);
	}
	
	public void render (SpriteBatch batch) {
		// Increase state time since last frame.
		stateTime += Gdx.graphics.getDeltaTime();
	}
}
