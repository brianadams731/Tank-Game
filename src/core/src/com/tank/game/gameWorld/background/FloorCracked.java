package com.tank.game.gameWorld.background;

import com.tank.game.AssetManager;

public class FloorCracked extends Background{
    public FloorCracked(float x, float y){
        super(
                AssetManager.getDungeonTiles(),2,10,
                x,y
        );
    }
}
