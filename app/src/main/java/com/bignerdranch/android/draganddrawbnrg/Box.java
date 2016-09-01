package com.bignerdranch.android.draganddrawbnrg;

import android.graphics.PointF;

/**
 * Created by smaikap on 1/9/16.
 */
public class Box {
    private PointF mOrigin;
    private PointF mCurrent;

    public Box(final PointF origin) {
        this.mOrigin = origin;
        this.mCurrent = origin;
    }

    public PointF getOrigin() {
        return this.mOrigin;
    }

    public PointF getCurrent() {
        return this.mCurrent;
    }

    public void setCurrent(final PointF current) {
        this.mCurrent = current;
    }
}
