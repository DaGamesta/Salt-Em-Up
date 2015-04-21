package com.mwojnar.GameWorld;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//import javafx.util.Pair;

import com.badlogic.gdx.math.Vector2;
import com.mwojnar.Assets.AssetLoader;
import com.mwojnar.GameObjects.SaltShaker;
import com.mwojnar.GameObjects.Snail;
import com.playgon.GameEngine.Background;
import com.playgon.GameEngine.BackgroundTemplate;
import com.playgon.GameEngine.Entity;
import com.playgon.GameEngine.PlaygonObject;
import com.playgon.GameEngine.Sprite;
import com.playgon.GameWorld.GameWorld;

public class LudumDare32World extends GameWorld {
	
	private int difficultyInterval = 1800, difficulty = 1, maxDifficulty = 5, timeUntilNextDifficulty = difficultyInterval;
	public int textMode = 0, score = 0, lives = 5;
	public boolean gameOver = false;
	private boolean isPlaying = false, sizzling = false;
	
	public LudumDare32World() {
		
		super();
		
	}
	
	@Override
	public void initialize() {
		
		score = 0;
		lives = 5;
		gameOver = false;
		difficulty = 1;
		timeUntilNextDifficulty = difficultyInterval;
		
		Entity tempEntity = new SaltShaker(this);
		tempEntity.setPos(new Vector2(getGameDimensions().x, getGameDimensions().y / 2.0f), true);
		createEntity(tempEntity);
		
		Background background = new Background(AssetLoader.tableclothBackground);
		addBackground(background);
		
		AssetLoader.music.setLooping(true);
		AssetLoader.musicHandler.startMusic(AssetLoader.music);
		
		getRenderer().rotateCam(180.0f);
		
	}
	
	@Override
	public void update(float delta) {
		
		super.update(delta);
		
		Random random = new Random();
		sizzling = false;
		
		if (isPlaying) {
			
			if (random.nextFloat() * (360.0f - 60.0f * difficulty) <= 1.0f) {
				
				Snail snail = new Snail(this, difficulty);
				snail.setPos(new Vector2(-25.0f, random.nextFloat() * getGameDimensions().y), true);
				createEntity(snail);
				
			}
			
			if (difficulty < maxDifficulty) {
				
				if (timeUntilNextDifficulty <= 0) {
					
					timeUntilNextDifficulty = difficultyInterval;
					if (difficulty < maxDifficulty) {
						
						difficulty++;
						
					} else {
						
						timeUntilNextDifficulty = Integer.MAX_VALUE;
						
					}
					
				} else {
					
					timeUntilNextDifficulty--;
					
				}
				
			}
			
		} else {
			
			if (textMode >= 3) {
				
				isPlaying = true;
				
			}
			
		}
		
	}
	
	public void sizzle() {
		
		if (!sizzling) {
			
			AssetLoader.sizzleSound.play();
			sizzling = true;
			
		}
		
	}
	
}