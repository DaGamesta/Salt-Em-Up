package com.mwojnar.Game;

import com.badlogic.gdx.Game;
import com.mwojnar.Assets.AssetLoader;
import com.mwojnar.Screens.GameScreen;

public class LudumDare32Game extends Game {

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}