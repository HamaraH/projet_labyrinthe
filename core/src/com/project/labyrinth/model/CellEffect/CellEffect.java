package com.project.labyrinth.model.CellEffect;

import com.badlogic.gdx.physics.box2d.*;
import com.project.labyrinth.model.Player;

public abstract class CellEffect {



    protected Body body;
    private World world;
    protected PolygonShape shape;



    CellEffect(World world, int x, int y, int size) {

        int s = size;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);
        this.world = world;
        PolygonShape shape = new PolygonShape();
        shape.set(new float[]{0f, 0f, 0f, s, s, s, s, 0f});
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef);
        shape.dispose();


    }


    public Body getBody() {
        return body;
    }


    public abstract void getEffect(Player player);
}