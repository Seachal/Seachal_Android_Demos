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


    private List activityList = new ArrayList<StartActivityBean>();


    {
        activityList.add(new StartActivityBean("Bitmmap ???????????????????????????????????????????????????????????????", BitmapDipActivity.class));
        activityList.add(new StartActivityBean("TextView setText ????????????onMessure,????????????????????????????????????2???", CustomTextViewTestActivity.class));
        activityList.add(new StartActivityBean("android ??????????????????", ShareGeneratePictureActivity.class));
        activityList.add(new StartActivityBean("textView ????????????", LinkTextViewActivity.class));
        activityList.add(new StartActivityBean("RecyclerView ?????? gride ??????", RecyclerViewTestActivity.class));
        activityList.add(new StartActivityBean("CountDownTimer ?????????", CountDownTimerActivity.class));
        activityList.add(new StartActivityBean("uri", URITestActivity.class));
        activityList.add(new StartActivityBean("???????????????", SkipToTaobaoActivity.class));
        activityList.add(new StartActivityBean("???????????????2", SkipToTaobaoActivity.class));
        activityList.add(new StartActivityBean("android ???????????????", SkipToTaobaoActivity2.class));
        activityList.add(new StartActivityBean("View?????? View ?????????", ViewMeasureActivity.class));
        activityList.add(new StartActivityBean("EditText  ?????????????????????????????????,addView", EditTextMinAndMaxLengthActivity.class));

        activityList.add(new StartActivityBean("SecondActivity", SecondActivity.class));
        activityList.add(new StartActivityBean("DebugActivity", DebugActivity.class));

        activityList.add(new StartActivityBean("????????? ?????????", CustomViewPreviewActivity.class));
        activityList.add(new StartActivityBean("?????? & ??????, ??????????????????????????????app???????????????????????????", PermissionActivity.class));
        activityList.add(new StartActivityBean("IamgeView gif  selector???????????? gif", ImageViewActivity.class));
        activityList.add(new StartActivityBean("background ????????????????????? view????????? ????????????????????? view ?????????????????? ", BackgroundActivity.class));
        activityList.add(new StartActivityBean("cardview ????????????",
                CardViewActivity.class));
        activityList.add(new StartActivityBean("cardview ??????  ?????????????????????????????????",
                CardVeiwScrollActivity.class));

        activityList.add(new StartActivityBean("???????????????",
                AndroiodScreenPropertyActivity.class));
        activityList.add(new StartActivityBean("????????? title",
                CustomTitleActivity.class));


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nenu);
        ButterKnife.bind(this);
        recycler_view.setAdapter(new MyAdapter(MainMenuActivity.this, activityList));
        recycler_view.setLayoutManager(new LinearLayoutManager(MainMenuActivity.this));

//        // ????????????
//        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
//        if (EasyPermissions.hasPermissions(this, perms)) {
//            recycler_view.setAdapter(new MyAdapter(MainActivity.this, activityList));
//            recycler_view.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//        } else {
//            // ??????????????????????????????????????????????????????????????????
//            EasyPermissions.requestPermissions(this, "??????App??????????????????????????????", RESULT_CODE_1, perms);
//        }
    }


    // ????????????
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(getApplicationContext(), "????????????????????????", Toast.LENGTH_SHORT).show();
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
//     * ????????????
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
//     * ????????????
//     *
//     * @param requestCode
//     * @param perms
//     */
//    @Override
//    public void onPermissionsDenied(int requestCode, List<String> perms) {
//        Toast.makeText(this, "??????", Toast.LENGTH_SHORT).show();
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

   public class StartActivityBean {

        private String mTitle;

        private Class mActivityClass;


        public StartActivityBean(String title, Class activityClass) {
            mTitle = title;
            mActivityClass = activityClass;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(String title) {
            mTitle = title;
        }

        public Class getActivityClass() {
            return mActivityClass;
        }

        public void setActivityClass(Class activityClass) {
            mActivityClass = activityClass;
        }

    }



}
