package com.tank.game.actors.owners;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tank.game.GameConstants;
import com.tank.game.actors.entities.Collides;
import com.tank.game.actors.entities.GameObject;
import com.tank.game.actors.entities.powerUps.PUHealth;
import com.tank.game.actors.entities.powerUps.PULazer;
import com.tank.game.actors.entities.powerUps.PUMachineGun;
import com.tank.game.actors.entities.powerUps.PowerUp;

import java.util.ArrayList;
import java.util.Iterator;

public class PowerUpController implements GameObject, ColliderController, Resettable {
    ArrayList<PowerUp> powerUps;
    public PowerUpController(){
        this.reset();
    }

    @Override
    public ArrayList<Collides> getColliders(){
        ArrayList<Collides> collides = new ArrayList<>();
        collides.addAll(powerUps);
        return collides;
    }
    @Override
    public void update() {
        for(PowerUp powerUp:powerUps){
            powerUp.update();
        }
        cullPowerUps();
    }

    @Override
    public void draw(SpriteBatch batch) {
        for(PowerUp powerUp:powerUps){
            powerUp.draw(batch);
        }
    }

    @Override
    public void dispose(){
        for(PowerUp powerUp:powerUps){
            powerUp.dispose();
        }
    }

    private void cullPowerUps(){
        Iterator<PowerUp> i = powerUps.iterator();
        while(i.hasNext()){
            PowerUp powerUp = i.next();
            if(powerUp.getShouldRemove()){
                i.remove();
            }
        }
    }
    @Override
    public void reset(){
        powerUps = new ArrayList<>();
        powerUps.add(new PUHealth((GameConstants.WINDOW_WIDTH/2) - 8,GameConstants.WINDOW_WIDTH - (GameConstants.WINDOW_HEIGHT/4) - 8));
        powerUps.add(new PUHealth((GameConstants.WINDOW_WIDTH/2) - 8,GameConstants.WINDOW_WIDTH - (3*(GameConstants.WINDOW_HEIGHT/4)) - 8));
        powerUps.add(new PULazer((GameConstants.WINDOW_WIDTH/11) - 8,GameConstants.WINDOW_WIDTH - (GameConstants.WINDOW_HEIGHT/2) - 8));
        powerUps.add(new PUMachineGun(GameConstants.WINDOW_WIDTH - (GameConstants.WINDOW_WIDTH/11) - 8,GameConstants.WINDOW_WIDTH - (GameConstants.WINDOW_HEIGHT/2) - 8));
    }

}
