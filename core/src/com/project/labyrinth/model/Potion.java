package com.project.labyrinth.model;

import com.badlogic.gdx.physics.box2d.*;
import com.project.labyrinth.factory.Constante;

public class Potion {


    private int size;
    private Body body;
    private World world;
    private Boolean active;


    Potion(World world, int x, int y, int size) {

        this.size = size;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);
        this.world = world;
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


    public void effectPotionOfLife(Player player){
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

                if (contact.getFixtureB().getBody() == body && contact.getFixtureA().getBody() == player.getBody()) {
                    player.setHp(player.getHp() + 10);
                    setActive(false);}

            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }

        });

        //delete body potion
    if(!active) {
        world.destroyBody(body);
    }

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