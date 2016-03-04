package evos.tightbudget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Copyright © 2016 Media Applications Technologies. All rights reserved.
 */
public class TouchCapturingFrameLayout extends FrameLayout {
    public TouchCapturingFrameLayout(Context context) {
        super(context);
    }

    public TouchCapturingFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchCapturingFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TouchCapturingFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
