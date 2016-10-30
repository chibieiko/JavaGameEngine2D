package com.ebingine;

import com.ebingine.GameLoop;

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

    public abstract void update(GameLoop gameLoop, double deltaTime);

    public abstract void render(GameLoop gameLoop, Render renderer);

}
