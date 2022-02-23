package com.tank.game.gameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.tank.game.actors.entities.GameObject;
import com.tank.game.gameWorld.background.FloorCracked;
import com.tank.game.gameWorld.background.FloorCrackedTwo;
import com.tank.game.gameWorld.background.FloorUncracked;
import com.tank.game.gameWorld.foreground.BreakableBox;
import com.tank.game.gameWorld.foreground.SolidWall;

import java.util.ArrayList;

/*
*       --- Foreground ----
*   1 = Solid Wall
*   2 = Breakable Box
*
*       --- Background ---
*   10 = Uncracked Floor
*   11 = Cracked Floor #1
*   12 = Cracked Floor #2
 */


public class GameWorldDeserializer {
    private final ArrayList<GameObject> foreground;
    private final ArrayList<GameObject> background;

    public GameWorldDeserializer(){
        foreground = new ArrayList<>();
        background = new ArrayList<>();


        ArrayList<ArrayList<Integer>> foregroundUnparsed = loadMatrixFromCSV("foreground.csv");
        parseMatrixIntoGameWorld(foregroundUnparsed);

        ArrayList<ArrayList<Integer>> backgroundUnparsed = loadMatrixFromCSV("background.csv");
        parseMatrixIntoGameWorld(backgroundUnparsed);

    }

    private ArrayList<ArrayList<Integer>> loadMatrixFromCSV(String csvFileName){
        FileHandle handle = Gdx.files.local(csvFileName);
        String worldString = handle.readString();
        String[] rows= worldString.split("\r\n");

        ArrayList<ArrayList<Integer>> matrix = initMatrix(rows.length);

        for(int i = 0; i< rows.length; i++){
            String[] row = rows[i].split(",");
            for(int j = 0; j<row.length;j++){
                try {
                    matrix.get(j).add(Integer.parseInt(row[j]));
                }catch(NumberFormatException e){
                    System.out.println("NUMBER DROPPED");
                    matrix.get(i).add(-1);
                }
            }
        }

        return matrix;
    }

    private ArrayList<ArrayList<Integer>> initMatrix( int numberOfColumns ){
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        for(int i = 0;i <  numberOfColumns;i++){
            ret.add(new ArrayList<Integer>());
        }
        return ret;
    }

    private void parseMatrixIntoGameWorld(ArrayList<ArrayList<Integer>> matrix){
        for(int i = 0; i<matrix.size(); i++){
            for(int j = 0; j<matrix.get(i).size(); j++){
                int tileNumber = matrix.get(i).get(j);
                int x = i * 16;
                int y = ((matrix.get(i).size()-1)*16) - (j * 16); // Flips y, to unflip use (j * 16)
                // Foreground
                if(tileNumber==1){
                    this.addSolidWall(x, y);
                }else if(tileNumber == 2){
                    this.addBreakableBox(x,y);
                }
                // Background
                else if(tileNumber == 10){
                    this.addFloorUncracked(x,y);
                }else if(tileNumber == 11){
                    this.addFloorCrackedOne(x,y);
                }else if(tileNumber == 12){
                    this.addFloorCrackedTwo(x,y);
                }
            }
        }
    }

    private void addSolidWall(float x, float y){
        foreground.add(new SolidWall(x, y));
    }
    private void addBreakableBox(float x,float y){
        foreground.add(new BreakableBox(x,y));
    }

    private void addFloorUncracked(float x, float y){
        background.add(new FloorUncracked(x,y));
    }
    private void addFloorCrackedOne(float x, float y){
        background.add(new FloorCracked(x,y));
    }
    private void addFloorCrackedTwo(float x, float y){
        background.add(new FloorCrackedTwo(x,y));
    }

    public ArrayList<GameObject> getForeground() {
        return this.foreground;
    }
    public ArrayList<GameObject> getBackground() {
        return this.background;
    }
}
