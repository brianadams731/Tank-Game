package com.tank.game.actors.entities.powerUps;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tank.game.AssetManager;

public class PUMachineGun extends PowerUp implements ChangeBulletType  {
    String bulletType;
    public PUMachineGun(int x, int y) {
        super(
                AssetManager.getPowerUps(),0,18,
                x,
                y
        );
        this.bulletType = "mg";
    }
    @Override
    public String getBulletType() {
        return bulletType;
    }
}
