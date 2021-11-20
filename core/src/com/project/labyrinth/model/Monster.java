package com.project.labyrinth.model;

import com.badlogic.gdx.physics.box2d.*;

abstract class Monster extends Entity {

    private int direction;
    private int ratio;
    private  FixtureDef fixtureDef;

    /**
     * create a monster
     * @param world ,the world of game (manage all physics entities)
     * @param x ,position in x
     * @param y ,position in y
     * @param hp ,life point
     * @param size ,size world
     * @param ratio ,screen ratio
     */
    Monster(World world, int x, int y, int hp, int size, int ratio){
        System.out.println("monster");
        this.posX = x;
        this.posY = y;
        this.hp = hp;
        this.ratio = ratio;
        this.direction = -1;


        this.size = size;


    }

    /**
     * position in the map
     * @param depX ,position in X
     * @param depY ,position in Y
     */
    void move(int depX, int depY){
        this.posX += depX;
        this.posY += depY;
    }


    int getRatio() {
        return ratio;
    }


    int getDirection() {
        return direction;
    }

    void setDirection(int direction) {
        this.direction = direction;
    }
}