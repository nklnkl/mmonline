package com.ludussquare.mmonline.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.ludussquare.mmonline.services.User;

public class Shot extends DynamicEntity implements ContactListener {
	
	private Sprite sprite;
	
	private Body parentBody;
	
	// The timer for this shot's expiration.
	private Timer timer;
	private float expiration;

	public Shot(Vector2 position, int horizontalDirection, Body parentBody, World world) {
		super(position, world);
		
		System.out.println(toString() + " created");
		
		// 1. Set width/height
		width = 8f;
		height = 8f;
		
		// 2. Define shape using width/height.
		shape = new PolygonShape();
		shape.setAsBox(width/4, height/4);
		
		// 3. Define fixture.
		fixtureDef = new FixtureDef();
		
		// 4. Set shape of fixture definition to polygon shape.
		fixtureDef.shape = shape;
		
		// 5. Add fixture definition to body.
		fixture = body.createFixture(fixtureDef);
		
		// 6. Define movement
		body.setGravityScale(0f);
		verticalSpeed = 0f;
		horizontalSpeed = 150f;
		this.horizontalDirection = horizontalDirection;
		body.setLinearVelocity(horizontalSpeed * horizontalDirection, 0f);
		movementFinal = true;
		
		// 7. Define timer and set expiration for shot.
		expiration = 5f;
		timer = new Timer();
		timer.scheduleTask(new Task() {
			
			@Override
			public void run() {
				dispose();
			}
		}, expiration);
		
		// 8. Define parent body.
		this.parentBody = parentBody;
		
		// ?. Define sprite.
		sprite = new Sprite(new Texture("box.png"));
	}

	@Override
	public void render(SpriteBatch batch) {
		if (alive) {
			super.render(batch);
			
			// 1. Get world coord of body.
			Vector2 p = body.getPosition().scl(2f).sub(width, height);
			// 2. Set position of sprite to physics body.
			sprite.setBounds(p.x + height/2, p.y + width/2, width, height);
			sprite.draw(batch);
		}
	}

	@Override
	public void beginContact(Contact contact) {
		// Get other colliding body.
		Body other = contact.getFixtureA().getBody();
		
		// Check to see if the user data referenced in the body, is of any collision types shot should check for.
		Object otherObject = other.getUserData();
		
		if (otherObject == null) return;
		
		// If the other body is the body that spawned it, return early.
		if (other == parentBody) return;
		
		if (otherObject.getClass() == Hunter.class) {
			Hunter hunter = (Hunter) otherObject;
			hunter.die();
			return;
		}
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}
	
	private void dispose () {
		alive = false;
		body.destroyFixture(fixture);
		body = null;
		sprite = null;
	}
}
