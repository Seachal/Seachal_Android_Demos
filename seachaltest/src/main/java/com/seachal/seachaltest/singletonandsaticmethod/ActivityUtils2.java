package com.seachal.seachaltest.singletonandsaticmethod;

import android.widget.BaseAdapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * *
 * *
 * Project_Name:Seachal_Android_Demos
 * [(81条消息) 工具类该用单例模式，还是用静态的方式_侠亦狐的博客-CSDN博客_工具类单例模式](https://blog.csdn.net/qq_35584878/article/details/91438648)
 * @author zhangxc
 * @date 2022/3/17 14:51
 *  惊天方法
 * *
 */
public class ActivityUtils2 {


    /**
     * 适配器绑定纵向列表控件
     * @param adapter 适配器
     * @param recyclerView 列表控件
     * [(81条消息) 工具类该用单例模式，还是用静态的方式_侠亦狐的博客-CSDN博客_工具类单例模式](https://blog.csdn.net/qq_35584878/article/details/91438648)
     */
    public  static void bindToRecyclerView(BaseAdapter adapter, RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
//        adapter(recyclerView);
    }


}
