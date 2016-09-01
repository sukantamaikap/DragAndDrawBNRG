package com.bignerdranch.android.draganddrawbnrg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smaikap on 31/8/16.
 */
public class BoxDrawingView extends View {

    private final static String TAG = "BoxDrawingView";

    private Paint mBoxPaint;
    private Paint mBackgroundPaint;
    private Box mCurrentBox;
    private List<Box> mBoxen = new ArrayList<>();

    public BoxDrawingView(final Context context) {
        this(context, null);
    }

    public BoxDrawingView(final Context context, final AttributeSet attrs) {
        super(context, attrs);

        this.mBoxPaint = new Paint();
        this.mBoxPaint.setColor(0x22f80965);

        this.mBackgroundPaint = new Paint();
        this.mBackgroundPaint.setColor(0xf8964);
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        final PointF current = new PointF(event.getX(), event.getY());
        String action = null;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                action = "ACTION_DOWN";
                //reset drawling state
                this.mCurrentBox = new Box(current);
                this.mBoxen.add(this.mCurrentBox);
                break;

            case MotionEvent.ACTION_MOVE :
                action = "ACTION_MOVE";
                if (this.mCurrentBox != null) {
                    this.mCurrentBox.setCurrent(current);
                    this.invalidate();
                }
                break;

            case MotionEvent.ACTION_UP :
                action = "ACTION_UP";
                this.mCurrentBox = null;
                break;

            case MotionEvent.ACTION_CANCEL :
                action = "ACTION_CANCEL";
                this.mCurrentBox = null;
                break;
        }

        Log.i(TAG, action + " at x = " + current.x + ", y = " + current.y);
        return Boolean.TRUE;
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        Log.i(TAG, "Set canvas color to semi transparent red");
        canvas.drawPaint(this.mBackgroundPaint);

        for (final Box box : this.mBoxen) {
            final float left = Math.min(box.getOrigin().x, box.getCurrent().x);
            final float right = Math.max(box.getOrigin().x, box.getCurrent().x);
            final float top = Math.min(box.getOrigin().y, box.getCurrent().y);
            final float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);
            Log.i(TAG, "Draw rectangle : left : " +  left + " right : " + right + " top : " + top + " bottom : " + bottom);
            canvas.drawRect(left, top, right, bottom, this.mBoxPaint);
        }
    }
}
