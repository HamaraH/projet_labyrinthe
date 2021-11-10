package com.project.labyrinth.model;

import com.badlogic.gdx.physics.box2d.*;

public class Wall {
    private int posX;
    private int posY;
    private Body body ;
    private int size ;


    /**
     * create a wall
     * @param world ,the world of game (manage all physics entities)
     * @param posX ,position in x
     * @param posY ,position in y
     * @param size ,size wall
     */
    public Wall(World world, int posX, int posY, int size){

        this.posX = posX * size ;
        this.posY = posY * size ;
        this.size = size;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();

        shape.set(new float[]{this.posX, this.posY,
                this.posX+size,  this.posY,
                this.posX + size ,  this.posY +size,
                this.posX, this.posY+size});

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        body.setUserData("Wall");

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
