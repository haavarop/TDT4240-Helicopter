package com.opium.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.opium.game.MyGdxGame;
import com.opium.game.handlers.CollisionHandler;
import com.opium.game.sprites.Heliflopter;

import java.util.ArrayList;

/**
 *  Created by opium on 18.01.18.
 */

public class PlayState extends State {


    private ArrayList<Heliflopter> heliflopters = new ArrayList<Heliflopter>();
    private float stateTime;

    private CollisionHandler collisionHandler;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        stateTime = 0f;

        for (int i = 0; i < 4; i++) {
            heliflopters.add(new Heliflopter(MyGdxGame.WIDTH / (i+1), MyGdxGame.HEIGHT / (i+1)));
        }

        collisionHandler = new CollisionHandler(heliflopters);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        for(Heliflopter heli : heliflopters){
            heli.update(dt);
        }
        collisionHandler.detectCollisions();
    }

    @Override
    public void render(SpriteBatch sb) {
        stateTime += Gdx.graphics.getDeltaTime();

        for(Heliflopter heli : heliflopters){
            if(heli.checkBounce()){
                flipp(heli);
            }
        }

        sb.begin();
        for(Heliflopter heli : heliflopters){
            TextureRegion currentFrame =
                    heli.getFlyAnimation().getKeyFrame(stateTime, true);

            sb.draw(currentFrame,
                    heli.isFlip() ? heli.getPosition().x + heli.getSprite().getWidth() : heli.getPosition().x,
                    heli.getPosition().y,
                    heli.isFlip() ? - heli.getSprite().getWidth() : heli.getSprite().getWidth(),
                    heli.getSprite().getHeight());
        }
        sb.end();
    }

    public void flipp(Heliflopter heliflopter){
        boolean newState = !heliflopter.isFlip();
        heliflopter.setFlip(newState);
    }
    @Override
    public void dispose() {
    }
}
