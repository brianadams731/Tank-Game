package com.tank.game.actors.entities.tanks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tank.game.AssetManager;

public class Sherman extends Tank{

    public Sherman(float x, float y, float initialAngle){
        super(
                AssetManager.getShermanTankBase(),
                AssetManager.getShermanTankTop(),
                5,
                4,
                initialAngle,
                x,
                y
        );
    }
}
