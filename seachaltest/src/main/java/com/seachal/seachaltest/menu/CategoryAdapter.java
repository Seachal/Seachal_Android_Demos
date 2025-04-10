package com.seachal.seachaltest.menu;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.seachal.seachaltest.R;
import com.seachal.seachaltest.bean.StartActivityBean;

import java.util.List;
import java.util.Random;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_CATEGORY = 0;
    private static final int TYPE_ACTIVITY = 1;
    
    // Color schemes for category items
    private static final int[] CATEGORY_COLORS = {
        Color.parseColor("#4CAF50"),  // Green
        Color.parseColor("#2196F3"),  // Blue
        Color.parseColor("#9C27B0"),  // Purple
        Color.parseColor("#FF9800"),  // Orange
        Color.parseColor("#F44336"),  // Red
        Color.parseColor("#009688"),  // Teal
        Color.parseColor("#3F51B5"),  // Indigo
        Color.parseColor("#795548"),  // Brown
    };

    private AppCompatActivity context;
    private List<Category> categories;
    private Random random = new Random();

    public CategoryAdapter(AppCompatActivity context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }
    
    /**
     * Updates the data set and refreshes the RecyclerView
     * @param newCategories The new list of categories to display
     */
    public void updateData(List<Category> newCategories) {
        this.categories = newCategories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        int itemCount = 0;
        for (Category category : categories) {
            // Category header
            if (position == itemCount) {
                return TYPE_CATEGORY;
            }
            itemCount++;

            // Activities in this category
            if (category.isExpanded()) {
                int activityCount = category.getActivities().size();
                if (position < itemCount + activityCount) {
                    return TYPE_ACTIVITY;
                }
                itemCount += activityCount;
            }
        }
        return TYPE_CATEGORY; // Default fallback
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_CATEGORY) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
            return new CategoryViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
            return new ActivityViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemCount = 0;
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            
            // Category header
            if (position == itemCount) {
                CategoryViewHolder categoryHolder = (CategoryViewHolder) holder;
                categoryHolder.tvCategory.setText(category.getTitle());
                
                // Set category title background color using a color from the array
                int colorIndex = i % CATEGORY_COLORS.length;
                categoryHolder.itemView.setBackgroundColor(CATEGORY_COLORS[colorIndex]);
                
                // Set arrow direction based on expanded state
                categoryHolder.ivArrow.setRotation(category.isExpanded() ? 180 : 0);
                
                // Set click listener to expand/collapse
                final int categoryIndex = i;
                final int currentItemCount = itemCount;
                categoryHolder.itemView.setOnClickListener(v -> {
                    boolean isExpanded = category.isExpanded();
                    category.setExpanded(!isExpanded);
                    
                    // Notify adapter about the change
                    if (isExpanded) {
                        // Collapse - remove items
                        notifyItemRangeRemoved(currentItemCount + 1, category.getActivities().size());
                    } else {
                        // Expand - add items
                        notifyItemRangeInserted(currentItemCount + 1, category.getActivities().size());
                    }
                    
                    // Animate arrow rotation
                    categoryHolder.ivArrow.animate().rotation(isExpanded ? 0 : 180).setDuration(300).start();
                });
                return;
            }
            itemCount++;

            // Activities in this category
            if (category.isExpanded()) {
                int activityCount = category.getActivities().size();
                if (position < itemCount + activityCount) {
                    ActivityViewHolder activityHolder = (ActivityViewHolder) holder;
                    final StartActivityBean activity = category.getActivities().get(position - itemCount);
                    
                    activityHolder.tvActivity.setText(activity.getTitle());
                    
                    // Show subtitle if available, otherwise hide it
                    if (activity.getSubtitle() != null && !activity.getSubtitle().isEmpty()) {
                        activityHolder.tvSubtitle.setVisibility(View.VISIBLE);
                        activityHolder.tvSubtitle.setText(activity.getSubtitle());
                    } else {
                        activityHolder.tvSubtitle.setVisibility(View.GONE);
                    }
                    
                    // Set a slightly randomized card background color for visual variety
                    int color = Color.rgb(
                        255 - random.nextInt(20),
                        255 - random.nextInt(20),
                        255 - random.nextInt(20)
                    );
                    activityHolder.cardRoot.setCardBackgroundColor(color);
                    
                    activityHolder.itemView.setOnClickListener(v -> {
                        Intent intent = new Intent(context, activity.getActivityClass());
                        ActivityUtils.startActivity(intent);
                    });
                    
                    return;
                }
                itemCount += activityCount;
            }
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        for (Category category : categories) {
            // Add 1 for the category header
            count++;
            
            // Add count of activities if category is expanded
            if (category.isExpanded()) {
                count += category.getActivities().size();
            }
        }
        return count;
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;
        ImageView ivArrow;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tv_category);
            ivArrow = itemView.findViewById(R.id.iv_arrow);
            
            // Make text color white for better contrast with colored backgrounds
            tvCategory.setTextColor(Color.WHITE);
        }
    }

    static class ActivityViewHolder extends RecyclerView.ViewHolder {
        TextView tvActivity;
        TextView tvSubtitle;
        LinearLayout llRoot;
        CardView cardRoot;

        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            tvActivity = itemView.findViewById(R.id.tv_items);
            tvSubtitle = itemView.findViewById(R.id.tv_subtitle);
            llRoot = itemView.findViewById(R.id.ll_root);
            cardRoot = itemView.findViewById(R.id.card_root);
        }
    }
} 