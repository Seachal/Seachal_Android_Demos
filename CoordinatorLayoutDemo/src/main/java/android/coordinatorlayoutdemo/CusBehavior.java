package android.coordinatorlayoutdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * @author hiphonezhu@gmail.com
 * @version [CoordinatorLayout-BehaviorDemo, 17/2/6 13:39]
 */

public class CusBehavior extends CoordinatorLayout.Behavior {
    private int width;

    public CusBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics display = context.getResources().getDisplayMetrics();
        width = display.widthPixels;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency.getId() == R.id.dependency;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setY(dependency.getY() + child.getHeight());
        return true;
    }
}
