package com.project.labyrinth.model;

import com.badlogic.gdx.physics.box2d.*;

public class Monster extends Entity {

    private int size, relativePosX, relativePosY;
    private Body body ;
    private Shape shape;
    private BodyDef bodyDef;

    public int getRelativePosX() {
        return relativePosX;
    }

    public int getRelativePosY() {
        return relativePosY;
    }

    public Monster(World world, int x, int y, int hp, int size, int ratio){

        this.posX = x;
        this.posY = y;
        this.hp = hp;
        relativePosX = posX * ratio;
        relativePosY = posY * ratio;

        this.size = size;
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(relativePosX + 5, relativePosY + 5);
        body = world.createBody(bodyDef);

        shape = new PolygonShape();
        ((PolygonShape) shape).set(new float[]{0f, 0f, 0f, size, size, size, size, 0f});

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        fixtureDef.density = 0 ;
        fixtureDef.restitution = 0f ;

        body.createFixture(fixtureDef);
        body.setLinearDamping(0f);

        body.setUserData("Monster");

        shape.dispose();

    }

    public void move(int depX, int depY){
        this.posX += depX;
        this.posY += depY;
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