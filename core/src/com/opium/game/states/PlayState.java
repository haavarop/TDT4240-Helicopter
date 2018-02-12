package com.opium.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.opium.game.MyGdxGame;
import com.opium.game.sprites.Heliflopter;
import com.opium.game.sprites.Position;

/**
 *  Created by opium on 18.01.18.
 */

public final class PlayState extends State {

    // Declare Singleton
    private static PlayState instance = null;

    private Heliflopter heliflopter;
    private Position position;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        heliflopter = new Heliflopter(MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);
        position = new Position(MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);
    }

    // Lazy initialization of Singleton
    public static PlayState getInstance(GameStateManager gsm) {
        if(instance == null) {
            instance = new PlayState(gsm);
        }
        return instance;
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            heliflopter.setMouseX(Gdx.input.getX());
            heliflopter.setMouseY(MyGdxGame.HEIGHT - Gdx.input.getY());
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        heliflopter.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(new Texture("background.jpg"), 0,0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        sb.draw(heliflopter.getSprite(),
                heliflopter.getPosition().x,
                heliflopter.getPosition().y);
        sb.end();

        //ShapeRenderer and SpriteBatch has to be processed separately
        position.drawShape();

        sb.begin();
        position.getText().setColor(1,1,1,1);
        position.getText().draw(
                sb,
                "("+Math.floor(heliflopter.getPosition().x) + "," + Math.floor(heliflopter.getPosition().y)+")",
                0,
                800
        );
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
