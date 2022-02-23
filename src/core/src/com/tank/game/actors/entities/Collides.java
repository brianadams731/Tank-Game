package com.tank.game.actors.entities;

public interface Collides {
    Entity getEntity();
    void collided(Collides collider);   // Returns the collider that collided and determines behavior on collision
}
