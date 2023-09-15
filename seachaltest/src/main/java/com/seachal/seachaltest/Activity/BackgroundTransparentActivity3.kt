package com.seachal.seachaltest.Activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seachal.seachaltest.R

/**
 * TODO  背景主色调白色
 *
 */
class BackgroundTransparentActivity3 : AppCompatActivity() {
    var recyclerView:RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background_transparent3)
        val list: MutableList<StyleData> = ArrayList()

        for (i in 0..100 step 10) {
            val data = StyleData()
            data.item = "透明度$i%"
            data.value = i
            list.add(data)
        }
         recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val manager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.layoutManager = manager
        val adapter = StyleAdapter(this, manager, list)
        recyclerView?.adapter = adapter
    }

    internal class StyleAdapter(
        private val context: Context,
        private val manager: LinearLayoutManager,
        private val list: List<StyleData>
    ) :
        RecyclerView.Adapter<StyleAdapter.StyleViewHolder>() {


        override fun getItemViewType(position: Int): Int {
            return list[position].type
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StyleViewHolder {
            val holder: StyleViewHolder
            holder = ViewHolder1(
                LayoutInflater.from(context).inflate(
                    R.layout.item_transparent, parent,
                    false
                )
            )

            return holder
        }

        override fun onBindViewHolder(holder: StyleViewHolder, position: Int) {
            holder.textView!!.text = list[position].item
            when (list[position].value) {
                0 -> {
                    holder.textView!!.setBackgroundColor(Color.parseColor("#00FFFFFF")); // 10%
                }
                10 -> {
                    holder.textView!!.setBackgroundColor(Color.parseColor("#1AFFFFFF")); // 10%
                }
                20 -> {
                    holder.textView!!.setBackgroundColor(Color.parseColor("#33FFFFFF")); // 20%
                }
                30 -> {
                    holder.textView!!.setBackgroundColor(Color.parseColor("#4DFFFFFF")); // 30%
                }
                40 -> {
                    holder.textView!!.setBackgroundColor(Color.parseColor("#66FFFFFF")); // 40%
                }
                50 -> {
                    holder.textView!!.setBackgroundColor(Color.parseColor("#80FFFFFF")); // 50%
                }
                60 -> {
                    holder.textView!!.setBackgroundColor(Color.parseColor("#99FFFFFF")); // 60%
                }
                70 -> {
                    holder.textView!!.setBackgroundColor(Color.parseColor("#B3FFFFFF")); // 70%
                }
                80 -> {
                    holder.textView!!.setBackgroundColor(Color.parseColor("#CCFFFFFF")); // 80%
                }
                90 -> {
                    holder.textView!!.setBackgroundColor(Color.parseColor("#E6FFFFFF")); // 90%
                }
                100 -> {
                    holder.textView!!.setBackgroundColor(Color.parseColor("#FFFFFFFF")); // 100%
                }
            }

            holder.textView!!.setOnClickListener{
                
            }

        }

        override fun getItemCount(): Int {
            return list.size
        }

        internal open inner class StyleViewHolder(view: View?) : RecyclerView.ViewHolder(
            view!!
        ) {
            var textView: TextView? = null
        }

        internal inner class ViewHolder1(view: View) : StyleViewHolder(view) {
            init {
                textView = view.findViewById(R.id.tv_card_view_menu_container3)
            }
        }



    }

    internal class StyleData {
        var type = 0
        var item: String? = null
        var value = 0
    }


}