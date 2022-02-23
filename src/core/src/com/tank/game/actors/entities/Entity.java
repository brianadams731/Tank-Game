package com.tank.game.actors.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.tank.game.Asset;
import com.tank.game.GameConstants;

// Wrapper Class for Sprite, Contains render information about Object
public class Entity {
    private Sprite entity;
    public Entity(Asset asset, float x, float y){
        this.entity = new Sprite(asset.getTexture());
        this.entity.setX(x);
        this.entity.setY(y);
    }

    public Entity(Asset asset, int indexX, int indexY, float x, float y){
        this.entity = new Sprite(
                asset.getTexture(),
                asset.getOffsetValueX(indexX),
                asset.getOffsetValueY(indexY),
                asset.getWidth(), asset.getHeight()
        );
        this.entity.setX(x);
        this.entity.setY(y);
    }

    public static boolean detectCollision(Collides one, Collides two){
        return one.getEntity().overlaps(two.getEntity());
    }
    public void switchTextures(Asset asset){
        this.entity.setTexture(asset.getTexture());
    }
    public boolean overlaps(Entity otherSprite){
        // Must be rectangle!!!!!
        return this.entity.getBoundingRectangle().overlaps(otherSprite.entity.getBoundingRectangle());
    }
    public boolean inBounds(){
        return (
                this.entity.getX() <= GameConstants.WINDOW_WIDTH  && this.entity.getX()+this.entity.getWidth()  >= 0 &&
                this.entity.getY() <= GameConstants.WINDOW_HEIGHT && this.entity.getY()+this.entity.getHeight() >= 0
        );
    }

    public float getRotation(){
        return this.entity.getRotation();
    }
    public float getHeight(){
        return this.entity.getHeight();
    }
    public float getWidth(){
        return this.entity.getWidth();
    }
    public float getX(){
        return this.entity.getX();
    }
    public float getY(){
        return this.entity.getY();
    }
    public float getCenterX(){
        return this.entity.getWidth()/2;
    }
    public float getCenterY(){
        return this.entity.getHeight()/2;
    }
    public float[] getVertices(){
        return this.entity.getVertices();
    }
    private float getScaleY(){
        return this.entity.getScaleY();
    }
    private float getScaleX(){
        return this.entity.getScaleX();
    }

    public void setRotation(float angle){
        this.entity.setRotation(angle);
    }
    public void rotate(float angle){
        this.entity.rotate(angle);
    }
    public void setX(float x){
        this.entity.setX(x);
    }
    public void setY(float y){
        this.entity.setY(y);
    }
    public void setScale(float scale){
        this.entity.setScale(scale);
    }
    public void setWidth(int width){
        this.entity.setSize(width,this.entity.getHeight());
    }

    public Rectangle getRectangle(){
        return this.entity.getBoundingRectangle();
    }

    public void draw(SpriteBatch batch){
        batch.draw(
                this.entity,
                this.getX(),
                this.getY(),
                this.getCenterX(),
                this.getCenterY(),
                this.getWidth(),
                this.getHeight(),
                this.getScaleX(),
                this.getScaleY(),
                this.getRotation()
        );
    }
}
