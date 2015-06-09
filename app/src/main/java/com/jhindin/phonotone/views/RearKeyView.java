package com.jhindin.phonotone.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.concurrent.CopyOnWriteArrayList;

public class RearKeyView extends View {
    protected Paint mFillPaint, mStrokePaint;
    protected int mWidth, mHeight;

    int currentTone = -1;
    float currentPressure = -1f;

    protected CopyOnWriteArrayList<ToneListener> listeners = new CopyOnWriteArrayList<>();

    PhonotoneKey keys[] =  { new PhonotoneKey(), new PhonotoneKey(),
            new PhonotoneKey(), new PhonotoneKey(),
            new PhonotoneKey(), new PhonotoneKey()};


    public RearKeyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mFillPaint = new Paint();
        mFillPaint.setColor(0xffffffff);

        mStrokePaint = new Paint();
        mStrokePaint.setColor(0xffffffff);
        mStrokePaint.setStyle(Paint.Style.STROKE);

    }

    public void addToneListener(ToneListener l) {
        listeners.add(l);
    }

    public void removeToneListener(ToneListener l) {
        listeners.remove(l);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        final int pointerCount = event.getPointerCount();

        int newTone = 0;
        float newPressure = 0;


        int upIndex = -1;
        if (event.getActionMasked() == MotionEvent.ACTION_UP) {
            upIndex = event.getActionIndex();
        }

        for (int keyIndex = 0; keyIndex < keys.length; keyIndex++) {
            PhonotoneKey key = keys[keyIndex];
            boolean pressed = false;

            for (int p = 0; p < pointerCount; p++) {
                pressed |= key.rect.contains((int) event.getX(p), (int) event.getY(p)) &&
                        p != upIndex;
                if (pressed)
                    newPressure += event.getPressure(p);
            }

            if (key.pressed != pressed) {
                key.pressed = pressed;
                invalidate(key.rect);
                if (pressed) {
                    newTone |= 1 << keyIndex;
                }
            }
        }

        if (newTone != currentTone) {
            if (currentTone != -1)
                notifyReleased(currentTone);

            if (newTone != 0)
                notifyPressed(newTone, newPressure);

            currentTone = newTone;
            currentPressure = newPressure;
        } else if (Math.abs(newPressure - currentPressure) > 0.1) {
            currentPressure = newPressure;
            notifyPressureChanged(newPressure);
        }

        return true;
    }

    protected void notifyReleased(int tone) {
        for (ToneListener l : listeners) {
            l.keyReleased(this, tone);
        }
    }

    protected void notifyPressed(int tone, float pressure) {
        for (ToneListener l : listeners) {
            l.keyPressed(this, tone, pressure);
        }
    }

    protected void notifyPressureChanged(float pressure) {
        for (ToneListener l : listeners) {
            l.pressureChanged(this, currentTone, pressure);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        int keyHeight, keyVerticalPadding, keyWidth, keyGap;

        keyVerticalPadding = h / 10;
        keyHeight = 2 * keyVerticalPadding;
        keyGap = w / 5;
        keyWidth = 2 * keyGap;


        keys[0].rect.top = keyVerticalPadding;
        keys[0].rect.bottom = keys[0].rect.top + keyHeight - 1;
        keys[0].rect.left = 0;
        keys[0].rect.right = keyWidth - 1;

        keys[1].rect.top = keys[0].rect.bottom + keyVerticalPadding;
        keys[1].rect.bottom = keys[1].rect.top + keyHeight - 1;
        keys[1].rect.left = 0;
        keys[1].rect.right = keyWidth - 1;

        keys[2].rect.top = keys[1].rect.bottom + keyVerticalPadding;
        keys[2].rect.bottom =  keys[2].rect.top + keyHeight - 1;
        keys[2].rect.left = 0;
        keys[2].rect.right = keyWidth - 1;

        keys[3].rect.top = keyVerticalPadding;
        keys[3].rect.bottom = keys[3].rect.top + keyHeight - 1;
        keys[3].rect.left = w - 1 - (keyWidth - 1);
        keys[3].rect.right = w - 1;

        keys[4].rect.top = keys[3].rect.bottom + keyVerticalPadding;
        keys[4].rect.bottom =  keys[4].rect.top + keyHeight - 1;
        keys[4].rect.left = w - 1 - (keyWidth - 1);
        keys[4].rect.right = w - 1;

        keys[5].rect.top = keys[4].rect.bottom + keyVerticalPadding;
        keys[5].rect.bottom =  keys[5].rect.top + keyHeight - 1;
        keys[5].rect.left = w - 1 - (keyWidth - 1);
        keys[5].rect.right = w - 1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (PhonotoneKey k : keys) {
            canvas.drawRect(k.rect, k.pressed ? mFillPaint : mStrokePaint);
        }
    }

    protected class PhonotoneKey {
        public Rect rect = new Rect();
        boolean pressed = false;
    }
}
