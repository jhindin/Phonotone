package com.jhindin.phonotone.views;

public interface ToneListener {
    void keyPressed(int musicKey, float pressure);
    void keyReleased(int musicKey);
    void pressureChanged(int musicKey, float pressure);
}
