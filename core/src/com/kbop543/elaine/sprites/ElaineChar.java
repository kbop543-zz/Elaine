package com.kbop543.elaine.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by kyrastephen on 9/27/16.
 */

public class ElaineChar {
    private static final int MOVEMENT = 100;
    private static final int GRAVITY = 15;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation elaineAnimation;
    private Texture texture;
    private Sound flap;


    public ElaineChar(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        texture = new Texture("elaineanimation.png");
        elaineAnimation = new Animation(new TextureRegion(texture),3,0.5f);
        bounds = new Rectangle(x,y,texture.getWidth() /3,texture.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return elaineAnimation.getFrame();
    }

    public void update(float dt){
        elaineAnimation.update(dt);
        if(position.y > 0)
            velocity.add(0, GRAVITY,0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt,velocity.y,0);
        if(position.y < 0)
            position.y = 0;

        velocity.scl(1/dt);
        bounds.setPosition(position.x,position.y);
    }

    public void jump(){

        velocity.y = -250;
        flap.play(0.3f);
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        texture.dispose();
        flap.dispose();
    }
}
