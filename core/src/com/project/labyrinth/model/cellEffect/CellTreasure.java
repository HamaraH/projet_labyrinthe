package com.project.labyrinth.model.cellEffect;

import com.badlogic.gdx.physics.box2d.*;

/**
 * cell for last level
 */
public class CellTreasure extends CellEnd {


    private boolean treasure;

    /**
     *
     * @param world, ,the world of game (manage all physics entities)
     * @param x , position in x
     * @param y , posisition in y
     * @param size , size entity
     */
    public CellTreasure(World world, int x, int y, int size){
        super(world, x, y, size);

        treasure = false;
    }


    public boolean isTreasure() {
        return treasure;
    }

    public void setTreasure(boolean treasure) {
        this.treasure = treasure;
    }

    @Override
    public boolean isTreasureCell() {
        return true;
    }
}

