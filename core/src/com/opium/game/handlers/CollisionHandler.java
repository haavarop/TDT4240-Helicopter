package com.opium.game.handlers;

import com.badlogic.gdx.math.Rectangle;
import com.opium.game.sprites.Heliflopter;
import java.util.ArrayList;

/**
 *  Created by opium on 23.01.18.
 */

public class CollisionHandler {

    private ArrayList<Heliflopter> heliflopters;

    public CollisionHandler(ArrayList<Heliflopter> heliflopters){

        this.heliflopters = heliflopters;
    }

    public void detectCollisions(){

        for(Heliflopter heli : heliflopters){
            Heliflopter current = heli;

            Rectangle currentRect = new Rectangle(
                    (int)current.getPosition().x,
                    (int)current.getPosition().y,
                    (int)current.getSprite().getWidth(),
                    (int)current.getSprite().getHeight()
            );

            for(Heliflopter other : heliflopters){

                Rectangle otherRect = new Rectangle(
                    (int)other.getPosition().x,
                    (int)other.getPosition().y,
                    (int)other.getSprite().getWidth(),
                    (int)other.getSprite().getHeight()
                );

                if(current.equals(other)){
                    continue;
                }

                if(currentRect.overlaps(otherRect)){

                    // Holy if-statement Batman!
                    if(otherRect.getY() > currentRect.getY() &&  otherRect.getY() < currentRect.getY() + currentRect.getHeight()  ||
                       otherRect.getY() + otherRect.getHeight() > currentRect.getY() && otherRect.getY() + otherRect.getHeight() <
                       currentRect.getY() + currentRect.getHeight() &&
                       otherRect.getX() > currentRect.getX() + currentRect.getWidth() &&
                       otherRect.getX() + otherRect.getWidth() < currentRect.getX()){

                        current.bounceX();
                        other.bounceX();

                        current.setFlip(! current.isFlip());
                        other.setFlip(! other.isFlip());

                    }else{
                        current.bounceY();
                        other.bounceY();
                    }
                }

            }
        }
    }
}
