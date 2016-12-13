package com.ludussquare.mmonline.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public abstract class DynamicEntity extends Entity {

	protected Body body;
	protected boolean facingLeft;
	
	protected boolean movingLeft, movingRight, jump, movementFinal;

	// The options to be set by a sub class.
	protected boolean alive;
	protected float width, height;
	protected int horizontalDirection, verticalDirection;
	protected float horizontalSpeed, verticalSpeed;
	protected FixtureDef fixtureDef;
	protected Fixture fixture;
	protected PolygonShape shape;
	
	public DynamicEntity(Vector2 position, World world) {
		super(position, world);
		
		// Set body definition to be dynamic.
		bodyDef.type = BodyType.DynamicBody;
		
		// Create body using body definition.
		body = world.createBody(bodyDef);
		
		// Set body position to starting position of body definition.
		body.getPosition().set(bodyDef.position);
		
		facingLeft = false;
		
		// By default, set movementFinal to false.
		movementFinal = false;
		
		// By default, set alive to true.
		alive = true;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		super.render(batch);
		
		processMovement();
	}
	
	private void processMovement () {
		
		if (!movementFinal && alive) {
			// 1. Reset horizontal direction.
			horizontalDirection = 0;
			
			// 2. Set horizontal direction according to existing directional states.
			if (movingLeft) horizontalDirection -= 1;
			if (movingRight) horizontalDirection += 1;
			
			// Get current velocity and apply jump velocity if necessary.
			float yVelocity = body.getLinearVelocity().y;
			
			// Set velocity as set by direction and speeds.
			body.setLinearVelocity(horizontalSpeed * horizontalDirection, yVelocity);
		}
	}
	
	public Vector2 getBodyPosition () {
		return body.getPosition();
	}
}
