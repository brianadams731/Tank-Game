package com.tank.game.actors.owners;

import com.badlogic.gdx.Input;
import com.tank.game.InputController;
import com.tank.game.actors.entities.RotatesToPoint;
import com.tank.game.actors.entities.tanks.Panther;

public class PlayerOne extends Player{
    public PlayerOne(){
        super(
                new Panther(30,30, 0),
                new InputController(Input.Keys.UP, Input.Keys.RIGHT, Input.Keys.DOWN, Input.Keys.LEFT, Input.Buttons.LEFT)
        );
    }
    @Override
    protected void rotateTopToPoint(){
        ((RotatesToPoint)this.getItem()).rotateToPoint(input.getMouseX(), input.getMouseY());
    }
}
