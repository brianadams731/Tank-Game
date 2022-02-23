package com.tank.game.actors.owners;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tank.game.actors.entities.GameObject;

public abstract class Owner {
    private GameObject item;

    Owner(GameObject item){
        this.item = item;
    }

    GameObject getItem(){
        return this.item;
    }

    public void setItem(GameObject item){
        this.item = item;
    }
    public void dispose(){
        this.item.dispose();
    }
    public abstract void update();
    public abstract void draw(SpriteBatch batch);
}
