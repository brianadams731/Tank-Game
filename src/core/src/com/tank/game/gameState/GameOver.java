package com.tank.game.gameState;

import com.tank.game.GameConstants;
import com.tank.game.TankGame;

public class GameOver implements  GameState{
    @Override
    public void render(TankGame tankGame) {
        tankGame.getCamera().position.x = GameConstants.WINDOW_WIDTH/2;
        tankGame.getCamera().position.y = GameConstants.WINDOW_HEIGHT/2;
        tankGame.getMenuController().update(tankGame.getCamera());
        tankGame.getMenuController().drawGameOverScreen(tankGame.getBatch());
    }
}
