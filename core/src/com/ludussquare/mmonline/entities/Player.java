package com.ludussquare.mmonline.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends Hunter implements InputProcessor {

	public Player(Vector2 position, World world) {
		super(position, world);
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.D) {
			movingRight = true;
			facingLeft = false;
		}
		if (keycode == Keys.A) {
			movingLeft = true;
			facingLeft = true;
		}
		if (keycode == Keys.W && alive) jump();
		if (keycode == Keys.SPACE && alive) fire();
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.D) movingRight = false;
		if (keycode == Keys.A) movingLeft = false;
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
