package com.tank.game.actors.entities.tanks;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tank.game.Asset;
import com.tank.game.GameConstants;
import com.tank.game.actors.entities.*;
import com.tank.game.actors.entities.bullets.BulletBag;
import com.tank.game.actors.entities.powerUps.*;
import com.tank.game.actors.owners.ColliderController;
import com.tank.game.ui.HealthBar;

import java.util.ArrayList;


public abstract class Tank implements GameObject,
        Shoots,
        Movable,
        RotatesToPoint,
        Collides,
        ColliderController,
        GivesPosition,
        GivesLifeCount,
        Removeable,
        Stoppable,
        Solid {

    private final Entity base;
    private final Entity top;

    private final BulletBag bulletBag;

    private final Health hp;

    private final MoveBoundary moveBoundary;
    private boolean shouldRemove;

    private float baseAngle;
    private final int rotationSpeed;

    private final int speed;

    private final HealthBar healthBar;

    private final float respawnX;
    private final float respawnY;


    public Tank(Asset base, Asset top, int speed, int rotationSpeed, float initialAngle, float x, float y){
        this.base = new Entity(base,x ,y);
        this.top = new Entity(top, x, y);

        this.hp = new Health(100,3);
        this.base.setRotation(initialAngle - 90);
        this.top.setRotation(initialAngle - 90);
        this.speed = speed;
        this.rotationSpeed = rotationSpeed;
        this.baseAngle = initialAngle;
        this.bulletBag = new BulletBag("mg");
        this.shouldRemove = false;

        this.moveBoundary = new MoveBoundary(this.base);

        this.healthBar = new HealthBar(this.base,this.hp.getHp());

        this.respawnX = x;
        this.respawnY = y;
    }

    // -- Collides --//
    @Override
    public Entity getEntity(){
        return this.base;
    }
    @Override
    public void collided(Collides collider){
        if(collider instanceof HealthMod){
            this.hp.addHp(((HealthMod)collider).getHealthMod());
        }
        if(collider instanceof Damages){
            this.hp.removeHp(((Damages)collider).getDamage());
        }
        if(collider instanceof ChangeBulletType){
            this.bulletBag.setBullets(((ChangeBulletType)collider).getBulletType());
        }
        if(collider instanceof Solid){
            this.moveBoundary.determineIfStopped(collider);
        }
        if(collider instanceof Breakable){
            if(!((Breakable)collider).isBroken()){
                this.moveBoundary.determineIfStopped(collider);
            }
        }
    }

    // -- ColliderController -- //
    @Override
    public ArrayList<Collides> getColliders(){
        return this.bulletBag.getColliders();
    }

    // -- GameObject ---//
    @Override
    public void update(){
        this.bulletBag.update();

        if(this.hp.getRespawn()){
            this.hp.respawn();
            this.getEntity().setX(this.respawnX);
            this.getEntity().setY(this.respawnY);
            this.top.setX(this.getEntity().getX());
            this.top.setY(this.getEntity().getY());
        }

        if(this.hp.shouldRemove()){
            this.shouldRemove = true;
        }

        this.healthBar.update(this.base, this.hp.getHp());
    }
    @Override
    public void draw(SpriteBatch batch){
        this.base.draw(batch);
        this.top.draw(batch);
        this.bulletBag.draw(batch);

        this.healthBar.draw(batch);
    }
    @Override
    public void dispose(){
        this.bulletBag.dispose();
    }

    // --- Shoots ---//
    @Override
    public void shoot(){
        this.bulletBag.addBullets(
                bulletStartX(),
                bulletStartY(),
                bulletRotation()
        );
    }

    // --- Self --- //
    private float bulletStartY(){
        float[] topVertex = this.top.getVertices();
        float topLeftY = topVertex[SpriteBatch.Y2];
        float topRightY = topVertex[SpriteBatch.Y3];
        return ((topLeftY + topRightY)/2);
    }
    private float bulletStartX(){
        float[] topVertex = this.top.getVertices();
        float topLeftX = topVertex[SpriteBatch.X2];
        float topRightX = topVertex[SpriteBatch.X3];
        return (topLeftX + topRightX)/2;
    }
    private float bulletRotation(){
        return this.top.getRotation() + 90;
    }

    // --- Movable --- //
    @Override
    public void moveForward(){
        this.base.setX(this.base.getX() + this.getVelocityX(true));
        this.base.setY(this.base.getY() + this.getVelocityY(true));
        this.top.setX(this.base.getX());
        this.top.setY(this.base.getY());
    }
    @Override
    public void moveBackward(){
        this.base.setX(this.base.getX() - this.getVelocityX(false));
        this.base.setY(this.base.getY() - this.getVelocityY(false));
        this.top.setX(this.base.getX());
        this.top.setY(this.base.getY());
    }
    @Override
    public void turnClockwise(){
        this.baseAngle -= this.rotationSpeed;
        this.base.setRotation(this.baseAngle - 90);
    }
    @Override
    public void turnCounterClockwise(){
        this.baseAngle += this.rotationSpeed;
        this.base.setRotation(this.baseAngle - 90);
    }

    // -- Stoppable --//
    @Override
    public void resetStop() {
        this.moveBoundary.resetStop();
    }

    // ---- RotatesToPoint ----//
    @Override
    public void rotateToPoint(int mouseX, int mouseY){
        Vector2 tankPos = new Vector2((this.top.getX() + this.top.getCenterX()),(this.top.getY() + this.top.getCenterY()));
        Vector2 mousePos = new Vector2(mouseX, mouseY);
        float angle = new Vector2(mousePos).sub(tankPos).angleDeg();
        this.top.setRotation(angle - 90);
    }

    public void rotateTopClockwise(){
        this.top.setRotation(this.top.getRotation()-5);
    }

    public void rotateTopCounterClockwise(){
        this.top.setRotation(this.top.getRotation()+5);
    }

    // -- GivesPosition -- //
    @Override
    public int[] getPosition() {
        return new int[]{
                (int)(this.base.getX() + this.base.getCenterX()),
                (int)(this.base.getY() + this.base.getCenterY()),
        };
    }

    // -- GivesLifeCount --//
    @Override
    public int getLifeCount(){
        return this.hp.getLives();
    }

    // -- Removable -- //
    @Override
    public boolean getShouldRemove(){
        return this.shouldRemove;
    }

    private int getVelocityX(boolean moveForward){
        int velocityX = (int) Math.round(this.speed * Math.cos(Math.toRadians(this.baseAngle)));
        return moveBoundary.normalizeX(velocityX, moveForward);
    }
    private int getVelocityY(boolean moveForward){
        int velocityY = (int) Math.round(this.speed * Math.sin(Math.toRadians(this.baseAngle)));
        return moveBoundary.normalizeY(velocityY, moveForward);
    }

    public void setOutOfBounds(){
        this.getEntity().setX(-GameConstants.WINDOW_WIDTH*2);
        this.getEntity().setY(-GameConstants.WINDOW_HEIGHT*2);
    }
}
