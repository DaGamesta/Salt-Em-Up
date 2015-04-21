package com.mwojnar.GameObjects;

import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.mwojnar.Assets.AssetLoader;
import com.playgon.GameEngine.Entity;
import com.playgon.GameEngine.TouchEvent;
import com.playgon.GameWorld.GameWorld;

public class Salt extends Entity {
	
	private float rotationSpeed = 0;
	
	public Salt(GameWorld myWorld) {
		
		super(myWorld);

		Random random = new Random();
		rotationSpeed = random.nextFloat() * 40.0f - 20.0f;
		
		setSprite(AssetLoader.saltSprite);
		setGridVelocity(-3.0f, 0.0f);
		setPivot(new Vector2(1.3f + random.nextFloat() * 0.4f, 1.3f + random.nextFloat() * 0.4f));
		setDepth(1);
		
		
	}
	
	public void update(float delta, List<TouchEvent> touchEventList, List<Character> charactersTyped, List<Integer> keysFirstDown, List<Integer> keysFirstUp, List<Integer> keysDown) {
		
		super.update(delta, touchEventList, charactersTyped, keysFirstDown, keysFirstUp, keysDown);
		
		if (getPos(true).x < 0) {
			
			getWorld().destroyEntity(this);
			
		}
		
		setRotation(getRotation() + rotationSpeed);
		movePos(getGridVelocity().x, getGridVelocity().y);
		
	}
	
}