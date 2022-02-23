package com.tank.game.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tank.game.FontText;


public class GameOverMenu {
    private final FontText font;


    int roundCount;

    public GameOverMenu(){
        font = new FontText(42);
        font.setColor(1,0,0,1);
        this.roundCount = 0;
    }

    public void setRoundCount(int roundCount){
        this.roundCount = roundCount;
    }

    public void draw(SpriteBatch batch){
        font.drawTextCentered("Game Over",50,batch);
        font.drawTextCentered(String.format("Survived Until Round %d", this.roundCount),0,batch);
    }
}
