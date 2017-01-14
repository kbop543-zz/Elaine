package com.kbop543.elaine.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.kbop543.elaine.Elaine;
import com.kbop543.elaine.sprites.ElaineChar;
import com.kbop543.elaine.sprites.Tube;

/**
 * Created by kyrastephen on 9/27/16.
 */

public class PlayState extends State {

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -30;

    private ElaineChar elaine;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private int level = 1;


    private Array<Tube> tubes;


    protected PlayState(GameStateManager gsm, int level) {
        //TO DO: add levels by having playstate take an integer
        super(gsm);
        elaine = new ElaineChar(10,200);
        level = 1;
        cam.setToOrtho(false, Elaine.WIDTH/2,Elaine.HEIGHT/2);
        bg = new Texture("bgg.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth /2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth/2)+
                + ground.getWidth(),GROUND_Y_OFFSET);


        tubes = new Array<Tube>();

        for (int i = 1;i < TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }


    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            elaine.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        elaine.update(dt);
        cam.position.x = elaine.getPosition().x + 80;

        for (int i = 0; i < tubes.size; i++){

            Tube tube = tubes.get(i);

            System.out.println("tube" + tube);
            System.out.println("number" + i);
            System.out.println("level" + level);




            if (cam.position.x - (cam.viewportWidth /2)
            > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH
                        + TUBE_SPACING * TUBE_COUNT)));
            }
            if (tube.collides(elaine.getBounds())) //restart if elaine collides
               // gsm.set(new GameOver(gsm));
                gsm.set(new PlayState(gsm,0));

            if(i == 2 * level) { // if a specific amount of tubes are passed, update it
                if (tube.passedThrough(elaine.getBounds()))
                    gsm.set(new PlayState(gsm,level));
                    level++;
                }
            }

        if(elaine.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET)
            gsm.set(new PlayState(gsm,level));

        if(elaine.getPosition().y > bg.getHeight())
            gsm.set(new PlayState(gsm,level));

        cam.update();


    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg,cam.position.x - (cam.viewportWidth /2),0);
        sb.draw(elaine.getTexture(),elaine.getPosition().x,elaine.getPosition().y);
        for(Tube tube: tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.draw(ground,groundPos1.x,groundPos1.y);
        sb.draw(ground,groundPos2.x,groundPos2.y);
        sb.end();

    }

    public void dispose(){
        bg.dispose();
        elaine.dispose();
        for( Tube tube: tubes)
            tube.dispose();
        System.out.println("Play State Disposed");

    }
    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2,0);
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2,0);
    }
}
