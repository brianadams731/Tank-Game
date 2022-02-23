package com.tank.game.menu;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.tank.game.FontText;
import com.tank.game.GameConstants;

public class MainMenu {
    Texture btnTextureInit;
    Texture btnTextureHover;
    MenuController menuControllerRef;

    FontText font;
    Sprite gameStartButton;


    public MainMenu(MenuController menuController){
        this.menuControllerRef = menuController;

        this.btnTextureInit = new Texture("gameStartBtn.png");
        this.btnTextureHover = new Texture("gameStartBtnActive.png");

        this.gameStartButton = new Sprite(btnTextureInit);
        this.gameStartButton.setX(GameConstants.WINDOW_WIDTH/2 - this.gameStartButton.getWidth()/2);
        this.gameStartButton.setY(GameConstants.WINDOW_HEIGHT/2 - this.gameStartButton.getHeight()/2 - 50);

        this.font = new FontText(54);
        this.font.setColor(1,0,0,1);
    }

    public void draw(SpriteBatch batch){
        gameStartButton.draw(batch);
        font.drawTextCentered("Tank Game",50, batch);
    }

    public void update(OrthographicCamera camera){
        handleGameStartBrn(camera);
    }

    private void handleGameStartBrn(OrthographicCamera camera){
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
        Vector3 mouseInput = new Vector3(x, y, 0);
        camera.unproject(mouseInput);

        if(gameStartButton.getBoundingRectangle().contains(mouseInput.x, mouseInput.y)){ // If courser is over button
            this.gameStartButton.setTexture(this.btnTextureHover);
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){      // if courser is down
                this.menuControllerRef.setGameStart(true);
            }
        }else{
            this.gameStartButton.setTexture(this.btnTextureInit);
        }
    }
}
