package com.project.labyrinth.factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;


public class SoundFactory {

    private Music background_music ;
    public static SoundFactory instance = new SoundFactory();

    public SoundFactory(){

        background_music = Gdx.audio.newMusic(Gdx.files.internal("background_music.mp3"));

    }


    public static SoundFactory getInstance() {
        return instance;
    }

    public Music getBackground_music() {
        return background_music;
    }
}