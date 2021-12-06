package com.project.labyrinth.factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;


public class SoundFactory {

    private Music backgroundMusic ;
    private Sound potion ;
    private Sound hurt ;
    private Sound attack ;
    private static SoundFactory instance = new SoundFactory();

    /**
     * brings together the music of the game
     */
    private SoundFactory(){

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background_music.mp3"));
        potion =Gdx.audio.newSound(Gdx.files.internal("potion.mp3"));
        hurt =Gdx.audio.newSound(Gdx.files.internal("hurt.mp3"));
        attack =Gdx.audio.newSound(Gdx.files.internal("attack.mp3"));

    }


    /**
     * free memory
     */
    public void dispose(){
        backgroundMusic.dispose();
        potion.dispose();
        hurt.dispose();
        attack.dispose();
    }

    public static SoundFactory getInstance() {
        return instance;
    }

    public Music getBackgroundMusic() {
        return backgroundMusic;
    }

    public Sound getPotion() {
        return potion;
    }

    public Sound getHurt() {
        return hurt;
    }

    public Sound getAttack() {
        return attack;
    }


}