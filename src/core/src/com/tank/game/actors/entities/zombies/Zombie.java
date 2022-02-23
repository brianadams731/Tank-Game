package com.tank.game.actors.entities.zombies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tank.game.Asset;
import com.tank.game.actors.entities.*;


public abstract class Zombie implements GameObject, Movable, Collides, RotatesToPoint, Removeable, Stoppable, Solid, GivesPosition {
    Entity zombie;
    MoveBoundary moveBoundary;

    private final float rotationSpeed;
    private final Health hp;
    private float speed;
    private boolean shouldRemove;
    private boolean shouldSwitchToNormalTexture;

    public Zombie(Asset asset, int indexX, int indexY,float x, float y, int speed, float rotationSpeed, int hp){
        zombie = new Entity(asset, indexX, indexY, x, y);
        moveBoundary = new MoveBoundary(this.zombie);

        this.zombie.setScale(1.5f);
        this.rotationSpeed = rotationSpeed;
        this.speed = speed;
        this.hp = new Health(hp,1);  // CHANGE
        this.shouldRemove = false;
        this.shouldSwitchToNormalTexture = false;
    }

    @Override
    public void update() {
        if(this.hp.shouldRemove()){
            this.shouldRemove = true;
        }
        if(shouldSwitchToNormalTexture){
            this.setNormalTexture();
            this.shouldSwitchToNormalTexture = false;
        }
    }

    protected void switchZombieTexture(Asset asset){
        this.zombie.switchTextures(asset);
    }
    protected void setShouldSwitchToNormalTexture(){
        this.shouldSwitchToNormalTexture = true;
    }

    public void setSpeed(float speed){
        this.speed = speed;
    }
    @Override
    public void draw(SpriteBatch batch) {
        this.zombie.draw(batch);
    }

    @Override
    public void dispose() {

    }

    private int getVelocityX(boolean moveForward){
        int velocityX = (int) Math.round(this.speed * Math.cos(Math.toRadians(this.zombie.getRotation())));
        return moveBoundary.normalizeX(velocityX, moveForward);
    }
    private int getVelocityY(boolean moveForward){
        int velocityY = (int) Math.round(this.speed * Math.sin(Math.toRadians(this.zombie.getRotation())));
        return moveBoundary.normalizeY(velocityY , moveForward);
    }

    @Override
    public void moveForward() {
        this.zombie.setX(this.zombie.getX() + this.getVelocityX(true));
        this.zombie.setY(this.zombie.getY() + this.getVelocityY(true));
    }

    @Override
    public void moveBackward() {
        this.zombie.setX(this.zombie.getX() - this.getVelocityX(false));
        this.zombie.setY(this.zombie.getY() - this.getVelocityY(false));
    }

    @Override
    public void turnCounterClockwise() {
        this.zombie.rotate(this.rotationSpeed);
    }

    @Override
    public void turnClockwise() {
        this.zombie.rotate(-this.rotationSpeed);
    }

    // --- Collides ---//
    @Override
    public Entity getEntity() {
        return this.zombie;
    }

    @Override
    public void collided(Collides collider) {
        if(collider instanceof Solid){
            this.moveBoundary.determineIfStopped(collider);
        }

        if(collider instanceof Breakable){
            if(!((Breakable)collider).isBroken()){
                this.moveBoundary.determineIfStopped(collider);
            }
        }

        if(collider instanceof Damages){
            this.hp.removeHp(((Damages)collider).getDamage());
            this.setDamageTexture();
        }
    }

    @Override
    public void rotateToPoint(int playerX, int playerY){
        Vector2 zombiePos = new Vector2((this.zombie.getX() + this.zombie.getCenterX()),(this.zombie.getY() + this.zombie.getCenterY()));
        Vector2 playerPos = new Vector2(playerX, playerY);
        float angle = new Vector2(playerPos).sub(zombiePos).angleDeg();
        this.zombie.setRotation(angle);
    }

    @Override
    public void resetStop(){
        this.moveBoundary.resetStop();
    }

    @Override
    public boolean getShouldRemove(){
        return this.shouldRemove;
    }

    @Override
    public int[] getPosition() {
        return new int[]{
                (int)(this.zombie.getX() + this.zombie.getCenterX()),
                (int)(this.zombie.getY() + this.zombie.getCenterY()),
        };
    }

    public abstract void setDamageTexture();
    public abstract void setNormalTexture();
}
