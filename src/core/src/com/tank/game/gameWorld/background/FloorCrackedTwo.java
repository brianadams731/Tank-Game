package com.tank.game.gameWorld.background;

import com.tank.game.AssetManager;

public class FloorCrackedTwo extends Background{
    public FloorCrackedTwo(float x, float y){
        super(
                AssetManager.getDungeonTiles(),2,12,
                x,y
        );
    }
}
