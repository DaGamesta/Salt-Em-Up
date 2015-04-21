package com.mwojnar.GameObjects;

import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mwojnar.Assets.AssetLoader;
import com.mwojnar.GameWorld.LudumDare32World;
import com.playgon.GameEngine.Entity;
import com.playgon.GameEngine.Mask;
import com.playgon.GameEngine.TouchEvent;
import com.playgon.GameWorld.GameRenderer;
import com.playgon.GameWorld.GameWorld;

public class SaltShaker extends Entity {
	
	private TouchEvent touchDragging = null;
	private Vector2 startTouchPoint = new Vector2();
	private boolean readyForNextShake = true;
	private float accelerometerShakeThreshold = 7.0f;
	private int textShakeAmount = 5, animation = 0;
	
	public SaltShaker(GameWorld myWorld) {
		
		super(myWorld);
		setSprite(AssetLoader.saltShakerSprite);
		setPivot(new Vector2(AssetLoader.saltShakerSprite.getWidth() / 2.0f, AssetLoader.saltShakerSprite.getHeight() / 2.0f));
		setMask(new Mask(this, AssetLoader.saltShakerSprite.getWidth(), AssetLoader.saltShakerSprite.getHeight()));
		setRotation(270);
		setDepth(1001);
		textShakeAmount = 5;
		
	}
	
	@Override
	public void update(float delta, List<TouchEvent> touchEventList, List<Character> charactersTyped, List<Integer> keysFirstDown, List<Integer> keysFirstUp, List<Integer> keysDown) {
		
		super.update(delta, touchEventList, charactersTyped, keysFirstDown, keysFirstUp, keysDown);
		
		if (touchDragging == null) {
			
			for (TouchEvent touchEvent : touchEventList) {
				
				if (touchEvent.type == TouchEvent.Type.TOUCH_DOWN && collidingWithPoint(new Vector2(320.0f - touchEvent.point.x, 240.0f - touchEvent.point.y), false)) {
					
					startTouchPoint = new Vector2(320.0f - touchEvent.point.x, 240.0f - touchEvent.point.y).sub(getPos(false));
					touchDragging = touchEvent;
					break;
					
				}
				
			}
			
		} else {
			
			if (touchDragging.type != TouchEvent.Type.TOUCH_UP) {
				
				if (getPos(false) != new Vector2(getPos(false).x, touchDragging.point.y - startTouchPoint.y) && ((LudumDare32World)getWorld()).textMode == 1) {
					
					AssetLoader.confirmationSound.play();
					((LudumDare32World)getWorld()).textMode = 2;
					
				}
				setPos(new Vector2(getPos(false).x, 240.0f - touchDragging.point.y - startTouchPoint.y), false);
				
			} else {
				
				touchDragging = null;
				
			}
			
		}
		
		float accelY = Gdx.input.getAccelerometerY();
		if (accelY > 5) {
			
			if (accelY < accelerometerShakeThreshold - 2) {
				
				accelerometerShakeThreshold = accelY + 2;
				readyForNextShake = true;
				
			} else if (accelY > accelerometerShakeThreshold) {
				
				accelerometerShakeThreshold = accelY;
				if (readyForNextShake && !((LudumDare32World)getWorld()).gameOver) {
					
					shake();
					readyForNextShake = false;
					
				}
				
			}
			if (((LudumDare32World)getWorld()).textMode == 0 && accelY > 7) {

				AssetLoader.confirmationSound.play();
				((LudumDare32World)getWorld()).textMode = 1;
				
			}
			
		}
		if (animation > 0) {
			
			switch (animation) {
			
			case 7: setRotation(270.0f + 11.25f); break;
			case 6: setRotation(270.0f + 22.5f); break;
			case 5: setRotation(270.0f + 11.25f); break;
			case 4: setRotation(270.0f); break;
			case 3: setRotation(270.0f - 11.25f); break;
			case 2: setRotation(270.0f - 22.5f); break;
			case 1: setRotation(270.0f - 11.25f); break;
			
			}
			animation--;
			
		} else {
			
			setRotation(270.0f);
			
		}
		
	}
	
	@Override
	public void draw(GameRenderer renderer) {
		
		switch (((LudumDare32World)getWorld()).textMode) {
		
		case 0: AssetLoader.holdStraightSprite.draw(80, 0, 0, 1, 1, 0, 0, 0, renderer); break;
		case 1: AssetLoader.moveShakerSprite.draw(80, 0, 0, 1, 1, 0, 0, 0, renderer); break;
		case 2: AssetLoader.shakeSprite.draw(80, 0, 0, 1, 1, 0, 0, 0, renderer); break;
		
		}
		
		AssetLoader.debugFont.draw(renderer.getBatcher(), "Score: " + ((LudumDare32World)getWorld()).score, 0, 0);
		AssetLoader.debugFont.draw(renderer.getBatcher(), "Lives: " + ((LudumDare32World)getWorld()).lives, 100, 0);
		
		super.draw(renderer);
		
		if (((LudumDare32World)getWorld()).gameOver) {
			
			AssetLoader.gameOverSprite.draw(80, 0, 0, 1, 1, 0, 0, 0, renderer);
			
		}
		
	}
	
	public void shake() {
		
		for (int i = 0; i < 10; i++) {
			
			Salt tempSalt = new Salt(getWorld());
			Random random = new Random();
			tempSalt.setPos(new Vector2(getPos(true).x - getSprite().getHeight() / 2.0f - random.nextFloat() * 50, getPos(true).y - getSprite().getWidth() / 2.0f + random.nextFloat() * 50), true);
			getWorld().createEntity(tempSalt);
			
		}
		AssetLoader.shakeSound.play();
		Gdx.input.vibrate(50);
		if (((LudumDare32World)getWorld()).textMode == 2) {
			
			if (textShakeAmount > 0) {
				
				textShakeAmount--;
				
			} else {
				
				AssetLoader.confirmationSound.play();
				((LudumDare32World)getWorld()).textMode = 3;
				
			}
			
		}
		
		animation = 7;
		
	}
	
}