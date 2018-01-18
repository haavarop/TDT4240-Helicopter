package com.opium.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.opium.game.MyGdxGame;

/**
 *  Created by opium on 18.01.18.
 */

public class MenuState extends State{

    private Texture playBtn;
    private Texture background;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background.jpg");
        playBtn = new Texture("playBtn.PNG");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0,0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        sb.draw(
            playBtn,
            (MyGdxGame.WIDTH / 2) - (playBtn.getWidth() / 2),
            MyGdxGame.HEIGHT / 2
        );

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
