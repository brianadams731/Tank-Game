package com.tank.game.gameWorld.background;

import com.tank.game.AssetManager;


public class FloorUncracked extends Background {

    public FloorUncracked(float x, float y){
        super(
                AssetManager.getDungeonTiles(),9,11,
                x,y
        );
    }
}