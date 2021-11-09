package com.project.labyrinth.model;

import com.badlogic.gdx.physics.box2d.*;

public class Wall {
    private int posX;
    private int posY;
    private Body body ;
    private Shape shape;
    private BodyDef bodyDef;
    private int size ;

    public Wall(World world, int posX, int posY, int size){
        this.posX = posX * size;
        this.posY = posY * size;

        this.size = 30;

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(posX, posY);
        body = world.createBody(bodyDef);
        shape = new PolygonShape();

        ((PolygonShape) shape).set(new float[]{0f, 0f, 0f, size, size, size, size, 0f});

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0 ;
        fixtureDef.restitution = 0.25f ;

        body.createFixture(fixtureDef);
        body.setLinearDamping(3f);
        body.setUserData("Player");

        shape.dispose();
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
