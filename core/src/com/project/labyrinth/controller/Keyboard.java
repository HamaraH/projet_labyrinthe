package com.project.labyrinth.controller;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.project.labyrinth.model.Labyrinth;
import com.project.labyrinth.model.Player;

public class Keyboard implements InputProcessor {

    private Vector2 acc;
    private int coeff; //speed
    private Boolean debug ;
    private Labyrinth labyrinth;


    /**
     * create the controller mouse and keyboard
     * @param labyrinth , the model
     */
    public Keyboard(Labyrinth labyrinth){
        acc = new Vector2(0f, 0f);

        coeff = 1000;
        debug = false;
        this.labyrinth = labyrinth;
    }


    /**
     * detects a key press on the keyboard
     * E, S, D, F, to move the player
     * C, C to display the bodies that are debugged
     * @param keycode ,key code press
     * @return always true
     */
    @Override
    public boolean keyDown(int keycode) {

        switch(keycode){
            case Input.Keys.Z:
            //Z in MacOs is W
            case Input.Keys.W:
                acc.y += coeff;
                labyrinth.getPlayer().setSens(Player.direction.UP);
                break;
            case Input.Keys.S:
                acc.y -= coeff;
                labyrinth.getPlayer().setSens(Player.direction.DOWN);

                break;
            case Input.Keys.Q:
            //Q in MacOS is A
            case Input.Keys.A:
                acc.x -= coeff;
                labyrinth.getPlayer().setSens(Player.direction.LEFT);
                break;

            case Input.Keys.D:
                acc.x +=coeff;
                labyrinth.getPlayer().setSens(Player.direction.RIGHT);

                break;

            case Input.Keys.C:
                debug = !debug;
                break;

            case Input.Keys.SPACE:
                labyrinth.attack();
                break;
            default:
        }
        return true;
    }

    /**
     *detects a key to release on the keyboard
     * @param keycode, key code to release
     * @return ,always true
     */
    @Override
    public boolean keyUp(int keycode) {
        switch(keycode) {
            case Input.Keys.Z:
            case Input.Keys.S:
            case Input.Keys.W:
                acc.y = 0;
                break;

            case Input.Keys.Q:
            case Input.Keys.D:
            case Input.Keys.A:
                acc.x = 0;
                break;
            default:
        }
        return true ;
    }


    /**
     * detects a click on the mouse
     * if left click -> attack
     * ---- javadoc of the input processor ---------
     * @param screenX, The x coordinate, origin is in the upper left corner
     * @param screenY, The y coordinate, origin is in the upper left corner
     * @param pointer, the pointer for the event
     * @param button, button click
     * @return ,true if left click, false else
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button == Input.Buttons.LEFT){
                labyrinth.attack();
                return true;
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
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


    public Vector2 getAcc() {
        return acc;
    }
}