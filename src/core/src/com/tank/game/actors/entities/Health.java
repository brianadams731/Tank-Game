package com.tank.game.actors.entities;

import com.badlogic.gdx.utils.TimeUtils;

public class Health{
    private int hp;
    private final int maxHp;
    private int lives;
    private boolean canTakeDamage;
    private boolean respawn;

    private long timeLastTakenDamage;
    private final int mileSecondsOfDamageThrottle;

    public Health(int hp, int lives){
        this.hp = hp;
        this.maxHp = hp;
        this.lives = lives;
        this.canTakeDamage = true;
        this.timeLastTakenDamage = 0;
        this.mileSecondsOfDamageThrottle = 50;
    }

    public int getHp(){
        return this.hp;
    }
    public int getLives(){
        return this.lives;
    }

    public void canTakeDamageTimeOut(){
        if(TimeUtils.timeSinceMillis(this.timeLastTakenDamage) >= mileSecondsOfDamageThrottle){
            this.canTakeDamage = true;
        }else{
            this.canTakeDamage = false;
        }
    }

    public boolean shouldRemove(){
        return this.lives < 1;
    }

    public void addHp(int alterHpBy){
        this.hp = Math.min(this.maxHp,this.hp + alterHpBy);
    }

    public void removeHp(int alterHpBy) {
        this.canTakeDamageTimeOut();
        if(canTakeDamage){
            this.hp -= alterHpBy;
            this.timeLastTakenDamage = TimeUtils.millis();
            if(this.isHpEmpty()){
                this.removeLife();
                if(this.lives >= 1){
                    this.respawn = true;
                }
            }
        }
    }

    public boolean getRespawn(){
        boolean res = this.respawn;
        if(respawn){ // Automatically toggles to false when return true, prevents double respawns
            respawn = false;
        }
        return res;
    }

    public void respawn(){
        this.hp = this.maxHp;
    }

    private boolean isHpEmpty(){
        return this.hp <= 0;
    }
    private void removeLife(){
        this.lives--;
    }
}
