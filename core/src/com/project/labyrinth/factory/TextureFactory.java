package com.project.labyrinth.factory;

import com.badlogic.gdx.graphics.Texture;

public class TextureFactory {

    public Texture mapTexture;
    public  Texture playerTexture;
    public Texture wallTexture;
    public Texture wallTextureRotated;
    public Texture monsterTexture1;
    public Texture monsterTexture2;
    public  Texture obstacleTexture;


    /**
     * brings together the textures of the game
     */
    public static TextureFactory instance = new TextureFactory() ;

    public TextureFactory() {

        // creating the map
        mapTexture= new Texture("grass.png");

        //creating the player object
        playerTexture= new Texture("player_standing.png");

        // creating the texture of walls
        wallTexture = new Texture("wall_texture.png");
        wallTextureRotated = new Texture("wall_texture_rotated.png");


        //creating the monsters
        monsterTexture1 = new Texture("monster1.png");
        monsterTexture2 = new Texture("monster2.png");

        //obstacle
        obstacleTexture = new Texture("obstacle.png");


    }

    public static TextureFactory getInstance() {
        return instance;
    }


    public Texture getMapTexture() {
        return mapTexture;
    }

    public Texture getPlayer_texture() {
        return playerTexture;
    }

    public Texture getWallTexture() {
        return wallTexture;
    }

    public Texture getWallTextureRotated() {
        return wallTextureRotated;
    }

    public Texture getMonsterTexture1() {
        return monsterTexture1;
    }

    public Texture getMonsterTexture2() {
        return monsterTexture2;
    }

    public Texture getObstacleTexture() {
        return obstacleTexture;
    }
}