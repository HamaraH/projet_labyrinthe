package com.project.labyrinth.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.project.labyrinth.view.MenuScreen;

public class MenuKeyboard implements InputProcessor {

    private MenuScreen ms;


    /**
     * Controller for menu
     * @param ms MenuScreen
     */

    public MenuKeyboard(MenuScreen ms){
        this.ms = ms;
    }


    /**
     * What to do when a key is pressed
     * @param i : key pressed
     * @return true
     */
    @Override
    public boolean keyDown(int i) {
        switch (i){
            case Input.Keys.S:
            case Input.Keys.Z:
            case Input.Keys.DOWN:
            case Input.Keys.UP:
                ms.setNewGame(!ms.isNewGame());
                break;
            case Input.Keys.ENTER:
            case Input.Keys.SPACE:
                if (ms.isNewGame())
                    ms.setStart(true);
                else
                    ms.setQuit(true);
        }
        return true;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
