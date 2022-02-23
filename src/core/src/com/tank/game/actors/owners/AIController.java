package com.tank.game.actors.owners;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tank.game.GameConstants;
import com.tank.game.actors.entities.Collides;

import java.util.ArrayList;
import java.util.Iterator;

public class AIController implements ColliderController {
    private ArrayList<AI> ai;
    private int zombiesToSpawn;

    public AIController(){
        this.ai = new ArrayList<>();
        this.zombiesToSpawn = 3;
        this.reset(1);
    }

    @Override
    public ArrayList<Collides> getColliders() {
        ArrayList<Collides> colliders = new ArrayList<>();
        for(ColliderController enemy:ai){
            colliders.addAll(enemy.getColliders());
        }
        return colliders;
    }


    public void reset(int roundCount) {
        this.zombiesToSpawn = roundCount + 3;
        this.ai = new ArrayList<>();

        for(int i = 0;i < this.zombiesToSpawn;i++){
            int x = (GameConstants.WINDOW_WIDTH/2) + (i * 25) - (((i * 25)+16)/2);
            int y = GameConstants.WINDOW_HEIGHT-32;
            this.ai.add(new AI(x,y));
        }
    }

    public void update(int[] playerPosition, int[] playerTwoPosition){
        for(AI enemy:ai){
            int [] enemyPosition = enemy.getPosition();
            int distToOne = getDistanceBetweenTwoCord(enemyPosition,playerPosition);
            int distToTwo = getDistanceBetweenTwoCord(enemyPosition,playerTwoPosition);

            enemy.update(distToOne<distToTwo? playerPosition:playerTwoPosition);
        }
        cullAI();
    }

    private int getDistanceBetweenTwoCord(int [] cordOne, int[] cordTwo){
        int xDist = Math.abs(cordOne[0]-cordTwo[0]);
        int yDist = Math.abs(cordOne[0]-cordTwo[0]);
        return xDist + yDist;
    }

    private void cullAI(){
        Iterator<AI> i = ai.iterator();
        while(i.hasNext()){
            AI ai = i.next();
            if(ai.getShouldRemove()){
                i.remove();
            }
        }
    }


    public void draw(SpriteBatch batch) {
        for(AI enemy:ai){
            enemy.draw(batch);
        }
    }

    public void dispose() {
        for(AI enemy:ai){
            enemy.dispose();
        }
    }

    public boolean shouldIncrementRound(){
        return this.ai.size() == 0;
    }
}
