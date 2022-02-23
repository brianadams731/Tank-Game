package com.tank.game;

import com.tank.game.actors.entities.GameObject;

import java.util.ArrayList;

public interface BuildFromTileMap {
    void pushNewObjects(ArrayList<GameObject> tileList);
}
