package com.tank.game;

import com.badlogic.gdx.graphics.Texture;

public final class AssetManager {
    static private final Asset bullets;

    static private final Asset powerUps;

    static private final Asset meleeZombie;
    static private final Asset meleeZombieHit;
    static private final Asset shootingZombie;

    static private final Asset pantherTankBase;
    static private final Asset pantherTankTop;

    static private final Asset shermanTankBase;
    static private final Asset shermanTankTop;

    static private final Asset dungeonTiles;

    private AssetManager(){}
    static{
        bullets = new Asset(new Texture("bullets.png"), 16,16);

        powerUps = new Asset(new Texture("upgrades.png"), 16, 16);

        meleeZombie = new Asset(new Texture("enemy-attack.png"),16,16);
        meleeZombieHit = new Asset(new Texture("enemy-attack-hit.png"),16,16);
        shootingZombie = new Asset(new Texture("enemy-shoot.png"),16,16);

        pantherTankBase = new Asset(new Texture("panther-base-trim.png"));
        pantherTankTop = new Asset(new Texture("panther-top-trim.png"));

        shermanTankBase = new Asset(new Texture("sherman-base.png"));
        shermanTankTop = new Asset(new Texture("sherman-top.png"));

        dungeonTiles = new Asset(new Texture("dungeon-tileset.png"),16,16);
    }

    public static void dispose(){
        bullets.dispose();
        powerUps.dispose();
        meleeZombie.dispose();
        shootingZombie.dispose();
        pantherTankBase.dispose();
        pantherTankTop.dispose();
        shermanTankBase.dispose();
        shermanTankTop.dispose();
    }

    public static Asset getBullets() {
        return bullets;
    }
    public static Asset getPowerUps() {
        return powerUps;
    }
    public static Asset getMeleeZombie() {
        return meleeZombie;
    }
    public static Asset getMeleeZombieHit(){
        return meleeZombieHit;
    }
    public static Asset getShootingZombie() {
        return shootingZombie;
    }
    public static Asset getPantherTankBase() {
        return pantherTankBase;
    }
    public static Asset getPantherTankTop() {
        return pantherTankTop;
    }
    public static Asset getShermanTankBase() {
        return shermanTankBase;
    }
    public static Asset getShermanTankTop() {
        return shermanTankTop;
    }
    public static Asset getDungeonTiles(){return dungeonTiles;}
}
