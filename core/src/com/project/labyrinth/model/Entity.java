package com.project.labyrinth.model;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * factors common elements between the monster and the player
 */
abstract class Entity {

    protected int posX, posY, hp, attackPoints, size;
    protected Body body ;

    /**
     * apply force to the monster to move it
     * @param vector ,of force
     */
    void applyForce(Vector2 vector){

        body.applyLinearImpulse(vector,  body.getWorldCenter(),true);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setBody(Body body) {
        this.body = body;
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