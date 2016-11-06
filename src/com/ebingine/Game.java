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

    public abstract void update(Container container, double deltaTime);

    public abstract void render(Container container, Render renderer);

}
