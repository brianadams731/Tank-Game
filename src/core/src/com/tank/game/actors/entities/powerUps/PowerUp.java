package com.tank.game.actors.entities.powerUps;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tank.game.Asset;
import com.tank.game.actors.entities.Collides;
import com.tank.game.actors.entities.Entity;
import com.tank.game.actors.entities.GameObject;
import com.tank.game.actors.entities.tanks.Tank;


abstract public class PowerUp implements GameObject, Collides {
    private final Entity powerUp;
    private boolean shouldRemove;
    public PowerUp(Asset asset,int srcXIndex, int srcYIndex, float x, float y){
        this.powerUp = new Entity(asset, srcXIndex, srcYIndex, x,y);
        this.powerUp.setScale(1.4f);
        this.shouldRemove = false;
    }

    @Override
    public Entity getEntity(){
        return this.powerUp;
    }

    @Override
    public void collided(Collides collides){
        if(collides instanceof Tank){
            this.shouldRemove = true;
        }
    }

    public boolean getShouldRemove(){
        return this.shouldRemove;
    }
    @Override
    public void draw(SpriteBatch batch){
        this.powerUp.draw(batch);
    }
    @Override
    public void dispose(){

    }
    @Override
    public void update(){

    }

}
