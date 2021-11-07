package com.project.labyrinth.model;

import com.badlogic.gdx.physics.box2d.*;

public class Monster extends Entity {

    private int id;
    private int size;
    private Body body ;
    private Shape shape;
    private BodyDef bodyDef;

    public Monster(World world, int x, int y, int hp, int size){

        this.posX = x;
        this.posY = y;
        this.hp = hp;

        this.size = size;
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);

        shape = new PolygonShape();
        ((PolygonShape) shape).set(new float[]{x, y, x, size, size, size, size, y});

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