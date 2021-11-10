package com.project.labyrinth.model;


/**
 * factors common elements between the monster and the player
 */
abstract class Entity {

    int posX, posY, hp, attackPoints;

    int getPosX() {
        return posX;
    }

    void setPosX(int posX) {
        this.posX = posX;
    }

    int getPosY() {
        return posY;
    }

    void setPosY(int posY) {
        this.posY = posY;
    }

}