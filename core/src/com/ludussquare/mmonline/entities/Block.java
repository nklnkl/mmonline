package com.ludussquare.mmonline.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Block extends StaticEntity {
	
	PolygonShape boxShape;
	
	public Block (Vector2 position, World world ) {
		super(position, world);
		boxShape = new PolygonShape();
		boxShape.setAsBox(3000f, 10f);
		body.createFixture(boxShape, 0f);
	}

	public void dispose() {
		boxShape.dispose();
	}
	
} 