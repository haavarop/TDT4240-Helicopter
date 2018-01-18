package com.opium.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.opium.game.MyGdxGame;

import java.util.Random;


/**
 *  Created by opium on 18.01.18.
 */

public class Heliflopter {

    private Vector3 position;
    private Vector3 velocity;
    private Texture heliflopter;
    private Sprite flopterSprite;

    public Heliflopter(int x, int y) {

        position = new Vector3(x, y, 0);
        velocity = new Vector3(randInt(-7, -1), randInt(-5, 5), 0);
        heliflopter = new Texture("heli1.png");
        flopterSprite = new Sprite(heliflopter);

    }

    public void update(float dt) {
        checkBounce();
        position.add(velocity.x, velocity.y, 0);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Sprite getSprite() {
        return flopterSprite;
    }

    private void checkBounce() {
        if (position.x > MyGdxGame.WIDTH - heliflopter.getWidth() || position.x < 0) {
            bounceX();
        } else if (position.y > MyGdxGame.HEIGHT - heliflopter.getHeight() || position.y < 0) {
            bounceY();
        }
    }

    private void bounceX() {
        Vector3 currentVel = velocity;
        flopterSprite.flip(true, false);
        setVelocity(new Vector3(-currentVel.x, currentVel.y, 0));
    }

    private void bounceY() {
        Vector3 currentVel = velocity;
        setVelocity(new Vector3(currentVel.x, -currentVel.y, 0));
    }

    public void setVelocity(Vector3 velocity) {
        this.velocity = velocity;
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
