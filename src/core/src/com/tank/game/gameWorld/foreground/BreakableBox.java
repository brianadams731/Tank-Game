package com.tank.game.gameWorld.foreground;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tank.game.AssetManager;
import com.tank.game.actors.entities.*;

public class BreakableBox extends Foreground implements Breakable, Collides {
    boolean isBroken;
    int hp;

    public BreakableBox(float x, float y){
        super(
                AssetManager.getDungeonTiles(),17,0,
                x,y
        );
        this.hp = 50;
        this.isBroken = false;
    }

    @Override
    public boolean isBroken() {
        return this.isBroken;
    }

    @Override
    public Entity getEntity() {
        return this.foreground;
    }

    @Override
    public void collided(Collides collider) {
        if(collider instanceof Damages){
            if(this.hp > 0){
                this.hp = (this.hp - ((Damages)collider).getDamage());
            }
        }
    }

    @Override
    public void update() {
        if(this.hp <= 0){
            this.isBroken = true;
            this.foreground = new Entity(
                    AssetManager.getDungeonTiles(),7,9,
                    this.getEntity().getX(),
                    this.getEntity().getY()
            );
        }
    }
}
