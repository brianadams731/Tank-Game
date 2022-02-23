package com.tank.game.actors.entities.bullets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tank.game.Asset;
import com.tank.game.actors.entities.*;

public abstract class Bullet implements GameObject, Collides, Damages {
    private final Entity base;
    private boolean shouldRemove;
    private final float velocity;
    private final int damage;


    public Bullet(float x, float y, float angle, int damage, float velocity, Asset asset, int srxIndexX, int srcIndexY){
        base = new Entity(asset, srxIndexX, srcIndexY, x, y);
        this.base.setRotation(angle);
        this.base.setX(x - this.base.getCenterX());
        this.base.setY(y - this.base.getCenterY());
        this.damage = damage;
        this.velocity = velocity;
        this.shouldRemove = false;

    }
    private float getVelocity(){
        return this.velocity;
    }

    public boolean inBounds(){
        return this.base.inBounds();
    }
    public boolean getShouldRemove(){
        return this.shouldRemove;
    }
    public void setShouldRemove(boolean shouldRemove){
        this.shouldRemove = shouldRemove;
    }
    // --- GameObject ---//
    @Override
    public void update(){
        float velocityX = (float) (this.getVelocity() * Math.cos(Math.toRadians(this.base.getRotation())));
        float velocityY = (float) (this.getVelocity() * Math.sin(Math.toRadians(this.base.getRotation())));

        this.base.setX(this.base.getX() + velocityX);
        this.base.setY(this.base.getY() + velocityY);
    }

    // --- Collides --- //
    @Override
    public Entity getEntity(){
        return this.base;
    }
    @Override
    public void collided(Collides collider){
        if(collider instanceof Solid){
            this.shouldRemove = true;
        }

        if(collider instanceof Breakable){
            this.shouldRemove = !((Breakable) collider).isBroken();
        }
    }

    public void setWidth(int width){
        this.base.setWidth(width);
    }
    public void resetX(float x){
        this.base.setX(x - this.base.getCenterX());
    }
    public void resetY(float y){
        this.base.setY(y-this.base.getCenterY());
    }

    @Override
    public void draw(SpriteBatch batch) {
        this.base.draw(batch);
    }

    @Override
    public void dispose(){

    }

    // -- Damages --//
    @Override
    public int getDamage(){
        return this.damage;
    }

}