package com.tank.game.gameWorld.foreground;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tank.game.AssetManager;
import com.tank.game.actors.entities.Collides;
import com.tank.game.actors.entities.Entity;
import com.tank.game.actors.entities.Solid;

public class SolidWall extends Foreground implements Solid, Collides {

    public SolidWall(float x, float y){
        super(
                AssetManager.getDungeonTiles(),3,1,
                x,y
        );
    }

    @Override
    public Entity getEntity() {
        return this.foreground;
    }

    @Override
    public void collided(Collides collider) {
        // Can add animation logic upon collision here
    }
}
