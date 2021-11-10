package com.project.labyrinth.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Player extends Entity {


    private Body body ;
    private int size ;


    /**
     * create a player
     * @param world ,the world of game (manage all physics entities)
     * @param x ,position in x
     * @param y ,position in y
     * @param size ,size player
     */

    Player(World world, int x, int y, int size){


        this.attackPoints = 10;
        this.size = size;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.set(new float[]{0f, 0f, 0f, size, size, size, size, 0f});

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0 ;
        fixtureDef.restitution = 0.25f ;

        body.createFixture(fixtureDef);
        body.setLinearDamping(3f);
        body.setUserData("Player");

        shape.dispose();

    }


    /**
     * apply force to the monster to move it
     * @param vector ,of force
     */
    public void applyForce(Vector2 vector){

        body.applyLinearImpulse(vector,  body.getWorldCenter(),true);

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