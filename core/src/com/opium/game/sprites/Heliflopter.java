package com.opium.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.opium.game.MyGdxGame;

import java.util.Random;


/**
 *  Created by opium on 18.01.18.
 */

public class Heliflopter {

    public static final int FRAME_COLS = 1, FRAME_ROWS = 4;

    Animation<TextureRegion> flyAnimation;
    Texture flopterSheet;

    private Vector3 position;
    private Vector3 velocity;
    private Texture heliflopter;
    private Sprite flopterSprite;

    public Heliflopter(int x, int y) {

        position = new Vector3(x, y, 0);
        velocity = new Vector3(randInt(-7, -1), randInt(-5, 5), 0);
        flopterSheet = new Texture("heliflopterSpriteSheet.png");
        heliflopter = new Texture("heli1.png");

        TextureRegion[][] tmp = TextureRegion.split(
                flopterSheet,
                flopterSheet.getWidth() / FRAME_COLS,
                flopterSheet.getHeight() / FRAME_ROWS
        );

        TextureRegion[] flopterFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                flopterFrames[index++] = tmp[i][j];
            }
        }

        flyAnimation = new Animation<TextureRegion>(0.1f, flopterFrames);
        flopterSprite = new Sprite(heliflopter);
    }

    public Animation<TextureRegion> getFlyAnimation() {
        return flyAnimation;
    }

    public void update(float dt) {
        position.add(velocity.x, velocity.y, 0);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Sprite getSprite() {
        return flopterSprite;
    }

    public boolean checkBounce() {
        if (position.x > MyGdxGame.WIDTH - heliflopter.getWidth() || position.x < 0) {
            bounceX();
            return true;

        } else if (position.y > MyGdxGame.HEIGHT - heliflopter.getHeight() || position.y < 0) {
            bounceY();
        }
        return false;
    }

    public void bounceX() {
        Vector3 currentVel = velocity;
        setVelocity(new Vector3(-currentVel.x, currentVel.y, 0));
    }

    public void bounceY() {
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
