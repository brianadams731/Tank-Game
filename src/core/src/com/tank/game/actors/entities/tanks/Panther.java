package com.tank.game.actors.entities.tanks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tank.game.AssetManager;

public class Panther extends  Tank{

    public Panther(float x, float y, float initialAngle){
        super(
                AssetManager.getPantherTankBase(),
                AssetManager.getPantherTankTop(),
                5,
                4,
                initialAngle,
                x,
                y
        );
    }
}
