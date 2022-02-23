package com.tank.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.tank.game.ui.RoundCounter;

public class RoundController {
    private int roundCount;
    private long roundDrawTimeout;
    private boolean isNewRound;

    public RoundController(){
        this.roundCount = 1;
        roundDrawTimeout = TimeUtils.millis();
    }

    public int getRoundCount() {
        return roundCount;
    }

    public boolean isNewRound(){
        boolean newRound = this.isNewRound;
        if(this.isNewRound){
            this.roundCount++;
            this.roundDrawTimeout = TimeUtils.millis();
            this.isNewRound = false;
        }
        return newRound;
    }

    public void update(boolean isNewRound){
        this.isNewRound = isNewRound;
    }

    public void draw(SpriteBatch batch){
        if(TimeUtils.timeSinceMillis(this.roundDrawTimeout) < 1500){
            RoundCounter.drawRoundCounter(batch,this.roundCount);
        }
    }
}
