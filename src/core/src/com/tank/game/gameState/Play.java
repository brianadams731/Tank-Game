package com.tank.game.gameState;

import com.tank.game.TankGame;
import com.tank.game.actors.entities.*;
import com.tank.game.actors.owners.ColliderController;

import java.util.ArrayList;

public class Play implements GameState{

    @Override
    public void render(TankGame tankGame) {

        this.update(tankGame);
        this.manageCollisions(tankGame);
        this.draw(tankGame);

    }

    private void  draw(TankGame tankGame){
        // ---------- Draw Start ------------ //
        tankGame.getBatch().begin();
        // -----------
        tankGame.getBackgrounds().draw(tankGame.getBatch());
        tankGame.getForegrounds().draw(tankGame.getBatch());

        tankGame.getPowerUps().draw(tankGame.getBatch());
        tankGame.getPlayer().draw(tankGame.getBatch());
        tankGame.getPlayerTwo().draw(tankGame.getBatch());
        tankGame.getAiController().draw(tankGame.getBatch());

        tankGame.getLifeCounter().draw(tankGame.getBatch(), ((GivesLifeCount)tankGame.getPlayer()).getLifeCount());
        tankGame.getLifeCounterPlayerTwo().draw(tankGame.getBatch(), ((GivesLifeCount)tankGame.getPlayerTwo()).getLifeCount());
        tankGame.getRoundController().draw(tankGame.getBatch());
        // ----------
        tankGame.getBatch().end();
        // ---------- Draw End ------------ //

        // -------------- ShapeRender Start ------------ //
        tankGame.getMinimap().draw(aggregateColliders(tankGame));
        // -------------- ShapeRender End ------------- //
    }

    private void manageCollisions(TankGame tankGame){
        ArrayList<Collides> powerUpCollide = ((ColliderController)tankGame.getPowerUps()).getColliders();
        ArrayList<Collides> playerCollide = ((ColliderController)tankGame.getPlayer()).getColliders();
        ArrayList<Collides> playerTwoCollide = ((ColliderController)tankGame.getPlayerTwo()).getColliders();
        ArrayList<Collides> aiColliders = ((ColliderController)tankGame.getAiController()).getColliders();
        ArrayList<Collides> foregroundCollides = ((ColliderController)tankGame.getForegrounds()).getColliders();


        for(Collides player:playerCollide){	// Player
            for(Collides powerUp:powerUpCollide){	// Player and PowerUps
                if(Entity.detectCollision( player, powerUp)){
                    player.collided(powerUp);
                    powerUp.collided(player);
                }
            }
            for(Collides ai:aiColliders){				// Player and AI
                if(Entity.detectCollision( player, ai)){
                    player.collided(ai);
                    ai.collided(player);
                }
            }
            for(Collides foreground:foregroundCollides){ // Player and Foreground
                if(Entity.detectCollision(player, foreground)){
                    player.collided(foreground);
                    foreground.collided(player);
                }
            }
        }


        for(Collides player:playerTwoCollide){	// Player Two
            for(Collides powerUp:powerUpCollide){	// Player and PowerUps
                if(Entity.detectCollision( player, powerUp)){
                    player.collided(powerUp);
                    powerUp.collided(player);
                }
            }
            for(Collides ai:aiColliders){				// Player and AI
                if(Entity.detectCollision( player, ai)){
                    player.collided(ai);
                    ai.collided(player);
                }
            }
            for(Collides foreground:foregroundCollides){ // Player and Foreground
                if(Entity.detectCollision(player, foreground)){
                    player.collided(foreground);
                    foreground.collided(player);
                }
            }
        }


        for(Collides ai:aiColliders){ // AI
            for(Collides foreground:foregroundCollides){ // AI and Foreground
                if(Entity.detectCollision(ai, foreground)){
                    ai.collided(foreground);
                    foreground.collided(ai);
                }
            }
        }

    }

    private ArrayList<Collides> aggregateColliders(TankGame tankGame){
        ArrayList<Collides> colliders = new ArrayList<>();
        colliders.addAll(((ColliderController)tankGame.getPlayer()).getColliders());
        colliders.addAll(((ColliderController)tankGame.getPlayerTwo()).getColliders());
        colliders.addAll(((ColliderController)tankGame.getForegrounds()).getColliders());
        colliders.addAll(((ColliderController)tankGame.getPowerUps()).getColliders());
        colliders.addAll(((ColliderController)tankGame.getAiController()).getColliders());
        return colliders;
    }

    private boolean shouldIncrementRound(TankGame tankGame){
        return tankGame.getAiController().shouldIncrementRound();
    }

    private void update(TankGame tankGame){
        tankGame.getPlayer().update(tankGame.getCamera());
        tankGame.getPlayerTwo().update(tankGame.getCamera());

        tankGame.getAiController().update(((GivesPosition) tankGame.getPlayer()).getPosition(), ((GivesPosition) tankGame.getPlayerTwo()).getPosition());
        tankGame.getPowerUps().update();

        tankGame.getForegrounds().update();
        tankGame.getBackgrounds().update();

        tankGame.getRoundController().update(this.shouldIncrementRound(tankGame));

        //tankGame.getCamera().position.x = ((GivesPosition) tankGame.getPlayer()).getPosition()[0];
        //tankGame.getCamera().position.y = ((GivesPosition) tankGame.getPlayer()).getPosition()[1];

        if (tankGame.getRoundController().isNewRound()) {
            tankGame.getPowerUps().reset();
            tankGame.getAiController().reset(tankGame.getRoundController().getRoundCount());
        }

        this.setGameOver(tankGame);
    }

    private void setGameOver(TankGame tankGame){
        if(((Removeable)tankGame.getPlayer()).getShouldRemove() && ((Removeable)tankGame.getPlayerTwo()).getShouldRemove()){
            tankGame.getMenuController().setGameOverRoundCount(tankGame.getRoundController().getRoundCount());
            tankGame.setState(new GameOver());
        }
    }
}
