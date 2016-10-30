package com.ebingine.test;

import com.ebingine.GameLoop;

public class Main {

    public static void main(String[] args) {
        GameLoop gameLoop = new GameLoop();
        gameLoop.start();
        gameLoop.run();
    }
}
