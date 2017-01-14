package com.kbop543.elaine.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kbop543.elaine.Elaine;

/**
 * Created by kyrastephen on 10/3/16.
 */

public class GameOver extends State {
    private Texture background;
    private Texture playBtn;
    private Texture title;

    protected GameOver(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        playBtn = new Texture("playBtn.png");
        title = new Texture("Game-Over.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm,0));

        }

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0, Elaine.WIDTH,Elaine.HEIGHT);
        sb.draw(title, (Elaine.WIDTH/2) - (title.getWidth()/2) , Elaine.HEIGHT/2 + 100);
        sb.draw(playBtn, (Elaine.WIDTH/2) - (playBtn.getWidth()/2), Elaine.HEIGHT /2);
        sb.end();
    }


    public void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("Game Over State Disposed");

    }
}

