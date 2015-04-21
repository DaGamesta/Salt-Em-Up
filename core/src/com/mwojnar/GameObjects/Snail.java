package com.mwojnar.GameObjects;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.mwojnar.Assets.AssetLoader;
import com.mwojnar.GameWorld.LudumDare32World;
import com.playgon.GameEngine.Entity;
import com.playgon.GameEngine.Mask;
import com.playgon.GameEngine.TouchEvent;
import com.playgon.GameWorld.GameWorld;

public class Snail extends Entity {
	
	private int health = 40;
	
	public Snail(GameWorld myWorld) {
		
		super(myWorld);
		setSprite(AssetLoader.snailSprite);
		setPivot(new Vector2(AssetLoader.snailSprite.getWidth() / 2.0f, AssetLoader.snailSprite.getHeight() / 2.0f));
		setRotation(270);
		setAnimationSpeed(5);
		setGridVelocity(0.3f, 0.0f);
		setDepth(-1);
		List<Vector2> maskPolygonPoints = new ArrayList<Vector2>();
		maskPolygonPoints.add(new Vector2(23, 46));
		maskPolygonPoints.add(new Vector2(26, 46));
		maskPolygonPoints.add(new Vector2(33, 37));
		maskPolygonPoints.add(new Vector2(34, 30));
		maskPolygonPoints.add(new Vector2(37, 30));
		maskPolygonPoints.add(new Vector2(37, 20));
		maskPolygonPoints.add(new Vector2(34, 14));
		maskPolygonPoints.add(new Vector2(29, 9));
		maskPolygonPoints.add(new Vector2(12, 14));
		maskPolygonPoints.add(new Vector2(12, 30));
		maskPolygonPoints.add(new Vector2(15, 30));
		maskPolygonPoints.add(new Vector2(16, 37));
		maskPolygonPoints.add(new Vector2(19, 43));
		setMask(new Mask(this, maskPolygonPoints));
		
	}
	
	public Snail(GameWorld myWorld, int difficulty) {
		
		super(myWorld);
		setSprite(AssetLoader.snailSprite);
		setPivot(new Vector2(AssetLoader.snailSprite.getWidth() / 2.0f, AssetLoader.snailSprite.getHeight() / 2.0f));
		setRotation(270);
		setAnimationSpeed(5);
		setGridVelocity(0.3f + ((difficulty - 1) * 0.3f / 5.0f), 0.0f);
		setDepth(-1);
		List<Vector2> maskPolygonPoints = new ArrayList<Vector2>();
		maskPolygonPoints.add(new Vector2(23, 46));
		maskPolygonPoints.add(new Vector2(26, 46));
		maskPolygonPoints.add(new Vector2(33, 37));
		maskPolygonPoints.add(new Vector2(34, 30));
		maskPolygonPoints.add(new Vector2(37, 30));
		maskPolygonPoints.add(new Vector2(37, 20));
		maskPolygonPoints.add(new Vector2(34, 14));
		maskPolygonPoints.add(new Vector2(29, 9));
		maskPolygonPoints.add(new Vector2(12, 14));
		maskPolygonPoints.add(new Vector2(12, 30));
		maskPolygonPoints.add(new Vector2(15, 30));
		maskPolygonPoints.add(new Vector2(16, 37));
		maskPolygonPoints.add(new Vector2(19, 43));
		setMask(new Mask(this, maskPolygonPoints));
		
	}
	
	public void update(float delta, List<TouchEvent> touchEventList, List<Character> charactersTyped, List<Integer> keysFirstDown, List<Integer> keysFirstUp, List<Integer> keysDown) {
		
		super.update(delta, touchEventList, charactersTyped, keysFirstDown, keysFirstUp, keysDown);
		
		if (getPos(true).x - 25.0 > getWorld().getGameDimensions().x) {
			
			getWorld().destroyEntity(this);
			AssetLoader.loseLifeSound.play();
			((LudumDare32World)getWorld()).lives--;
			if (((LudumDare32World)getWorld()).lives <= 0) {

				((LudumDare32World)getWorld()).lives = 0;
				if (!((LudumDare32World)getWorld()).gameOver) {
					
					AssetLoader.gameOverSound.play();
					getWorld().createEntity(new Restart(getWorld()));
					((LudumDare32World)getWorld()).gameOver = true;
					
				}
				
			}
			
		}
		
		List<Entity> entityList = getWorld().getEntityList();
		
		for (Entity entity : entityList) {
			
			if (entity instanceof Salt) {
				
				if (collidingWithPoint(entity.getPos(true), true)) {
					
					health--;
					getWorld().destroyEntity(entity);
					((LudumDare32World)getWorld()).sizzle();
					
				}
				
			}
			
		}
		
		if (health <= 0) {
			
			getWorld().destroyEntity(this);
			AssetLoader.sizzleDeathSound.play(0.5f);
			AssetLoader.pointSound.play();
			((LudumDare32World)getWorld()).score++;
			
		}
		
		movePos(getGridVelocity().x, getGridVelocity().y);
		
	}
	
}
