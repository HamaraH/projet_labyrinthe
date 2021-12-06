package com.project.labyrinth.view;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.project.labyrinth.factory.TextureFactory;
import com.project.labyrinth.model.Labyrinth;


/**
 * management of the animation of the sprite
 */
public class PlayerAnimate extends Actor {

    private static final int COLS = 4, ROWS = 4;
    private Animation<TextureRegion> animationDown, animationUp, animationRight, animationLeft;
    private final static float SPEED = 0.100f;
    private TextureRegion textureRegion;
    private float stateTime;
    private Labyrinth labyrinth;


    /**
     * Create animation
     * @param labyrinth ,labyrinth that contains the player
     */
    public PlayerAnimate(Labyrinth labyrinth){

        this.labyrinth =labyrinth ;
        Texture sheet = TextureFactory.getInstance().getPlayerTexture() ;

        TextureRegion[][] tmp = TextureRegion.split(sheet, sheet.getWidth() / COLS, sheet.getHeight() / ROWS);

        TextureRegion[] regionUp = new TextureRegion[ROWS];
        TextureRegion[] regionDown = new TextureRegion[ROWS];
        TextureRegion[] regionLeft = new TextureRegion[ROWS];
        TextureRegion[] regionRight = new TextureRegion[ROWS];

        int index = 0;
        for (int i = 0; i < ROWS; i++) {

            regionDown[index] = tmp[0][i];
            regionRight[index] = tmp[2][i];
            regionLeft[index] = tmp[1][i];
            regionUp[index] = tmp[3][i];

            index= index + 1;
        }

        animationDown = new Animation<TextureRegion>(SPEED, regionDown);
        animationUp = new Animation<TextureRegion>(SPEED, regionUp);
        animationRight = new Animation<TextureRegion>(SPEED,regionRight);
        animationLeft = new Animation<TextureRegion>(SPEED, regionLeft);
        stateTime = 0f;

        textureRegion = animationDown.getKeyFrame(0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        stateTime += delta;


        if(labyrinth.getPlayer().getBody().getLinearVelocity().len() == 0){
            switch (labyrinth.getPlayer().getSens()) {
                case UP:
                    textureRegion = animationUp.getKeyFrame(0, false);
                    break;
                case DOWN:
                    textureRegion = animationDown.getKeyFrame(0, false);
                    break;
                case LEFT:
                    textureRegion = animationLeft.getKeyFrame(0, false);
                    break;
                case RIGHT:
                    textureRegion = animationRight.getKeyFrame(0, false);
                    break;

            }
        }else {


            switch (labyrinth.getPlayer().getSens()) {
                case UP:
                    textureRegion = animationUp.getKeyFrame(stateTime, true);
                    break;
                case DOWN:
                    textureRegion = animationDown.getKeyFrame(stateTime, true);
                    break;
                case LEFT:
                    textureRegion = animationLeft.getKeyFrame(stateTime, true);
                    break;
                case RIGHT:
                    textureRegion = animationRight.getKeyFrame(stateTime, true);
                    break;
            }

        }
    }

    /**
     * draw player
     * @param batch batch
     */
    public void draw(Batch batch){

        batch.draw(textureRegion,  labyrinth.getPlayer().getPositionX(), labyrinth.getPlayer().getPositionY(), labyrinth.getPlayer().getSize(), labyrinth.getPlayer().getSize());
    }




}