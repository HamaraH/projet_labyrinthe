package com.project.labyrinth.factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;


public class SoundFactory {

    private Music backgroundMusic ;
    private static SoundFactory instance = new SoundFactory();

    /**
     * brings together the music of the game
     */
    private SoundFactory(){

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background_music.mp3"));

    }


    public static SoundFactory getInstance() {
        return instance;
    }

    public Music getBackgroundMusic() {
        return backgroundMusic;
    }
}