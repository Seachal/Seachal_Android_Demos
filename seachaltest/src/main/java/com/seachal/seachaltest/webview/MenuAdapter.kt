package com.seachal.seachaltest.webview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.seachal.seachaltest.R
import com.seachal.seachaltest.bean.StartActivityBean

/**
 * WebView 示例菜单适配器
 * 
 * 专门用于展示 WebView 功能示例列表的 RecyclerView 适配器
 * 遵循阿里巴巴 Android 开发手册规范
 */
class MenuAdapter(
    private val context: AppCompatActivity,
    private val menuItems: List<StartActivityBean>
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    companion object {
        private const val TAG = "MenuAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_menu_webview, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menuItem = menuItems[position]
        
        // 设置标题
        holder.tvTitle.text = menuItem.title
        
        // 设置描述，如果有的话
        if (menuItem.subtitle.isNullOrEmpty()) {
            holder.tvDescription.visibility = View.GONE
        } else {
            holder.tvDescription.visibility = View.VISIBLE
            holder.tvDescription.text = menuItem.subtitle
        }
        
        // 设置点击事件
        holder.itemView.setOnClickListener {
            try {
                val intent = Intent(context, menuItem.activityClass)
                context.startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
                // 可以在这里添加错误提示
            }
        }
    }

    override fun getItemCount(): Int = menuItems.size

    /**
     * ViewHolder 类
     */
    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_description)
        val ivIcon: ImageView = itemView.findViewById(R.id.iv_icon)
    }
} 