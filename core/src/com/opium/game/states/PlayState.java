package com.opium.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.opium.game.MyGdxGame;
import com.opium.game.sprites.Heliflopter;

/**
 *  Created by opium on 18.01.18.
 */

public class PlayState extends State {

    private Heliflopter heliflopter;
    private float stateTime;
    private TextureRegion frame;
    private boolean flip;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        stateTime = 0f;
        heliflopter = new Heliflopter(MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);
        flip = false;
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
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame =
                heliflopter.getFlyAnimation().getKeyFrame(stateTime, true);

        if(heliflopter.checkBounce()){
            System.out.println("Flip!");
            flipp();
        }

        sb.begin();
        sb.draw(currentFrame,
                flip ? heliflopter.getPosition().x + heliflopter.getSprite().getWidth() : heliflopter.getPosition().x,
                heliflopter.getPosition().y,
                flip ? - heliflopter.getSprite().getWidth() : heliflopter.getSprite().getWidth(),
                heliflopter.getSprite().getHeight());

        sb.end();
    }

    public void flipp(){
        this.flip = !flip;
    }
    @Override
    public void dispose() {

    }
}
