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

        this.size = size;

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(posX, posY);
        body = world.createBody(bodyDef);
        shape = new PolygonShape();

        ((PolygonShape) shape).set(new float[]{this.posX, this.posY,
                this.posX+size,  this.posY,
                this.posX + size,  this.posY +size,
                this.posX, this.posY+size});

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

    public int getSize() {
        return size;
    }
}
