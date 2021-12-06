package com.project.labyrinth.model.cellEffect;

import com.badlogic.gdx.physics.box2d.*;
import com.project.labyrinth.model.Player;

/**
 * secret passage: player teleport
 */


public class CellPath {

    private int destinationX, destinationY;
    private boolean activated = false;
    private int size;
    private Body body;

    /**
     *
     * @param world, the world of game (manage all physics entities)
     * @param x position in x
     * @param y position in y
     * @param destX position destination in x
     * @param destY position destination in y
     * @param size size body in the world
     * @param ratio screen ratio
     */

    public CellPath(World world, int x, int y, int destX, int destY, int size, int ratio){
        x *= ratio;
        y *= ratio;

        this.size = size;

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

    /**
     * cell effect teleport player
     * @param player player of  world
     */
    public void getEffect(Player player){

        player.getBody().setLinearVelocity(0, 0);
        player.getBody().setTransform(destinationX, destinationY, player.getBody().getAngle());

    }

    public Body getBody(){
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
