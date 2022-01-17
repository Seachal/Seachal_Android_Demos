package android.coordinatorlayoutdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * @author hiphonezhu@gmail.com
 * @version [CoordinatorLayout-BehaviorDemo, 17/2/6 13:50]
 */

public class DependencyView extends androidx.appcompat.widget.AppCompatButton {
    private int lastX;
    private int lastY;

    public DependencyView(Context context) {
        super(context);
    }

    public DependencyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DependencyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) getLayoutParams();
                int left = layoutParams.leftMargin + x - lastX;
                int top = layoutParams.topMargin + y - lastY;

                layoutParams.leftMargin = left;
                layoutParams.topMargin = top;
                setLayoutParams(layoutParams);
                requestLayout();
                break;
            }
        }
        lastX = x;
        lastY = y;
        return true;
    }
}
