package com.tank.game.gameWorld.foreground;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tank.game.Asset;
import com.tank.game.actors.entities.Entity;
import com.tank.game.actors.entities.GameObject;

public abstract class Foreground implements GameObject {
    Entity foreground;
    public Foreground(Asset asset, int offsetX, int offsetY, float x, float y){
        foreground = new Entity(asset, offsetX, offsetY, x, y);
    }
    @Override
    public void draw(SpriteBatch batch){
        this.foreground.draw(batch);
    }

    @Override
    public void update(){

    }

    @Override
    public void dispose(){

    }
}
