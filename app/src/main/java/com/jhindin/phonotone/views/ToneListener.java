package com.jhindin.phonotone.views;

import android.view.View;

public interface ToneListener {
    void keyPressed(View source, int musicKey, float pressure);
    void keyReleased(View source, int musicKey);
    void pressureChanged(View source, int musicKey, float pressure);
}
