package com.example.seachal.launchedapp;

import android.content.Intent;
import android.os.Bundle;

public class TrainTransferActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_transfer);
        startOtherAppActivity_4();


    }


    /**
     * @Author zhangxc
     * @Description //TODO  没有执行onNewIntent
     * @Date 15:52 2023/6/15
     *
     * @return void
     **/
    public  void startOtherAppActivity_1(){
        Intent intent = new Intent(TrainTransferActivity.this, Main5Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("name","seachal.tech");
        startActivity(intent);
    }


    /**
     * @Author zhangxc
     * @Description //TODO  没有执行onNewIntent
     * @Date 15:52 2023/6/15
     *
     * @return void
     **/
    public  void startOtherAppActivity_2(){
        Intent intent = new Intent(TrainTransferActivity.this, Main5Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("name","seachal.tech");
        startActivity(intent);
    }
   /**
    * @Author zhangxc
    * @Description //TODO  成功执行了额 onNewIntent
    * @Date 15:51 2023/6/15
    *   但是创建了新的 Activity 实例。  singleTop的效果？
    * @return void
    **/
    public  void startOtherAppActivity_3(){
        Intent intent = new Intent(TrainTransferActivity.this, Main5Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("name","seachal.tech");
        startActivity(intent);
    }


    /**
     * @Author zhangxc     Android intent flag 达到 singleTask 效果
     *
     * FLAG_ACTIVITY_NEW_TASK 和 FLAG_ACTIVITY_CLEAR_TOP
     * @Description //TODO
     * @Date 16:00 2023/6/15
     *
     * @return void
     **/
    public  void startOtherAppActivity_4(){
        Intent intent = new Intent(TrainTransferActivity.this, Main5Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("name","seachal.tech");
        startActivity(intent);
    }
}