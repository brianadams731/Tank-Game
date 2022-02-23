package com.tank.game.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.tank.game.actors.entities.Entity;

/*
*                           ------- To Consume -------
*   HealthBar(Entity, int) <---- Entity the hp bar is bound to, total hp of entity
*   void draw(SpriteBatch) <---- Renders Hp bar but ends sprite batch
*   void update(Entity, int) <---- Entity that hp bar is bound to, current hp of entity
 */

public class HealthBar {
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private final int totalWidth;
    private final int totalHp;


    private final Rectangle background;
    private final Rectangle health;

    public HealthBar(Entity entity, int totalHp){
        this.totalWidth = 20;
        this.totalHp = totalHp;

        this.background = new Rectangle(calcX(entity),calcY(entity),totalWidth,3);
        this.health = new Rectangle(calcX(entity),calcY(entity),totalWidth,3);
    }

    public void update(Entity entity, int hp){
        this.background.setX(this.calcX(entity));
        this.background.setY(this.calcY(entity));

        this.health.setX(this.calcX(entity));
        this.health.setY(this.calcY(entity));
        this.health.setWidth(this.calcHealth(hp));
    }

    public void draw(SpriteBatch batch){
        // TODO Consider refactoring this so batch is not being reset!
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1,0,0,1);
        shapeRenderer.rect(this.background.x,this.background.y,this.background.width,this.background.height);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0,1,0,1);
        shapeRenderer.rect(this.health.x,this.health.y,this.health.width,this.health.height);

        shapeRenderer.end();
        batch.begin();
    }

    private float calcX(Entity entity){
        return (entity.getX() + entity.getCenterX()) - ((float) this.totalWidth/2);
    }

    private float calcY(Entity entity){
        return entity.getY() + entity.getHeight() + 5;
    }

    private float calcHealth(int hp){
        return ((float) hp / this.totalHp) * this.totalWidth;
    }
}
