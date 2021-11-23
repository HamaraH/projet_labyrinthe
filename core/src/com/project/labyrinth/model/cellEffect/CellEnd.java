package com.project.labyrinth.model.cellEffect;

import com.badlogic.gdx.physics.box2d.*;

public class CellEnd {
    protected Body body;
    protected int size;

    public CellEnd(World world, int x, int y, int size) {
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

    public boolean isTreasureCell() {
        return false;
    }
}