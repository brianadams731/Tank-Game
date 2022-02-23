package com.tank.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class InputController {
    private boolean leftKeyDown;
    private boolean rightKeyDown;
    private boolean forwardKeyDown;
    private boolean backKeyDown;
    private boolean fireKeyDown;

    final private int leftKey;
    final private int rightKey;
    final private int forwardKey;
    final private int backKey;
    final private int fireKey;

    private int mouseX;
    private int mouseY;


    public InputController(int forwardKey, int rightKey, int backKey, int leftKey, int fireKey){
        this.forwardKey = forwardKey;
        this.rightKey = rightKey;
        this.backKey = backKey;
        this.leftKey = leftKey;
        this.fireKey = fireKey;

        this.leftKeyDown = false;
        this.rightKeyDown = false;
        this.forwardKeyDown = false;
        this.backKeyDown = false;
        this.fireKeyDown = false;

    }

    public void update(OrthographicCamera camera){
        this.forwardKeyDown = Gdx.input.isKeyPressed(this.forwardKey);
        this.backKeyDown = Gdx.input.isKeyPressed(this.backKey);
        this.leftKeyDown = Gdx.input.isKeyPressed(this.leftKey);
        this.rightKeyDown = Gdx.input.isKeyPressed(this.rightKey);
        this.fireKeyDown = this.fireKey == 0?Gdx.input.isButtonPressed(this.fireKey):Gdx.input.isKeyPressed(this.fireKey);

        Vector3 screenToWorld = camera.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(),0));
        this.mouseX = (int)screenToWorld.x;
        this.mouseY = (int)screenToWorld.y;
    }

    public boolean isLeftKeyDown() {
        return leftKeyDown;
    }

    public boolean isRightKeyDown() {
        return rightKeyDown;
    }

    public boolean isForwardKeyDown() {
        return forwardKeyDown;
    }

    public boolean isBackKeyDown() {
        return backKeyDown;
    }

    public boolean isFireKeyDown() {
        return fireKeyDown;
    }

    public int getMouseX(){
        return mouseX;
    }

    public int getMouseY(){
        return mouseY;
    }

    @Override
    public String toString(){
        return String.format(
                "Forward: %b, Backward: %b, Left: %b, Right: %b, Fire %b",
                this.forwardKeyDown, this.backKeyDown, this.leftKeyDown, this.rightKeyDown, this.fireKeyDown
        );
    }
}
