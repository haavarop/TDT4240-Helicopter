package com.opium.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.opium.game.MyGdxGame;
import com.opium.game.sprites.Heliflopter;

/**
 *  Created by opium on 18.01.18.
 */

public class PlayState extends State {

    private Heliflopter heliflopter;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        heliflopter = new Heliflopter(MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        heliflopter.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(heliflopter.getSprite(),
                heliflopter.getPosition().x,
                heliflopter.getPosition().y);

        sb.end();
    }

    @Override
    public void dispose() {

    }
}
