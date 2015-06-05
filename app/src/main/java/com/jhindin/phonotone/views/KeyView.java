package com.jhindin.phonotone.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class KeyView extends View {
    Paint mFillPaint, mStrokePaint;
    int mWidth, mHeight;

    Rect keyRects[] =  { new Rect(), new Rect(), new Rect(),
            new Rect(), new Rect(), new Rect()};


    public KeyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mFillPaint = new Paint();
        mFillPaint.setColor(0xffffffff);

        mStrokePaint = new Paint();
        mStrokePaint.setColor(0xffffffff);
        mStrokePaint.setStyle(Paint.Style.STROKE);

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


        keyRects[0].top = keyVerticalPadding;
        keyRects[0].bottom = keyRects[0].top + keyHeight - 1;
        keyRects[0].left = 0;
        keyRects[0].right = keyWidth - 1;

        keyRects[1].top = keyRects[0].bottom + keyVerticalPadding;
        keyRects[1].bottom = keyRects[1].top + keyHeight - 1;
        keyRects[1].left = 0;
        keyRects[1].right = keyWidth - 1;

        keyRects[2].top = keyRects[1].bottom + keyVerticalPadding;
        keyRects[2].bottom =  keyRects[2].top + keyHeight - 1;
        keyRects[2].left = 0;
        keyRects[2].right = keyWidth - 1;

        keyRects[3].top = keyVerticalPadding;
        keyRects[3].bottom = keyRects[3].top + keyHeight - 1;
        keyRects[3].left = w - 1 - (keyWidth - 1);
        keyRects[3].right = w - 1;

        keyRects[4].top = keyRects[0].bottom + keyVerticalPadding;
        keyRects[4].bottom =  keyRects[4].top + keyHeight - 1;
        keyRects[4].left = w - 1 - (keyWidth - 1);
        keyRects[4].right = w - 1;

        keyRects[5].top = keyRects[1].bottom + keyVerticalPadding;
        keyRects[5].bottom =  keyRects[5].top + keyHeight - 1;
        keyRects[5].left = w - 1 - (keyWidth - 1);
        keyRects[5].right = w - 1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Rect r : keyRects) {
            canvas.drawRect(r, mStrokePaint);
        }

    }
}
