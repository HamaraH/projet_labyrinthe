package com.project.labyrinth.view;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.project.labyrinth.Game;
import com.project.labyrinth.factory.TextureFactory;
import com.project.labyrinth.model.Labyrinth;
import com.project.labyrinth.model.Player;

public class PlayerAnimate extends Actor {

    private static final int FRAME_COLS = 4, FRAME_ROWS = 4;
    Animation<TextureRegion> walkAnimationDown, walkAnimationUp, walkAnimationRight, walkAnimationLeft;

    private final static int STARTING_X = 50;
    private final static int STARTING_Y = 50;
    TextureRegion reg;
    float stateTime;
    Player player;


    public PlayerAnimate(Player player){
        createIdleAnimation();
        this.setPosition(STARTING_X, STARTING_Y);
        this.player = player;

    }

    private void createIdleAnimation() {
       Texture walkSheet = TextureFactory.getInstance().getPlayerTexture() ;

        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] walkFramesUp = new TextureRegion[FRAME_ROWS];
        TextureRegion[] walkFramesDown = new TextureRegion[FRAME_ROWS];
        TextureRegion[] walkFramesLeft = new TextureRegion[FRAME_ROWS];
        TextureRegion[] walkFramesRight = new TextureRegion[FRAME_ROWS];

        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {

            walkFramesDown[index] = tmp[0][i];
            walkFramesRight[index] = tmp[2][i];
            walkFramesLeft[index] = tmp[1][i];
            walkFramesUp[index] = tmp[3][i];

            index= index + 1;
        }

        walkAnimationDown = new Animation<TextureRegion>(0.025f, walkFramesDown);
        walkAnimationUp = new Animation<TextureRegion>(0.025f, walkFramesUp);
        walkAnimationRight = new Animation<TextureRegion>(0.025f, walkFramesRight);
        walkAnimationLeft = new Animation<TextureRegion>(0.025f, walkFramesLeft);
        stateTime = 0f;

        reg=walkAnimationDown.getKeyFrame(0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        stateTime += delta;


        if(player.getBody().getLinearVelocity().len() < 100){
            switch (player.getSens()) {
                case UP:
                    reg = walkAnimationUp.getKeyFrame(0, false);
                    break;
                case DOWN:
                    reg = walkAnimationDown.getKeyFrame(0, false);
                    break;
                case LEFT:
                    reg = walkAnimationLeft.getKeyFrame(0, false);
                    break;
                case RIGHT:
                    reg = walkAnimationRight.getKeyFrame(0, false);
                    break;

            }
        }else {


            switch (player.getSens()) {
                case UP:
                    reg = walkAnimationUp.getKeyFrame(stateTime, true);
                    break;
                case DOWN:
                    reg = walkAnimationDown.getKeyFrame(stateTime, true);
                    break;
                case LEFT:
                    reg = walkAnimationLeft.getKeyFrame(stateTime, true);
                    break;
                case RIGHT:
                    reg = walkAnimationRight.getKeyFrame(stateTime, true);
                    break;
            }

        }
    }


    public void draw(Batch batch){


        batch.draw(reg,  player.getPositionX(), player.getPositionY(), player.getSize(), player.getSize());
    }




}