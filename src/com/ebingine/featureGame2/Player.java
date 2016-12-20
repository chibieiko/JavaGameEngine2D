package com.ebingine.featureGame2;

import com.ebingine.GameContainer;
import com.ebingine.resources.Animation;
import com.ebingine.gameObjects.Sprite;
import com.ebingine.tiled.ObjectLayer;
import com.ebingine.tiled.TiledMap;
import com.ebingine.tiled.TiledObject;
import com.ebingine.resources.Texture;

import java.awt.*;
import java.util.ArrayList;

/**
 * The player of the game.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class Player extends Sprite {

    /**
     * A tiled map.
     */
    private transient TiledMap tiled;

    /**
     * Determines how strong the pull of gravity is.
     */
    private float gravity;

    /**
     * Determines jump velocity.
     */
    private float velocity;

    /**
     * Indicates current velocity.
     */
    private float currentVelocity;

    /**
     * Determines falling down speed.
     */
    private float fallDown;

    /**
     * Indicates whether the player has jumped and is still in jumping state.
     */
    private boolean jumped;

    /**
     * The ground of the tiled map.
     */
    private transient TiledObject ground;

    /**
     * The left border of the tiled map.
     */
    private transient TiledObject leftBorder;

    /**
     * The left border of the tiled map.
     */
    private transient TiledObject rightBorder;

    /**
     * The platforms of the tiled map.
     */
    private transient ObjectLayer platforms;

    /**
     * Animation for walking.
     */
    private transient Animation walk;

    /**
     * The frame of the player to be drawn.
     */
    private transient Texture currentFrame;

    /**
     * Indicates which way to draw the player.
     */
    private boolean flip;

    /**
     * Determines player's speed when boost is on.
     */
    private float boostSpeed;

    /**
     * Indicates whether the boost is on(true) or not(false).
     */
    private boolean boost;

    /**
     * Holds player's bullets.
     */
    private ArrayList<Bullet> bullets = new ArrayList<>();

    /**
     * Constructor sets and initiates player's variable values.
     *
     * @param coordinateX coordinate x
     * @param coordinateY coordinate y
     * @param width width
     * @param height height
     */
    public Player(int coordinateX, int coordinateY, int width, int height) {
        super(coordinateX, coordinateY, width, height);
        setTexture(AssetManager.rosette);
        setRectangle(coordinateX, coordinateY, width, height);
        gravity = 0.1f;
        velocity = 5f;
        currentVelocity = velocity;
        fallDown = 0;
        jumped = false;
        flip = false;
        setSpeedX(75);
        boostSpeed = getSpeedX() * 2;
        boost = false;
        setSpeedY(5);
        currentFrame = getTexture();
        createWalkAnimation();
        addDrawable();
    }

    /**
     * Draws the player and bullets.
     *
     * @param g2d a graphics object for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        if (flip) {
            g2d.drawImage(currentFrame.getImage(), (int) getX() + getWidth(),
                    (int) getY(),
                    -getWidth(),
                    getHeight(), null);
        } else {
            g2d.drawImage(currentFrame.getImage(), (int) getX(), (int) getY(),
                    getWidth(),
                    getHeight(), null);
        }

        if (bullets.size() > 0) {
            for (Bullet bullet : bullets) {
                bullet.draw(g2d);
            }
        }
    }

    /**
     * Moves the player accordingly.
     *
     * @param delta game's delta time
     */
    @Override
    public void move(double delta) {
        if (!jumped) {
            if (collidesWith(ground.getRectangle()) || checkPlatforms()) {
                fallDown = 0;
                // Creates gravity.
            } else {
                setY(getY() + fallDown);
                fallDown += gravity;
            }
        }

        if (isAlive()) {
            if (GameContainer.input.keyPressed("W")) {
                boost = true;
            } else {
                boost = false;
            }

            boolean walking = false;

            if (GameContainer.input.keyPressed("A") &&
                    !collidesWith(leftBorder.getRectangle())) {
                if (boost) {
                    setX(getX() - (boostSpeed * (float) delta));
                } else {
                    setX(getX() - (getSpeedX() * (float) delta));
                }

                walking = true;
                flip = true;
            }

            if (GameContainer.input.keyPressed("D") &&
                    !collidesWith(rightBorder.getRectangle())) {
                if (boost) {
                    setX(getX() + (boostSpeed * (float) delta));
                } else {
                    setX(getX() + (getSpeedX() * (float) delta));
                }

                walking = true;
                flip = false;
            }

            if (GameContainer.input.keyReleased("SPACE")) {
                jumped = true;
            }

            if (walking) {
                updateAnimations(delta);
            } else {
                currentFrame = getTexture();
            }
        } else {
            currentFrame = AssetManager.rosetteDead;
        }

        if (bullets.size() > 0) {
            for (int i = bullets.size() - 1; i >= 0; i--) {
                if (!bullets.get(i).isAlive()) {
                    bullets.remove(bullets.get(i));
                }
            }
        }
    }

    /**
     * Creates player's walk animation.
     */
    public void createWalkAnimation() {
        Texture[] images = GameContainer.utils.splitImage(
                AssetManager.rosetteWalk.getImage(), 4, 1);
        walk = new Animation(10d / 60d, images);
        walk.start();
    }

    /**
     * Updates animation state.
     *
     * @param delta game's delta time
     */
    public void updateAnimations(double delta) {
        walk.update(delta);
        currentFrame.setImage(walk.getKeyFrame().getImage());
    }

    /**
     * Lifts player to the air when player jumps and brings her back down.
     */
    public void jump() {
        if (jumped) {
            if (currentVelocity <= 0 && collidesWith(ground.getRectangle())
                    || currentVelocity <= 0 && checkPlatforms()) {
                jumped = false;
                currentVelocity = velocity;
            } else {
                setY(getY() - currentVelocity);
                currentVelocity -= gravity;
            }
        }
    }

    /**
     * Initiates tiled map objects.
     *
     * @param tiled a tiled map
     */
    public void setTiled(TiledMap tiled) {
        this.tiled = tiled;
        ground = tiled.getObject("border-bottom");
        leftBorder = tiled.getObject("border-left");
        rightBorder = tiled.getObject("border-right");
        platforms = tiled.getObjectLayer("platform-obj");
    }

    /**
     * Checks if player collides with a platform.
     *
     * @return true if collision, otherwise false
     */
    public boolean checkPlatforms() {
        boolean platformCollision = false;

        for (int i = 0; i < platforms.getTiledObjects().size(); i++) {
            if (collidesWith(platforms.getTiledObjects().get(i)
                    .getRectangle())) {
                platformCollision = true;
            }
        }

        return platformCollision;
    }

    /**
     * Creates a new bullet when player shoots.
     */
    public void shoot() {
        bullets.add(new Bullet((int) (getX()), (int) (getY()),
                tiled.getTileWidth(),
                tiled.getTileHeight(),
                flip));
    }

    /**
     * Updates bullet's state.
     *
     * @param delta game's delta time
     * @param monster a monster in the game
     */
    public void updateBullet(double delta, Monster monster) {
        for (Bullet bullet : bullets) {
            if (!checkBulletCollision(bullet, monster) &&
                    !checkBulletCollision(bullet) && bullet.isAlive()) {
                bullet.move(delta);
            } else if (checkBulletCollision(bullet, monster)){
                bullet.setCollision(true);
                monster.setAlive(false);
                bullet.updateAnimations(delta);
            } else {
                bullet.setCollision(true);
                bullet.updateAnimations(delta);
            }
        }
    }

    /**
     * Checks if a bullet collides with right or left border.
     *
     * @param bullet player's bullet
     * @return true if collides, otherwise false
     */
    public boolean checkBulletCollision(Bullet bullet) {
        boolean collision = false;

        if (bullet.collidesWith(leftBorder.getRectangle()) ||
                bullet.collidesWith(rightBorder.getRectangle())) {
            collision = true;
            bullet.playExplodeSound();
        }

        return collision;
    }

    /**
     * Checks if bullet collides with a monster.
     *
     * @param bullet player's bullet
     * @param monster game's monster
     * @return true if collision, otherwise false
     */
    public boolean checkBulletCollision(Bullet bullet, Monster monster) {
        boolean collision = false;

        if (bullet.collidesWith(monster.getRectangle())) {
            collision = true;
            bullet.playExplodeSound();
        }

        return collision;
    }
}
