package com.tank.game.gameWorld.background;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tank.game.Asset;
import com.tank.game.actors.entities.Entity;
import com.tank.game.actors.entities.GameObject;

public abstract class Background implements GameObject {
    Entity background;
    public Background(Asset asset, int offsetX, int offsetY, float x, float y){
        this.background = new Entity(asset, offsetX, offsetY, x, y);
    }

    @Override
    public void draw(SpriteBatch batch){
        this.background.draw(batch);
    }

    @Override
    public void update(){

    }

    @Override
    public void dispose(){

    }
}
