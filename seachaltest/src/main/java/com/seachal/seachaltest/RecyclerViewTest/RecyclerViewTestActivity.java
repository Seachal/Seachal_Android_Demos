package com.seachal.seachaltest.RecyclerViewTest;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seachal.seachaltest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * *
 * * [RecyclerView多样式不同的布局_红色与青色-CSDN博客_recycleview 多样式](https://blog.csdn.net/u010302327/article/details/79084125)
 * Project_Name:SeachalDemos
 *
 * @author zhangxc
 * @date 2019-09-27 16:02
 * *
 */
public class RecyclerViewTestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        List<StyleData> list = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            StyleData data = new StyleData();
            data.item = "" + i;
            if (i > 3 && i < 7 || i == 11 || i == 13) {
                data.type = 2;//占两个span
            } else if (i == 15 || i == 18) {
                data.type = 3;//占三个span
            } else if (i == 19) {
                data.type = 4;//占四个span
            } else {
                data.type = 1;//占一个span
            }
            list.add(data);
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(manager);
        StyleAdapter adapter = new StyleAdapter(this, manager, list);
        recyclerView.setAdapter(adapter);
    }

    class StyleAdapter extends RecyclerView.Adapter<StyleAdapter.StyleViewHolder> {
        private Context context;
        private List<StyleData> list;
        private GridLayoutManager manager;


        public StyleAdapter(Context context, GridLayoutManager manager, List<StyleData> list) {
            this.context = context;
            this.manager = manager;
            this.list = list;
//            设置源以获取适配器中每个项目占用的跨度数
            manager.setSpanSizeLookup(new GridSpanSizeLookup());
        }

        @Override
        public int getItemViewType(int position) {
            return list.get(position).type;
        }

        @Override
        public StyleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            StyleViewHolder holder;
            if (viewType == 4) {
                holder = new ViewHolder4(LayoutInflater.from(context).inflate(R.layout.item_type_4, parent,
                        false));
            } else if (viewType == 3) {
                holder = new ViewHolder3(LayoutInflater.from(context).inflate(R.layout.item_type_3, parent,
                        false));
            } else if (viewType == 2) {
                holder = new ViewHolder2(LayoutInflater.from(context).inflate(R.layout.item_type_2, parent,
                        false));
            } else {
                holder = new ViewHolder1(LayoutInflater.from(context).inflate(R.layout.item_type_1, parent,
                        false));
            }
            return holder;
        }

        @Override
        public void onBindViewHolder(StyleViewHolder holder, int position) {
            holder.textView.setText(list.get(position).item);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class StyleViewHolder extends RecyclerView.ViewHolder {

            TextView textView;

            public StyleViewHolder(View view) {
                super(view);
            }
        }

        class ViewHolder1 extends StyleViewHolder {
            public ViewHolder1(View view) {
                super(view);
                textView = view.findViewById(R.id.text_view);
            }
        }

        class ViewHolder2 extends StyleViewHolder {
            public ViewHolder2(View view) {
                super(view);
                textView = view.findViewById(R.id.text_view);
            }
        }

        class ViewHolder3 extends StyleViewHolder {
            public ViewHolder3(View view) {
                super(view);
                textView = view.findViewById(R.id.text_view);
            }
        }

        class ViewHolder4 extends StyleViewHolder {
            public ViewHolder4(View view) {
                super(view);
                textView = view.findViewById(R.id.text_view);
            }
        }

        //GridSpanSizeLookup.getSpanSize 返回1表示占1个span，返回gridManager.getSpanCount()表示占用整行
        public class GridSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
            @Override
            public int getSpanSize(int position) {

                //return gridManager.getSpanCount();
                return list.get(position).type;
            }
        }
    }

    class StyleData {
        int type;
        String item;
    }

}
