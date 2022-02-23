package com.tank.game;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundClip {
    Sound clip;
    float volume;

    public SoundClip(String path){
        this.clip = Gdx.audio.newSound(Gdx.files.internal(path));
        this.volume = .02f;
    }

    public void play(){
        this.clip.play(this.volume);
    }

    public void stop(){
        this.clip.stop();
    }

    public void dispose(){
        clip.dispose();
    }
}
