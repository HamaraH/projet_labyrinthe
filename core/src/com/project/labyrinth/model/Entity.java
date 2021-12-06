package com.project.labyrinth.model;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * factors common elements between the monster and the player
 */
public abstract class Entity {

    protected int posX, posY, hp, attackPoints, size;
    protected Body body ;

    /**
     * apply force to the monster to move it
     * @param vector ,of force
     */
   public void applyForce(Vector2 vector){
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

    public void setSize(int size) {
        this.size = size;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

   public float getPositionX(){
        return body.getPosition().x;
    }

   public float getPositionY(){
        return body.getPosition().y;
    }

    public int getSize() {
        return size;
    }

    public Body getBody() {
        return body;
    }
}