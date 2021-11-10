package com.project.labyrinth.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Monster extends Entity {

    private int size, relativePosX, relativePosY, direction, ratio;
    private Body body ;


    /**
     * create a monster
     * @param world ,the world of game (manage all physics entities)
     * @param x ,position in x
     * @param y ,position in y
     * @param hp ,life point
     * @param size ,size world
     * @param ratio ,screen ratio
     */
    public Monster(World world, int x, int y, int hp, int size, int ratio){

        this.posX = x;
        this.posY = y;
        this.hp = hp;
        this.ratio = ratio;
        this.direction = -1;
        this.relativePosX = posX * ratio;
        this.relativePosY = posY * ratio;

        this.size = size;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(relativePosX , relativePosY );
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.set(new float[]{0f, 0f, 0f, size, size, size, size, 0f});

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        fixtureDef.density = 0 ;
        fixtureDef.restitution = 0f ;

        body.createFixture(fixtureDef);
        body.setLinearDamping(0f);

        body.setUserData("Monster");

        shape.dispose();

    }

    /**
     * position in the map
     * @param depX ,position in X
     * @param depY ,position in Y
     */
    public void move(int depX, int depY){
        this.posX += depX;
        this.posY += depY;
    }


    /**
     * apply force to the monster to move it
     * @param vector ,of force
     */
    public void applyForce(Vector2 vector){
        body.applyLinearImpulse(vector,  body.getWorldCenter(),true);
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

    public int getRatio() {
        return ratio;
    }



    public int getRelativePosX() {
        return relativePosX;
    }

    public int getRelativePosY() {
        return relativePosY;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}