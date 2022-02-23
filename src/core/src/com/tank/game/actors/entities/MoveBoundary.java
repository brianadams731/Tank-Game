package com.tank.game.actors.entities;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class MoveBoundary {
    private final Entity owner;
    private boolean canMovePositiveY;
    private boolean canMoveNegativeY;
    private boolean canMovePositiveX;
    private boolean canMoveNegativeX;

    public MoveBoundary(Entity owner){
        this.owner =  owner;
        this.canMovePositiveY = true;
        this.canMovePositiveX = true;
        this.canMoveNegativeY = true;
        this.canMoveNegativeX = true;
    }

    public int normalizeX(int x, boolean forward){
        int ret;
        if(forward) {
            if (x >= 0 && this.canMovePositiveX) {
                ret = x;
            } else if (x < 0 && this.canMoveNegativeX) {
                ret = x;
            } else {
                ret = 0;
            }
        }else{
            if (x < 0 && this.canMovePositiveX) {
                ret = x;
            } else if (x >= 0 && this.canMoveNegativeX) {
                ret = x;
            } else {
                ret = 0;
            }
        }
        return ret;
    }

    public int normalizeY(int y, boolean forward){

        int ret;
        if(forward){
            if(y>=0 && this.canMovePositiveY){
               ret = y;
            }else if(y<0 && this.canMoveNegativeY){
               ret = y;
            }else{
              ret = 0;
            }
        }else{
            if(y < 0 && this.canMovePositiveY){
                ret = y;
            }else if(y>=0 && this.canMoveNegativeY){
                ret = y;
            }else{
                ret = 0;
            }
        }
        return ret;
    }

    public void determineIfStopped(Collides other){
        // TODO CITE CODE SOURCE!!!!!!!!! https://stackoverflow.com/questions/22464893/libgdx-detect-side-touching-between-rectangles-side-collision
        Rectangle r1 = owner.getRectangle();
        Rectangle r2 = other.getEntity().getRectangle();

        Rectangle intersection = new Rectangle();
        Intersector.intersectRectangles(r1, r2, intersection);

        if(intersection.x > r1.x){
            this.canMovePositiveX = false;
        }
            //Intersects with right side
        if(intersection.y > r1.y){
            this.canMovePositiveY = false;
        }
                //Intersects with top side
        if(intersection.x + intersection.width < r1.x + r1.width){
            this.canMoveNegativeX = false;
        }
                    //Intersects with left side
        if(intersection.y + intersection.height < r1.y + r1.height){
            this.canMoveNegativeY = false;
        }
        //Intersects with bottom side
    }

    public void resetStop(){
        this.canMovePositiveY = true;
        this.canMoveNegativeY = true;
        this.canMovePositiveX = true;
        this.canMoveNegativeX = true;
    }
}
