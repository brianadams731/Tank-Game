package com.tank.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tank.game.actors.owners.*;
import com.tank.game.gameState.GameState;
import com.tank.game.gameState.Initial;
import com.tank.game.gameWorld.BackgroundController;
import com.tank.game.gameWorld.ForegroundController;
import com.tank.game.gameWorld.GameWorldDeserializer;
import com.tank.game.menu.MenuController;
import com.tank.game.ui.LifeCounter;
import com.tank.game.ui.Minimap;


public class TankGame extends ApplicationAdapter {
	SpriteBatch batch;
	OrthographicCamera camera;

	Player player;
	Player playerTwo;

	PowerUpController powerUps;
	AIController aiController;

	ForegroundController foregrounds;
	BackgroundController backgrounds;

	LifeCounter lifeCounter;
	LifeCounter lifeCounterPlayerTwo;
	Minimap minimap;

	GameWorldDeserializer gameWorldDeserializer;

	RoundController roundController;

	MenuController menuController;

	GameState gameState;

	@Override
	public void create () {
		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(false,GameConstants.WINDOW_WIDTH,GameConstants.WINDOW_HEIGHT);

		player = new PlayerOne();
		playerTwo = new PlayerTwo();

		aiController = new AIController();
		powerUps = new PowerUpController();


		foregrounds = new ForegroundController();
		backgrounds = new BackgroundController();


		lifeCounter = new LifeCounter(true);
		lifeCounterPlayerTwo = new LifeCounter(false);
		minimap = new Minimap();

		/* Game World Building*/
		gameWorldDeserializer = new GameWorldDeserializer();
		((BuildFromTileMap)foregrounds).pushNewObjects(gameWorldDeserializer.getForeground());
		((BuildFromTileMap)backgrounds).pushNewObjects(gameWorldDeserializer.getBackground());
		gameWorldDeserializer = null;	// Clean up gameWorldDeserializer

		roundController = new RoundController();
		menuController = new MenuController();

		gameState = new Initial();
	}

	@Override
	public void render () {
		ScreenUtils.clear(.31f, .31f, .31f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		gameState.render(this);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		player.dispose();

		aiController.dispose();

		foregrounds.dispose();
		backgrounds.dispose();

		AssetManager.dispose();
		AudioManager.dispose();
	}



	public void setState(GameState state){
		this.gameState = state;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public Player getPlayer() {
		return player;
	}

	public PowerUpController getPowerUps() {
		return powerUps;
	}

	public AIController getAiController() {
		return aiController;
	}

	public ForegroundController getForegrounds() {
		return foregrounds;
	}

	public BackgroundController getBackgrounds() {
		return backgrounds;
	}

	public LifeCounter getLifeCounter() {
		return lifeCounter;
	}

	public Minimap getMinimap() {
		return minimap;
	}

	public RoundController getRoundController() {
		return roundController;
	}

	public MenuController getMenuController() {
		return menuController;
	}

	public Player getPlayerTwo() {
		return playerTwo;
	}
	public LifeCounter getLifeCounterPlayerTwo(){
		return lifeCounterPlayerTwo;
	}
}
