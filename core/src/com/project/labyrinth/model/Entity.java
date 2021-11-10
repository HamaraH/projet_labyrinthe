package com.project.labyrinth.model;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * factors common elements between the monster and the player
 */
abstract class Entity {

    int posX, posY, hp, attackPoints, size;
    Body body ;

    /**
     * apply force to the monster to move it
     * @param vector ,of force
     */
    void applyForce(Vector2 vector){

        body.applyLinearImpulse(vector,  body.getWorldCenter(),true);
    }

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

    float getPositionX(){

        return body.getPosition().x;
    }

    float getPositionY(){
        return body.getPosition().y;
    }

    int getSize() {
        return size;
    }

    Body getBody() {
        return body;
    }
}