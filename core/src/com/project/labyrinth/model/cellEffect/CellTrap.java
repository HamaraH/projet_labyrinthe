package com.project.labyrinth.model.cellEffect;

import com.badlogic.gdx.physics.box2d.*;
import com.project.labyrinth.model.Player;

public class CellTrap  {


    private final static  int DAMAGE = 1;
    private int size;
    private Body body;

    public CellTrap(World world, int x, int y, int size, int ratio){

        x *= ratio;
        y *= ratio;

        this.size = size;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);
        this.body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.set(new float[]{0f, 0f, 0f, size, size, size, size, 0f});
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef);
        shape.dispose();

    }


    public void getEffect(Player player)
    {
        player.setHp(player.getHp() - DAMAGE);
    }

    public Body getBody() {
        return body;
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
}

