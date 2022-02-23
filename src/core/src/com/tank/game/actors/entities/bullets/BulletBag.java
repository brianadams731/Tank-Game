package com.tank.game.actors.entities.bullets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.tank.game.AudioManager;
import com.tank.game.actors.entities.Collides;
import com.tank.game.actors.entities.GameObject;
import com.tank.game.actors.owners.ColliderController;

import java.util.ArrayList;
import java.util.Iterator;

/* ------------- To Use Implement This In Class Consuming -----------------------
*   BulletBag(String) String <-- Bullet Type
*   addBullet(float,float,float)  <--- x, y, rotation
*   ArrayList<Colliders> getColliders() <--- Returns an Array of Colliders taken from bullets
*   Draw(SpriteBatch)   <---- Batch to paint
*   dispose()
*   update()
* */

public class BulletBag implements GameObject, ColliderController {
    private final ArrayList<Bullet> bullets;
    private String bulletType;

    private long timeLastShot;
    private int bulletTimeOut;

    public BulletBag(String bagType){
        this.bullets = new ArrayList<>();
        this.bulletType = bagType; // "mg, lazer"

        this.bulletTimeOut = 0; // DEFAULT WILL CHANGE WHEN BULLETS GET ADDED
    }


    private void cullBullets(){
        Iterator<Bullet> i = bullets.iterator();
        while(i.hasNext()){
            Bullet bullet = i.next();
            if(bullet.getShouldRemove()){
                i.remove();
            }
        }
    }

    public void addBullets(float bulletStartX, float bulletStartY, float rotation){
        if(TimeUtils.timeSinceMillis(timeLastShot) < this.bulletTimeOut){   // Guard Clause Enforces Bullet Throttle
            return;
        }
        this.timeLastShot = TimeUtils.millis();
        if(this.bulletType.equalsIgnoreCase("lazer")){
            this.bulletTimeOut = 0;
            this.bullets.add(
                    new Lazer(
                            bulletStartX,bulletStartY,rotation
                    )
            );
            AudioManager.getLazerFire().play();

        }else if(this.bulletType.equalsIgnoreCase("mg")){
            this.bulletTimeOut = 120;
            this.bullets.add(
                    new MachineGun(
                            bulletStartX,bulletStartY,rotation
                    )
            );
            AudioManager.getMgFire().play();
            
        }
    }

    @Override
    public void update() {
        for (Bullet bullet: bullets){
            bullet.update();
            if(!bullet.inBounds()){
                bullet.setShouldRemove(true);
            }
        }
        cullBullets();
    }

    @Override
    public void draw(SpriteBatch batch) {
        for(Bullet bullet : bullets){
            bullet.draw(batch);
        }
    }

    @Override
    public void dispose() {
        for(Bullet bullet:bullets){
            bullet.dispose();
        }
    }

    @Override
    public ArrayList<Collides> getColliders() {
        ArrayList<Collides> colliders = new ArrayList<>();
        colliders.addAll(bullets);
        return colliders;
    }

    public void setBullets(String bullet){
        this.bulletType = bullet;
    }
}
