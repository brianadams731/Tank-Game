package com.tank.game;

final public class AudioManager {
    private final static SoundClip mgFire;
    private final static SoundClip lazerFire;

    private AudioManager(){}

    static {
        mgFire = new SoundClip("mgfire.mp3");
        lazerFire = new SoundClip("lazerfire.mp3");
    }

    public static SoundClip getMgFire() {
        return mgFire;
    }

    public static SoundClip getLazerFire(){
        return lazerFire;
    }


    public static void dispose(){
        mgFire.dispose();
        lazerFire.dispose();
    }
}
