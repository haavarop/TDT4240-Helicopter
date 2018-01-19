package com.opium.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.opium.game.MyGdxGame;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;


/**
 *  Created by opium on 18.01.18.
 */

public class Heliflopter {

    private Vector3 position;
    private Vector3 velocity;
    private Texture heliflopter;
    private Sprite flopterSprite;

    private float mouseX;
    private float mouseY;

    private double prevX;
    private double prevY;

    public Heliflopter(int x, int y) {

        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        heliflopter = new Texture("heli1.png");
        flopterSprite = new Sprite(heliflopter);

        mouseX = position.x;
        mouseY = position.y;

        prevX = 0;
        prevY = 0;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Sprite getSprite() {
        return flopterSprite;
    }

    public void setVelocity(Vector3 velocity) {
        this.velocity = velocity;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public void update(float dt) {

        // Quick maths
        float diffX = mouseX - position.x;
        float diffY = mouseY - position.y;

        double angle = Math.atan2(diffY, diffX) * 180 / Math.PI;
        double newX = round(Math.cos(angle * Math.PI/180) * 2, 3);
        double newY = round(Math.sin(angle * Math.PI/180) * 2, 3);

        //Avoid helicopter "jitter" caused by sin and cos math
        if(newX + prevX != 0 && newY + prevY != 0){
            position.y += newY;
            position.x += newX;

            prevX = newX;
            prevY = newY;
        }
    }


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
