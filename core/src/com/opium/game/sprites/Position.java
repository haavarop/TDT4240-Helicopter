package com.opium.game.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *  Created by opium on 19.01.18.
 */

public class Position {

    //Text output
    private BitmapFont text;
    private ShapeRenderer shape;

    public Position(int x, int y){
        text = new BitmapFont();
        shape = new ShapeRenderer();
    }

    public BitmapFont getText() {
        return text;
    }

    public void drawShape() {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.BLACK);
        shape.rect(0,785,90,15);
        shape.end();
    }
}
