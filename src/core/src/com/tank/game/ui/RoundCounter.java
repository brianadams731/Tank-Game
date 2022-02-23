package com.tank.game.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tank.game.FontText;

public final class RoundCounter {
    private static final FontText font;
    private RoundCounter(){
    }
    static{
        font = new FontText(52);
        font.setColor(1,0,0,1);
    }

    public static void drawRoundCounter(SpriteBatch batch, int round){
        font.drawTextCentered(String.format("Round %d",round),0,batch);
    }
}
