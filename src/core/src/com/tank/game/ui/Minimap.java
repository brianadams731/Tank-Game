package com.tank.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tank.game.GameConstants;
import com.tank.game.actors.entities.Breakable;
import com.tank.game.actors.entities.Collides;
import com.tank.game.actors.entities.powerUps.PUHealth;
import com.tank.game.actors.entities.powerUps.PowerUp;
import com.tank.game.actors.entities.tanks.Tank;
import com.tank.game.actors.entities.zombies.Zombie;
import com.tank.game.gameWorld.foreground.Foreground;

import java.awt.*;
import java.util.ArrayList;

public class Minimap {
    private final ShapeRenderer shapeRenderer;
    private final Rectangle base;
    private final int height;
    private final int width;

    public Minimap(){
        this.shapeRenderer = new ShapeRenderer();
        this.height = 150;
        this.width = 150;
        base = new Rectangle(0, GameConstants.WINDOW_HEIGHT - this.height,this.width,this.height);
    }

    public void drawMapBase(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(80/255f,80/255f,80/255f,.2f);
        shapeRenderer.rect(this.base.x,this.base.y,this.base.width,this.base.height);
        shapeRenderer.end();
    }

    private void drawPlayer(Collides collider){
        drawCircle(collider, 0,1,0,.4f);
    }
    private void drawZombie(Collides collider){
        drawCircle(collider, 1,0,0,.4f);
    }
    private void drawPowerUp(Collides collider){
        if(collider instanceof PUHealth){
            drawSquare(collider, 0,1,0,.4f);
        }else{
            drawSquare(collider, 0,0,1,.4f);
        }
    }

    private void drawForeground(Collides collider){
        if(collider instanceof Breakable){
            if(((Breakable)collider).isBroken()){
                return;
            }
        }
        drawSquare(collider,1,223/255f,0,.4f);
    }

    private void drawCircle(Collides collider, float r, float g, float b, float a){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(r,g,b,a);
        shapeRenderer.circle(
                ((collider.getEntity().getX()/GameConstants.WINDOW_WIDTH)*this.base.width),
                (collider.getEntity().getY()/GameConstants.WINDOW_HEIGHT)*this.base.height + GameConstants.WINDOW_HEIGHT - this.height,
                5
        );
        shapeRenderer.end();
    }

    private void drawSquare(Collides collider, float r, float g, float b, float a){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(r,g,b,a);
        shapeRenderer.rect(((collider.getEntity().getX()/GameConstants.WINDOW_WIDTH)*this.base.width),
                ((collider.getEntity().getY()/GameConstants.WINDOW_HEIGHT)*this.base.height + GameConstants.WINDOW_HEIGHT - this.height)
                ,3,3);
        shapeRenderer.end();
    }

    public void draw(ArrayList<Collides> colliders){
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        this.drawMapBase();

        for(Collides collider:colliders){
            if(collider instanceof Tank){
                drawPlayer(collider);
            }else if(collider instanceof Zombie){
                drawZombie(collider);
            }else if(collider instanceof PowerUp){
                drawPowerUp(collider);
            }else if(collider instanceof Foreground){
                drawForeground(collider);
            }
        }

        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

}
