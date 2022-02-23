package com.tank.game.actors.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tank.game.actors.owners.Owner;

public interface GameObject {
    void update();
    void draw(SpriteBatch batch);
    void dispose();
}
