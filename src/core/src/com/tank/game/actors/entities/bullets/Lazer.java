package com.tank.game.actors.entities.bullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tank.game.AssetManager;

public class Lazer extends Bullet{
    public Lazer(float x, float y, float angle){
        super(
                x,y,angle,
                4, 30,
                AssetManager.getBullets(),6,9
        );

        this.setWidth(35);
        this.resetX(x);
        this.resetY(y);
    }
}
