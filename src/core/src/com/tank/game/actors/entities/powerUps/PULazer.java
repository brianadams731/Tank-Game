package com.tank.game.actors.entities.powerUps;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tank.game.AssetManager;
import com.tank.game.actors.entities.GameObject;

public class PULazer extends PowerUp implements ChangeBulletType {
    String bulletType;
    public PULazer(int x, int y){
        super(
                AssetManager.getPowerUps(),2,18,x,y
        );
        this.bulletType = "lazer";
    }

    @Override
    public String getBulletType() {
        return this.bulletType;
    }
}
