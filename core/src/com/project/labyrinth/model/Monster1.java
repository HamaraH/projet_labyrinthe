package com.project.labyrinth.model;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.project.labyrinth.factory.Constante;

public class Monster1 extends Monster {
    public Monster1(World world, int x, int y, int hp, int size, int ratio) {
        super(world, x, y, hp, size, ratio);

        int relativePosX = posX * ratio;
        int relativePosY = posY * ratio;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(relativePosX, relativePosY);
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.set(new float[]{0f, 0f, 0f, size, size, size, size, 0f});

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        fixtureDef.density = 0 ;
        fixtureDef.restitution = 0f ;
        fixtureDef.filter.categoryBits = Constante.CATEGORY_MONSTER1;

        body.createFixture(fixtureDef);

        body.setLinearDamping(0f);

        body.setUserData("Monster");

        shape.dispose();

    }
}
