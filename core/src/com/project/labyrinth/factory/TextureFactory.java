package com.project.labyrinth.factory;

import com.badlogic.gdx.graphics.Texture;

public class TextureFactory {

    public Texture map_texture;
    public  Texture player_texture;
    public Texture wall_texture;
    public Texture wall_texture_rotated;
    public Texture monster_texture1;
    public Texture monster_texture2;
    public  Texture obstacle_texture;


    public static TextureFactory instance = new TextureFactory() ;

    public TextureFactory() {

        // creating the map
        map_texture= new Texture("grass.png");

        //creating the player object
        player_texture= new Texture("player_standing.png");

        // creating the texture of walls
        wall_texture = new Texture("wall_texture.png");
        wall_texture_rotated = new Texture("wall_texture_rotated.png");


        //creating the monsters
        monster_texture1 = new Texture("monster1.png");
        monster_texture2 = new Texture("monster2.png");

        //obstacle
        obstacle_texture = new Texture("obstacle.png");


    }

    public static TextureFactory getInstance() {
        return instance;
    }


    public Texture getMap_texture() {
        return map_texture;
    }

    public Texture getPlayer_texture() {
        return player_texture;
    }

    public Texture getWall_texture() {
        return wall_texture;
    }

    public Texture getWall_texture_rotated() {
        return wall_texture_rotated;
    }

    public Texture getMonster_texture1() {
        return monster_texture1;
    }

    public Texture getMonster_texture2() {
        return monster_texture2;
    }

    public Texture getObstacle_texture() {
        return obstacle_texture;
    }
}