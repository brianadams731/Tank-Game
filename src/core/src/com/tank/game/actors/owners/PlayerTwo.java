package com.tank.game.actors.owners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.tank.game.GameConstants;
import com.tank.game.InputController;
import com.tank.game.actors.entities.tanks.Panther;
import com.tank.game.actors.entities.tanks.Tank;

public class PlayerTwo extends Player{
    public PlayerTwo(){
        super(
                new Panther(GameConstants.WINDOW_WIDTH-60,30, 180),
                new InputController(Input.Keys.W, Input.Keys.D, Input.Keys.S, Input.Keys.A, Input.Keys.SPACE)
        );
    }

    @Override
    protected void rotateTopToPoint(){
        if(Gdx.input.isKeyPressed(Input.Keys.E)){
            ((Tank)this.getItem()).rotateTopClockwise();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.Q)){
            ((Tank)this.getItem()).rotateTopCounterClockwise();
        }
    }
}
