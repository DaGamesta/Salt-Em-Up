package com.mwojnar.Assets;

//import javafx.util.Pair;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.playgon.GameEngine.BackgroundTemplate;
import com.playgon.GameEngine.Sprite;
import com.playgon.Helpers.MusicHandler;

public class AssetLoader {
	
	public static Texture snailTexture, saltShakerTexture, saltTexture, tableclothTexture,
						holdStraightTexture, moveShakerTexture, shakeTexture, gameOverTexture, restartTexture;
	public static Sprite snailSprite, saltShakerSprite, saltSprite,
						holdStraightSprite, moveShakerSprite, shakeSprite, gameOverSprite, restartSprite;
	public static BitmapFont debugFont, font, whiteFont, shadowFont;
	public static BackgroundTemplate tableclothBackground;
	public static Sound shakeSound, confirmationSound, gameOverSound, pointSound, loseLifeSound, sizzleDeathSound, sizzleSound;
	public static Music music;
	public static MusicHandler musicHandler;
	
	public static void load() {
		
		musicHandler = new MusicHandler();
		
		debugFont = new BitmapFont(true);
		debugFont.setColor(Color.BLACK);
		debugFont.setUseIntegerPositions(false);
		
		/*font = new BitmapFont(Gdx.files.internal("data/Fonts/text.fnt"), true);
		font.setScale(.25f, -.25f);
		font.setUseIntegerPositions(false);
		
		whiteFont = new BitmapFont(Gdx.files.internal("data/Fonts/whitetext.fnt"), true);
		whiteFont.setScale(.1f, -.1f);
		whiteFont.setUseIntegerPositions(false);
		
		shadowFont = new BitmapFont(Gdx.files.internal("data/Fonts/shadow.fnt"), true);
		shadowFont.setScale(.25f, -.25f);
		shadowFont.setUseIntegerPositions(false);*/
		
		shakeSound = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/LDSaltShake.wav"));
		confirmationSound = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/LDConfirmation.mp3"));
		gameOverSound = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/LDGameOver.mp3"));
		pointSound = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/LDGetPoint.mp3"));
		loseLifeSound = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/LDLoseLife.wav"));
		sizzleDeathSound = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/LDSizzleDeath.wav"));
		sizzleSound = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/LDBasicSizzle.wav"));
		
		music = Gdx.audio.newMusic(Gdx.files.internal("data/Music/LDMusic.mp3"));
		musicHandler.addMusic(music);
		
		loadTextures();
		loadSprites();
		
		//example of how to load a sound
		//dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
		
	}
	
	public static void dispose() {
		// We must dispose of the texture when we are finished.
		//testTexture.dispose();
		
	}
	
	private static void loadTextures() {
		
		snailTexture = new Texture(Gdx.files.internal("data/Images/LDsnail.png"));
		snailTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		saltShakerTexture = new Texture(Gdx.files.internal("data/Images/LDSaltShaker.png"));
		saltShakerTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		saltTexture = new Texture(Gdx.files.internal("data/Images/LDSalt.png"));
		saltTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		tableclothTexture = new Texture(Gdx.files.internal("data/Images/LDTableclothBG.png"));
		tableclothTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		holdStraightTexture = new Texture(Gdx.files.internal("data/Images/LDHoldStraight.png"));
		holdStraightTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		moveShakerTexture = new Texture(Gdx.files.internal("data/Images/LDMoveShaker.png"));
		moveShakerTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		shakeTexture = new Texture(Gdx.files.internal("data/Images/LDShake.png"));
		shakeTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		gameOverTexture = new Texture(Gdx.files.internal("data/Images/LDGameOver.png"));
		gameOverTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		restartTexture = new Texture(Gdx.files.internal("data/Images/LDRestart.png"));
		restartTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
	}
	
	private static void loadSprites() {
		
		snailSprite = new Sprite(snailTexture, 5);
		saltShakerSprite = new Sprite(saltShakerTexture, 1);
		saltSprite = new Sprite(saltTexture, 1);
		holdStraightSprite = new Sprite(holdStraightTexture, 1);
		moveShakerSprite = new Sprite(moveShakerTexture, 1);
		shakeSprite = new Sprite(shakeTexture, 1);
		gameOverSprite = new Sprite(gameOverTexture, 1);
		restartSprite = new Sprite(restartTexture, 1);
		
		tableclothBackground = new BackgroundTemplate(tableclothTexture, 1);
		
	}
	
}