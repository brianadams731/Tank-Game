package com.tank.game.gameWorld;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tank.game.BuildFromTileMap;
import com.tank.game.actors.entities.GameObject;
import com.tank.game.gameWorld.background.Background;

import java.util.ArrayList;

public class BackgroundController implements GameObject, BuildFromTileMap {
    ArrayList<Background> background;
    public BackgroundController(){
        this.background = new ArrayList<>();
    }


    @Override
    public void pushNewObjects(ArrayList<GameObject> tileList){
        for(GameObject tile:tileList){
            this.background.add((Background) tile);
        }
    }

    // -- GameObject -- //
    @Override
    public void update() {
        for(Background tile: background){
            tile.update();
        }
    }

    @Override
    public void dispose() {
        for(Background tile: background){
            tile.dispose();
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        for(Background tile: background){
            tile.draw(batch);
        }
    }

}