package com.ludussquare.mmonline.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class StaticEntity extends Entity {
	
	protected Body body;
	
	public StaticEntity (Vector2 position, World world) {
		super(position, world);
		bodyDef.type = BodyType.StaticBody;
		body = world.createBody(bodyDef);
	}
}