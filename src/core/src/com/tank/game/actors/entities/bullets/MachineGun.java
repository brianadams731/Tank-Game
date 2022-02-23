package com.tank.game.actors.entities.bullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tank.game.AssetManager;

public class MachineGun extends Bullet{

    public MachineGun(float x, float y, float angle){
        super(
                x,y,angle,
                10, 12,
                AssetManager.getBullets(),30,1
        );
    }
}
