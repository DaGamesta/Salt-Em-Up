package com.mwojnar.GameObjects;

import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.mwojnar.Assets.AssetLoader;
import com.mwojnar.GameWorld.LudumDare32World;
import com.playgon.GameEngine.Entity;
import com.playgon.GameEngine.Mask;
import com.playgon.GameEngine.TouchEvent;
import com.playgon.GameWorld.GameWorld;

public class Restart extends Entity {
	
	public Restart(GameWorld myWorld) {
		
		super(myWorld);
		setSprite(AssetLoader.restartSprite);
		setPivot(60.0f, 120.0f);
		setMask(new Mask(this, 120.0f, 240.0f));
		setDepth(1001);
		
	}
	
	@Override
	public void update(float delta, List<TouchEvent> touchEventList, List<Character> charactersTyped, List<Integer> keysFirstDown, List<Integer> keysFirstUp, List<Integer> keysDown) {
		
		super.update(delta, touchEventList, charactersTyped, keysFirstDown, keysFirstUp, keysDown);
		
		for (TouchEvent touchEvent : touchEventList) {
			
			if (touchEvent.type == TouchEvent.Type.TOUCH_DOWN) {
				
				if (collidingWithPoint(new Vector2(320.0f - touchEvent.point.x, 240.0f - touchEvent.point.y), false)) {
					
					((LudumDare32World)getWorld()).restart();
					
				}
				
			}
			
		}
		
	}
	
}