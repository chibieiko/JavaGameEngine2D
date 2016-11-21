package com.ebingine;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1030
 * @since 1.7
 */
public abstract class Game {

    public abstract void create(GameContainer gc);

    // To update game state.
    public abstract void update(GameContainer gc, double deltaTime);

    // To render screen, to draw.
    public abstract void render(GameContainer gc, Render
            renderer);

    public abstract void clear(GameContainer gc);

}
