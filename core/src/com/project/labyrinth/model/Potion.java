package com.project.labyrinth.model;

import com.badlogic.gdx.physics.box2d.*;
import com.project.labyrinth.factory.Constante;

public class Potion {


    private int size;
    private Body body;
    private Boolean active;


    Potion(World world, int x, int y, int size, int ratio) {

        this.size = size;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        x *= ratio;
        y *= ratio;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);
        this.active = true;

        PolygonShape shape = new PolygonShape();
        shape.set(new float[]{0f, 0f, 0f, size, size, size, size, 0f});

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = Constante.CATEGORY_POTION;
        fixtureDef.filter.maskBits = Constante.CATEGORY_PLAYER ;
        body.createFixture(fixtureDef);
        body.setUserData("Potion");

        shape.dispose();

    }


    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public float getBodyPositionX() {
        return body.getPosition().x;
    }

    public float getBodyPositionY() {
        return body.getPosition().y;
    }


    public int getSize() {
        return size;
    }

    public Body getBody() {
        return body;
    }
}