package com.seachal.seachaltest.bean;

/**
 * *
 * *
 * Project_Name:Seachal_Android_Demos
 *
 * @author zhangxc
 * @Description: TODO
 * @date 2023/5/17 11:28
 * @Version：1.0
 */
public class StartActivityBean {

    private String mTitle;
    private String mSubtitle;
    private Class mActivityClass;


    public StartActivityBean(String title, Class activityClass) {
        mTitle = title;
        mActivityClass = activityClass;
        mSubtitle = "";
    }

    public StartActivityBean(String title, String subtitle, Class activityClass) {
        mTitle = title;
        mSubtitle = subtitle;
        mActivityClass = activityClass;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
    
    public String getSubtitle() {
        return mSubtitle;
    }

    public void setSubtitle(String subtitle) {
        mSubtitle = subtitle;
    }

    public Class getActivityClass() {
        return mActivityClass;
    }

    public void setActivityClass(Class activityClass) {
        mActivityClass = activityClass;
    }

}
