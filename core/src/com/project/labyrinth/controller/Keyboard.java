package com.project.labyrinth.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.project.labyrinth.model.Labyrinth;

public class Keyboard implements InputProcessor {



    Vector2 acc;
    int coeff; //vitesse
    Boolean debug ;

    Labyrinth labyrinth;

    public Keyboard(Labyrinth labyrinth){
        acc = new Vector2(0f, 0f);

        coeff = 1000;
        debug = false;
        this.labyrinth = labyrinth;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case Input.Keys.E:
                acc.y += coeff;
                break;
            case Input.Keys.D:
                acc.y -= coeff;

                break;
            case Input.Keys.S:
                acc.x -= coeff;

                break;
            case Input.Keys.F:
                acc.x +=coeff;

                break;

            case Input.Keys.C:
                debug = !debug;
                break;



            default:



        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode) {
            case Input.Keys.E:
            case Input.Keys.D:
                acc.y = 0;
                break;

            case Input.Keys.S:
            case Input.Keys.F:
                acc.x = 0;
                break;




            default:

        }

        return true ;
    }


    public Vector2 getAccG() {
        return acc;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button == Input.Buttons.LEFT){
                labyrinth.attack();
                return true;
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public Boolean isDebug() {
        return debug;
    }


}