package com.tank.game.actors.owners;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tank.game.InputController;
import com.tank.game.actors.entities.*;
import com.tank.game.actors.entities.tanks.Tank;


import java.util.ArrayList;

public abstract class Player extends Owner implements ColliderController, GivesPosition, GivesLifeCount, Removeable {
    protected final InputController input;
    public Player(GameObject gameObject, InputController input){
        super(
                gameObject
        );
        this.input = input;
    }

    @Override
    public void update(){

    }
    // -- GameObject -- //
    public void update(OrthographicCamera camera){
        if(((Removeable)this.getItem()).getShouldRemove()){
            ((Tank)this.getItem()).setOutOfBounds();
            return;
        }

        input.update(camera);

        this.moveItem();
        this.rotateTopToPoint();

        this.getItem().update();
        this.fireBullet();

        ((Stoppable)this.getItem()).resetStop();
    }
    @Override
    public void draw(SpriteBatch batch){
        if(((Removeable)this.getItem()).getShouldRemove()){
            return;
        }

        this.getItem().draw(batch);
    }
    @Override
    public void dispose(){
        this.getItem().dispose();
    }

    // -- ColliderController -- //
    @Override
    public ArrayList<Collides> getColliders() {
        ArrayList<Collides> colliders = new ArrayList<>();
        colliders.add((Collides) this.getItem());
        colliders.addAll(((ColliderController)this.getItem()).getColliders());
        return colliders;
    }

    // -- Input Logic -- //
    private void moveItem(){
        if(this.input.isForwardKeyDown()){
            ((Movable)this.getItem()).moveForward();
        }
        if(this.input.isRightKeyDown()){
            ((Movable)this.getItem()).turnClockwise();
        }
        if(this.input.isBackKeyDown()){
            ((Movable)this.getItem()).moveBackward();
        }
        if(this.input.isLeftKeyDown()){
            ((Movable)this.getItem()).turnCounterClockwise();
        }
    }

    private void fireBullet(){
        if(this.input.isFireKeyDown()){
            ((Shoots)this.getItem()).shoot();
        }
    }

    protected abstract void rotateTopToPoint();

    @Override
    public int[] getPosition() {
        return ((GivesPosition)this.getItem()).getPosition();
    }

    @Override
    public int getLifeCount() {
        return ((GivesLifeCount)this.getItem()).getLifeCount();
    }

    @Override
    public boolean getShouldRemove() {
        return ((Removeable)this.getItem()).getShouldRemove();
    }
}
