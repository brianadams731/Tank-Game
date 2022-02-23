package com.tank.game.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tank.game.AssetManager;
import com.tank.game.GameConstants;
import com.tank.game.actors.entities.Entity;


public class LifeCounter {
    private final boolean playerOne;
    private final int windowOffsetX;
    private final int windowOffsetY;
    private final int lifeGap;

    Entity life;

    public LifeCounter(boolean isPlayerOne){
        this.playerOne = isPlayerOne;
        this.windowOffsetX = 30;
        this.windowOffsetY = 30;
        this.lifeGap = 25;

        this.life = isPlayerOne?
                new Entity(AssetManager.getPowerUps(),13,18,windowOffsetX,windowOffsetY):
                new Entity(AssetManager.getPowerUps(),14,18,windowOffsetX,windowOffsetY);

        this.life.setScale(1.4f);
    }

    public void draw(SpriteBatch batch, int lifeCount){
        for(int i = 0;i<lifeCount;i++){
            int xLoc = playerOne ?
                    this.windowOffsetX + (i * this.lifeGap) : (GameConstants.WINDOW_WIDTH - this.windowOffsetX - (int)this.life.getWidth()) - (i * this.lifeGap);
            this.life.setX(xLoc);
            this.life.draw(batch);
        }
    }

}
