package com.tank.game.actors.owners;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tank.game.actors.entities.*;
import com.tank.game.actors.entities.zombies.MeleeZombie;

import java.util.ArrayList;

public class AI extends Owner implements ColliderController, Removeable, GivesPosition{
    public AI(float x, float y){
        super(new MeleeZombie(x,y));
    }

    @Override
    public void update(){
        // DO NOT USE THIS UPDATE, REFACTOR THIS OUT
    }

    public void update(int[] playerPosition) {
        this.getItem().update();

        moveItem(playerPosition);

        ((Stoppable)this.getItem()).resetStop();
    }

    @Override
    public void draw(SpriteBatch batch) {
        this.getItem().draw(batch);
    }

    @Override
    public ArrayList<Collides> getColliders() {
        ArrayList<Collides> colliders = new ArrayList<>();
        colliders.add((Collides)this.getItem());
        return colliders;
    }

    private void moveItem(int[] playerPosition){
        ((Movable)this.getItem()).moveForward();
        ((RotatesToPoint)this.getItem()).rotateToPoint(playerPosition[0],playerPosition[1]);
    }

    @Override
    public boolean getShouldRemove() {
        return ((Removeable)this.getItem()).getShouldRemove();
    }


    @Override
    public int[] getPosition() {
        return ((GivesPosition)this.getItem()).getPosition();
    }
}

