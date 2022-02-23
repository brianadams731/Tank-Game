package com.tank.game.menu;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuController {
    private boolean gameStart;

    MainMenu mainMenu;
    GameOverMenu gameOverMenu;

    Sprite splashScreen;
    public MenuController(){
        this.gameStart = false;

        this.mainMenu = new MainMenu(this);
        this.gameOverMenu = new GameOverMenu();

        this.splashScreen = new Sprite(new Texture("splashscreen.png"));
    }

    public void update(OrthographicCamera camera){
        mainMenu.update(camera);
    }


    public void setGameStart(boolean gameStart){
        this.gameStart = gameStart;
    }


    public void drawStartScreen(SpriteBatch batch){
        batch.begin();
        splashScreen.draw(batch);
        mainMenu.draw(batch);
        batch.end();
    }

    public void setGameOverRoundCount(int roundCount){
        this.gameOverMenu.setRoundCount(roundCount);
    }

    public void drawGameOverScreen(SpriteBatch batch){
        batch.begin();
        splashScreen.draw(batch);
        gameOverMenu.draw(batch);
        batch.end();
    }

    public boolean getGameStart(){
        return this.gameStart;
    }
}
