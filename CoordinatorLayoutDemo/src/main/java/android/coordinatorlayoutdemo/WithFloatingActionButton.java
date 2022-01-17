package android.coordinatorlayoutdemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

/**
 * @author hiphonezhu@gmail.com
 * @version [CoordinatorLayoutDemo, 17/2/7 17:57]
 */

public class WithFloatingActionButton extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_with_floatingactionbutton_simple);
        setContentView(R.layout.activity_with_floatingactionbutton);
    }

    @SuppressLint("WrongConstant")
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Snackbar.make(findViewById(R.id.contentView), "Snackbar", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }
}
