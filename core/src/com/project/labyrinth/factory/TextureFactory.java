package com.project.labyrinth.factory;

import com.badlogic.gdx.graphics.Texture;

public class TextureFactory {

    private Texture mapTexture;
    private Texture playerTexture;
    private Texture wallTexture;
    private Texture monsterTexture1;
    private Texture potionOfLife;
    private Texture win;
    private Texture treasure;


    /**
     * brings together the textures of the game
     */
    private static TextureFactory instance = new TextureFactory() ;

    private TextureFactory() {

        // creating the map
        mapTexture= new Texture("grass.png");

        //creating the player object
        playerTexture= new Texture("player_standing.png");

        // creating the texture of walls
        wallTexture = new Texture("wall_texture.png");

        //creating the monsters
        monsterTexture1 = new Texture("monster1.png");

        //creating potions
        potionOfLife = new Texture("potionOfLife.png");

        win = new Texture("win.png");

        treasure = new Texture("tresor.png");

    }

    public static TextureFactory getInstance() {
        return instance;
    }


    public Texture getMapTexture() {
        return mapTexture;
    }

    public Texture getPlayerTexture() {
        return playerTexture;
    }

    public Texture getWallTexture() {
        return wallTexture;
    }

    public Texture getMonsterTexture1() {
        return monsterTexture1;
    }

    public Texture getPotionOfLife() {
        return potionOfLife;
    }

    public Texture getWin() {
        return win;
    }

    public Texture getTreasure() {
        return treasure;
    }
}