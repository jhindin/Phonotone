package com.jhindin.phonotone.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class KeyView extends View {
    Paint mFillPaint, mStrokePaint;
    int mWidth, mHeight;

    PhonotoneKey keys[] =  { new PhonotoneKey(), new PhonotoneKey(),
            new PhonotoneKey(), new PhonotoneKey(),
            new PhonotoneKey(), new PhonotoneKey()};


    public KeyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mFillPaint = new Paint();
        mFillPaint.setColor(0xffffffff);

        mStrokePaint = new Paint();
        mStrokePaint.setColor(0xffffffff);
        mStrokePaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        final int pointerCount = event.getPointerCount();


        int upIndex = -1;
        if (event.getActionMasked() == MotionEvent.ACTION_UP) {
            upIndex = event.getActionIndex();
        }

        for (PhonotoneKey key : keys) {
            boolean pressed = false;

            for (int p = 0; p < pointerCount; p++) {
                pressed |= key.rect.contains((int) event.getX(p), (int) event.getY(p)) &&
                        p != upIndex;
            }

            if (key.pressed != pressed) {
                key.pressed = pressed;
                invalidate(key.rect);
            }
        }

        return true;
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
