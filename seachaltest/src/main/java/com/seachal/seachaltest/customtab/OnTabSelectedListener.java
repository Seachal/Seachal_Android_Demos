package com.seachal.seachaltest.customtab;

/**
 * Tab选中监听器接口
 * 从CustomTabLayout内部类中提取出来，避免类型定义重复问题
 */
public interface OnTabSelectedListener {
    /**
     * 当Tab被选中时回调
     * @param position 被选中Tab的位置
     */
    void onTabSelected(int position);
} 