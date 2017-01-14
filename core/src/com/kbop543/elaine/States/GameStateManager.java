package com.kbop543.elaine.States;
import java.util.Stack;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by kyrastephen on 9/27/16.
 */

public class GameStateManager {

    private Stack<State> states;

    public GameStateManager() { //new instance of state
        states = new Stack<State>();
    }
    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop().dispose();

    }
    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt){ //delta time between renders
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){ //render the sprite
        states.peek().render(sb);
    }
}
