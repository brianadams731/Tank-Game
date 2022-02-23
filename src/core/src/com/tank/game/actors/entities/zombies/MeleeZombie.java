package com.tank.game.actors.entities.zombies;

import com.tank.game.AssetManager;
import com.tank.game.actors.entities.Damages;

import java.util.Random;

public class MeleeZombie extends Zombie implements Damages {
    int damage;
    public MeleeZombie(float x, float y){
        super(
                AssetManager.getMeleeZombie(),0,1,
                x,y, 3,2, 100
        );
        damage = 5;
        Random rand = new Random();
        float randomSpeed = 2 + rand.nextFloat() * (5.5f - 2);
        setSpeed(randomSpeed);
    }

    @Override
    public int getDamage(){
        return this.damage;
    }

    @Override
    public void setDamageTexture(){
        this.switchZombieTexture(AssetManager.getMeleeZombieHit());
        this.setShouldSwitchToNormalTexture();
    }
    @Override
    public void setNormalTexture(){
        this.switchZombieTexture(AssetManager.getMeleeZombie());
    }
}
