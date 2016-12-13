package com.ludussquare.mmonline.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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

public class Hunter extends DynamicEntity {
	
	private Sprite sprite;
	
	private Vector2 jump;
	private Vector2 jumpOrigin;
	private boolean readyToFire;
	protected float fireDelay;
	private Timer timer;
	private boolean dying;
	
	public Vector2 realP;
	
	// Set up textures and animation.
	protected TextureAtlas idleAtlas;
	protected TextureAtlas runAtlas;
	protected TextureAtlas jumpAtlas;
	protected TextureAtlas fallAtlas;
	protected TextureAtlas hurtAtlas;
	protected TextureAtlas spawnAtlas;
	protected TextureAtlas fireAtlas;
	protected Animation idleAnimation;
	protected Animation runAnimation;
	protected Animation jumpAnimation;
	protected Animation fallAnimation;
	protected Animation hurtAnimation;
	protected Animation spawnAnimation;
	protected Animation fireAnimation;
	protected Animation animation;
	// Set up textures and animation.
	
	private List<Shot> shots;
	
	public Hunter (Vector2 position, World world) {
		super(position, world);
		
		dying = false;
		
		// 1. Set width/height
		width = 16f;
		height = 16f;
		
		// 2. Define shape using width/height.
		shape = new PolygonShape();
		shape.setAsBox(width/2, height/2);
		
		// 3. Define fixture.
		fixtureDef = new FixtureDef();
		
		// 4. Set shape of fixture definition to polygon shape.
		fixtureDef.shape = shape;
		
		// 5. Add fixture definition to body.
		fixture = body.createFixture(fixtureDef);
		body.setUserData(this);
		
		// 6. Define movement variables.
		horizontalSpeed = 100f;
		verticalSpeed = 10f;
		jump = new Vector2(0f, 100f);
		jumpOrigin = new Vector2(0f, 0f);
		
		// 7. Set up timer.
		timer = new Timer();
		
		// 8. Set up fire system.
		readyToFire = true;
		fireDelay = 1.25f;
		shots = new ArrayList<Shot>();
		
		// 9. Define animations.
		setAnimations();
		
		// ?. Set test sprite.
		sprite = new Sprite(new Texture("box.png"));
	}
	
	protected void jump () {
		body.applyLinearImpulse(jump, jumpOrigin, true);
	}
	
	protected void fire () {
		
		// If the hunter is not yet ready to fire, return early.
		if (!readyToFire) return;
		
		System.out.println(this.toString() + " fired.");
		
		// The direction the shot will be sent.
		int hDirection = 1;
		if (facingLeft)  hDirection = -1;
		
		// Create new shot and add to shots list.
		Shot shot = new Shot(getBodyPosition(), hDirection, body, world);
		shots.add(shot);
		world.setContactListener(shot);
		
		// Set ready status to false.
		readyToFire = false;
		
		// Schedule ready for fire.
		timer.scheduleTask(new Task() {
			@Override
			public void run() {
				System.out.println(toString() + " ready to fire.");
				// After delay, set ready to fire to true.
				readyToFire = true;
			}
		}, fireDelay);
	}

	@Override
	public void render(final SpriteBatch batch) {
		super.render(batch);
		
		shots.forEach(new Consumer<Shot>() {
			@Override
			public void accept(Shot e) {
				e.render(batch);
			}
		});
		
		if (alive) renderAnimation(batch);
	}
	
	public float getSpriteX () {
		return sprite.getX();
	}
	
	public float getSpriteY () {
		return sprite.getY();
	}
	
	private void setAnimations () {
		idleAtlas = new TextureAtlas("player/idle.txt");
		runAtlas = new TextureAtlas("player/run.txt");
		jumpAtlas = new TextureAtlas("player/jump.txt");
		fallAtlas = new TextureAtlas("player/fall.txt");
		hurtAtlas = new TextureAtlas("player/hurt.txt");
		spawnAtlas = new TextureAtlas("player/spawn.txt");
		fireAtlas = new TextureAtlas("player/fire.txt");
		
		idleAnimation = new Animation(0.5f, idleAtlas.getRegions());
		runAnimation = new Animation(0.1f, runAtlas.getRegions());
		jumpAnimation = new Animation(0.25f, jumpAtlas.getRegions());
		fallAnimation = new Animation(0.5f, fallAtlas.getRegions());
		hurtAnimation = new Animation(0.5f, hurtAtlas.getRegions());
		spawnAnimation = new Animation(1/5f, spawnAtlas.getRegions());
		fireAnimation = new Animation(1/4f, fireAtlas.getRegions());
		
		idleAnimation.setPlayMode(Animation.PlayMode.LOOP);
		runAnimation.setPlayMode(Animation.PlayMode.LOOP);
		jumpAnimation.setPlayMode(Animation.PlayMode.LOOP);
		fallAnimation.setPlayMode(Animation.PlayMode.LOOP);
		hurtAnimation.setPlayMode(Animation.PlayMode.LOOP);
		spawnAnimation.setPlayMode(Animation.PlayMode.LOOP);
		fireAnimation.setPlayMode(Animation.PlayMode.LOOP);
	}
	
	private void renderAnimation (SpriteBatch batch) {
		// If the player just spawned.
		if (stateTime < 1f) {
			animation = spawnAnimation;
		}
		
		// If the player already spawned.
		else {
			
			// If the player is not dying.
			if (!dying) {
		
				// If player y velocity is between 5 and -5.
				if (body.getLinearVelocity().y < 5 && body.getLinearVelocity().y > -5) {
					// If player x velocity is moving.
					if (body.getLinearVelocity().x != 0) {
						animation = runAnimation;
					}
					if (body.getLinearVelocity().x == 0) {
						animation = idleAnimation;
					}
				} 
				// Else if the player y velocity is NOT between 5 and -5.
				else {
					// if the player y velocity is -5 or less
					if (body.getLinearVelocity().y <= -5) {
						animation = fallAnimation;
					}
					// if the player y velocity is 5 or more.
					if (body.getLinearVelocity().y >= 5) {
						animation = jumpAnimation;
					}
				}
				
				// If the player is firing
				if (!readyToFire) {
					animation = fireAnimation;
				}
			} else {
				animation = hurtAnimation;
			}
		}
		
		// 1. Set position of sprite to physics body.
		Vector2 p = body.getWorldCenter().scl(2f).sub(width, height);
		realP = p;

		// Draw current animation.
		batch.draw(
			animation.getKeyFrame(stateTime),
			(facingLeft ? Gdx.graphics.getWidth() / 2 + width * 2: Gdx.graphics.getWidth() / 2 ),
			Gdx.graphics.getHeight() / 2,
			(facingLeft ? -width : width) * 2,
			height * 2
		);

	}
	
	public void die () {
		dying = true;
		timer.scheduleTask(new Task() {
			@Override
			public void run() {
				dispose();
			}
		}, 2f);
	}
	
	private void dispose () {
		alive = false;
		animation = null;
		shape.dispose();
		fixture = null;
	}
	
}
 	