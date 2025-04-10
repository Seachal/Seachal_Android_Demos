package com.seachal.seachaltest.menu;

import com.seachal.seachaltest.bean.StartActivityBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a category of activities in the main menu.
 */
public class Category {
    private String title;
    private List<StartActivityBean> activities;
    private boolean isExpanded;

    public Category(String title) {
        this.title = title;
        this.activities = new ArrayList<>();
        this.isExpanded = false;
    }

    public void addActivity(StartActivityBean activity) {
        activities.add(activity);
    }

    public String getTitle() {
        return title;
    }

    public List<StartActivityBean> getActivities() {
        return activities;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
} 