package com.tank.game;

import com.badlogic.gdx.graphics.Texture;

public final class Asset {
    Texture texture;
    int width;
    int height;

    public Asset(Texture textureSheet){
        this.texture = textureSheet;
        this.width = textureSheet.getWidth();
        this.height = textureSheet.getHeight();
    }

    public Asset(Texture textureSheet, int width, int height){
        this.texture = textureSheet;
        this.width = width;
        this.height = height;
    }

    public Texture getTexture(){
        return this.texture;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public int getOffsetValueY(int index){
        return this.height * index;
    }

    public int getOffsetValueX(int index){
        return this.width * index;
    }

    public void dispose(){
        this.texture.dispose();
    }
}
