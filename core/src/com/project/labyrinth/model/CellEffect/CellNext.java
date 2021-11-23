package com.project.labyrinth.model.CellEffect;

import com.badlogic.gdx.physics.box2d.*;
import com.project.labyrinth.model.Player;

public class CellNext {

    private Body body;
    private int size;

   public CellNext(World world, int x, int y, int size) {


       this.size = size;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.set(new float[]{0f, 0f, 0f, size, size, size, size, 0f});
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef);
        shape.dispose();

    }



    public Body getBody() {
        return body;
    }

    public float getBodyPositionX(){

        return body.getPosition().x;
    }

    public float getBodyPositionY(){

        return body.getPosition().y;
    }

    public int getSize() {
        return size;
    }
}