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

    private static final int COLS = 4, ROWS = 4;
    Animation<TextureRegion> animationDown, animationUp, animationRight, animationLeft;


    private final static float SPEED = 0.100f;
    TextureRegion textureRegion;
    float stateTime;
    Player player;


    public PlayerAnimate(Player player){


        this.player = player;


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


        if(player.getBody().getLinearVelocity().len() == 0){
            switch (player.getSens()) {
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


            switch (player.getSens()) {
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


    public void draw(Batch batch){
        
        batch.draw(textureRegion,  player.getPositionX(), player.getPositionY(), player.getSize(), player.getSize());
    }




}