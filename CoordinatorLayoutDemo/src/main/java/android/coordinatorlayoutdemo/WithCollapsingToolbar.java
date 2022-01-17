package android.coordinatorlayoutdemo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;

/**
 * @author hiphonezhu@gmail.com
 * @version [CoordinatorLayoutDemo, 17/2/7 17:58]
 */

public class WithCollapsingToolbar extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_with_collapsingtoolbar);
//        setContentView(R.layout.activity_with_collapsingtoolbar_2);
        setContentView(R.layout.activity_with_collapsingtoolbar_3);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("标题");
        toolbar.setLogo(getResources().getDrawable(R.mipmap.ic_launcher));
        setSupportActionBar(toolbar);


        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsingToolbarLayout);
//        collapsingToolbarLayout.setTitleEnabled(false);
//        collapsingToolbarLayout.setTitle("这是标题");

        TextView tv = (TextView)findViewById(R.id.tv);
        for (int i = 0; i < 50; i++) {
            tv.append((i + 1) + "\n");
        }
    }
}
