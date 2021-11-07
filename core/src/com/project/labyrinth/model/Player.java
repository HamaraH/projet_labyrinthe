package com.project.labyrinth.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Player extends Entity {


    private Body body ;
    private Shape shape;
    private BodyDef bodyDef;
    private int size ;

   public Player(World world, int x, int y){


       this.attackPoints = 10;
        size = 30;
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
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
}