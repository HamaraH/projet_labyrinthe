package com.project.labyrinth.model.cellEffect;

import com.badlogic.gdx.physics.box2d.*;
import com.project.labyrinth.model.Player;

public class CellPath {

    private int destinationX, destinationY;
    private boolean activated = false;
    private Body body;

    public CellPath(World world, int x, int y, int destX, int destY, int size, int ratio){
        x *= ratio;
        y *= ratio;

        destinationX = destX * ratio;
        destinationY = destY * ratio;

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

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public void getEffect(Player player){
        System.out.println("Teleport to : " + destinationX + " ; " + destinationY);
        player.getBody().setLinearVelocity(0, 0);
        player.getBody().setTransform(destinationX, destinationY, player.getBody().getAngle());
        System.out.println("Wesh");
        player.getBody().setAwake(true);
    }

    public Body getBody(){
        return body;
    }

}
