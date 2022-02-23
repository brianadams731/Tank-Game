package com.tank.game.gameWorld;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tank.game.BuildFromTileMap;
import com.tank.game.actors.entities.Collides;
import com.tank.game.actors.entities.GameObject;
import com.tank.game.actors.owners.ColliderController;
import com.tank.game.gameWorld.foreground.Foreground;

import java.util.ArrayList;

public class ForegroundController implements ColliderController, GameObject, BuildFromTileMap {
    ArrayList<Foreground> foregrounds;
    public ForegroundController(){
        this.foregrounds = new ArrayList<>();
    }


    @Override
    public void pushNewObjects(ArrayList<GameObject> tileList){
        for(GameObject tile:tileList){
            this.foregrounds.add((Foreground) tile);
        }
    }

    // -- GameObject -- //
    @Override
    public void update() {
        for(Foreground foreground:foregrounds){
            foreground.update();
        }
    }

    @Override
    public void dispose() {
        for(Foreground foreground:foregrounds){
            foreground.dispose();
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        for(Foreground foreground:foregrounds){
            foreground.draw(batch);
        }
    }

    // --- ColliderController --- //
    @Override
    public ArrayList<Collides> getColliders() {
        ArrayList<Collides> colliders = new ArrayList<>();
        for(Foreground foreground:foregrounds){
            if(foreground instanceof Collides){
                colliders.add((Collides) foreground);
            }
        }
        return colliders;
    }
}
