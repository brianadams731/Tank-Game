package com.tank.game.actors.entities.powerUps;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tank.game.AssetManager;


public class PUHealth extends PowerUp implements HealthMod {
    private int healthMod;
    public PUHealth(float x, float y){
        super(
                AssetManager.getPowerUps(),3,18,
                x,
                y
        );
        this.healthMod = 30;
    }

    @Override
    public int getHealthMod(){
        return this.healthMod;
    }
}
