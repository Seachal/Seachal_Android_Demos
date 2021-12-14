package com.seachal.seachaltest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seachal.seachaltest.Activity.AndroiodScreenPropertyActivity;
import com.seachal.seachaltest.Activity.BackgroundActivity;
import com.seachal.seachaltest.Activity.CardVeiwScrollActivity;
import com.seachal.seachaltest.Activity.CardViewActivity;
import com.seachal.seachaltest.Activity.CountDownTimerActivity;
import com.seachal.seachaltest.Activity.CustomTitleActivity;
import com.seachal.seachaltest.Activity.CustomViewPreviewActivity;
import com.seachal.seachaltest.Activity.DebugActivity;
import com.seachal.seachaltest.Activity.EditTextMinAndMaxLengthActivity;
import com.seachal.seachaltest.Activity.SecondActivity;
import com.seachal.seachaltest.Activity.SkipToTaobaoActivity;
import com.seachal.seachaltest.Activity.SkipToTaobaoActivity2;
import com.seachal.seachaltest.Activity.URITestActivity;
import com.seachal.seachaltest.Activity.ViewMeasureActivity;
import com.seachal.seachaltest.BitmapDip.BitmapDipActivity;
import com.seachal.seachaltest.RecyclerViewTest.RecyclerViewTestActivity;
import com.seachal.seachaltest.ShareGeneratePicture.ShareGeneratePictureActivity;
import com.seachal.seachaltest.TextView.LinkTextViewActivity;
import com.seachal.seachaltest.bean.StartActivityBean;
import com.seachal.seachaltest.customview.CustomTextViewTestActivity;
import com.seachal.seachaltest.permission.PermissionActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainMenuActivity extends AppCompatActivity  {

    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private final int RESULT_CODE_1 = 100;
    private long exitTime = 0;


    private List<StartActivityBean> activityList = new ArrayList<StartActivityBean>();


    {
        activityList.add(new StartActivityBean("Bitmmap 放在不同的资源文件夹下，加载时所占用的内存", BitmapDipActivity.class));
        activityList.add(new StartActivityBean("TextView setText 的时候，onMessure,会被调用吗。测量文字是否2行", CustomTextViewTestActivity.class));
        activityList.add(new StartActivityBean("android 分享生成图片", ShareGeneratePictureActivity.class));
        activityList.add(new StartActivityBean("textView 带超链接", LinkTextViewActivity.class));
        activityList.add(new StartActivityBean("RecyclerView 多种 gride 布局", RecyclerViewTestActivity.class));
        activityList.add(new StartActivityBean("CountDownTimer 倒计时", CountDownTimerActivity.class));
        activityList.add(new StartActivityBean("uri", URITestActivity.class));
        activityList.add(new StartActivityBean("跳转到淘宝", SkipToTaobaoActivity.class));
        activityList.add(new StartActivityBean("跳转到淘宝2", SkipToTaobaoActivity.class));
        activityList.add(new StartActivityBean("android 跳转栈测试", SkipToTaobaoActivity2.class));
        activityList.add(new StartActivityBean("View与父 View 的测量", ViewMeasureActivity.class));
        activityList.add(new StartActivityBean("EditText  最小字符数，最大字符数,addView", EditTextMinAndMaxLengthActivity.class));

        activityList.add(new StartActivityBean("SecondActivity", SecondActivity.class));
        activityList.add(new StartActivityBean("DebugActivity", DebugActivity.class));

        activityList.add(new StartActivityBean("自定义 进度条", CustomViewPreviewActivity.class));
        activityList.add(new StartActivityBean("权限 & 设置, 在设置拒绝位置权限，app是否会被杀死？是。", PermissionActivity.class));
        activityList.add(new StartActivityBean("IamgeView gif  selector师傅支持 gif", ImageViewActivity.class));
        activityList.add(new StartActivityBean("background 并不会对上层的 view裁剪， 甚至会被上层的 view 遮挡住圆角。 ", BackgroundActivity.class));
        activityList.add(new StartActivityBean("cardview 阴影颜色",
                CardViewActivity.class));
        activityList.add(new StartActivityBean("cardview 原生  越靠近屏幕底部颜色越深",
                CardVeiwScrollActivity.class));

        activityList.add(new StartActivityBean("屏幕分辨率",
                AndroiodScreenPropertyActivity.class));
        activityList.add(new StartActivityBean("自定义 title",
                CustomTitleActivity.class));


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recycler_view.setAdapter(new MyAdapter(MainMenuActivity.this, activityList));
        recycler_view.setLayoutManager(new LinearLayoutManager(MainMenuActivity.this));

//        // 判断权限
//        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
//        if (EasyPermissions.hasPermissions(this, perms)) {
//            recycler_view.setAdapter(new MyAdapter(MainActivity.this, activityList));
//            recycler_view.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//        } else {
//            // 如果用户拒绝权限，第二次打开才会显示提示文字
//            EasyPermissions.requestPermissions(this, "维持App正常运行需要存储权限", RESULT_CODE_1, perms);
//        }
    }


    // 双击退出
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
//    }
//
//    /**
//     * 同意授权
//     *
//     * @param requestCode
//     * @param perms
//     */
//    @Override
//    public void onPermissionsGranted(int requestCode, List<String> perms) {
//
//
//    }
//
//    /**
//     * 拒绝授权
//     *
//     * @param requestCode
//     * @param perms
//     */
//    @Override
//    public void onPermissionsDenied(int requestCode, List<String> perms) {
//        Toast.makeText(this, "权限", Toast.LENGTH_SHORT).show();
//        finish();
//    }




    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private Context context;
        private List<StartActivityBean> arrayList;

        public MyAdapter(Context context, List<StartActivityBean> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent,
                    false));
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.mTextView.setText(arrayList.get(position).getTitle());
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainMenuActivity.this, arrayList.get(position).getActivityClass());
                    startActivity(intent);
                }
            });
            holder.llroot.setBackgroundResource(R.drawable.shape_solid_white_full_corner_20dp);
        }

        @Override
        public int getItemCount() {
            return activityList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private LinearLayout llroot;
           private  TextView mTextView;


            public MyViewHolder(View itemView) {
                super(itemView);
                llroot = itemView.findViewById(R.id.ll_root);
                mTextView = itemView.findViewById(R.id.tv_items);

            }
        }
    }


}
