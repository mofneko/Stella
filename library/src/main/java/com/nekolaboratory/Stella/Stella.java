package com.nekolaboratory.Stella;

/**
 * @author Yusuke Arakawa
 */

public class Stella {

    Core stellaCore;

    public void initialize(int loopDeley, StellaCallback callback) {
        stellaCore = new Core(loopDeley, callback);
    }

    public void start() {
        stellaCore.start();
    }

    public void stop() {
        stellaCore.stop();
    }
}
