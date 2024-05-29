package com.seachal.seachaltest.TextView.spannableString;

import android.graphics.Color;
import android.text.style.ForegroundColorSpan;

import com.seachal.seachaltest.R;


/**
 * 用户角色工具类（用于聊天、私聊、问答）
 */
public class UserRoleUtils {

    /**
     * 获取用户的角色头像
     */
    public static int getUserRoleAvatar(String userRole) {
        // 主讲（publisher）、助教（teacher）、主持人（host）、学生或观众（student）、其他没有角色（unknow）
        if ("publisher".equalsIgnoreCase(userRole)) {
            return R.drawable.head_view_publisher;
        }
        if ("teacher".equalsIgnoreCase(userRole)) {
            return R.drawable.head_view_teacher;
        }
        if ("host".equalsIgnoreCase(userRole)) {
            return R.drawable.head_view_host;
        }
        if ("student".equalsIgnoreCase(userRole)) {
            return R.drawable.head_view_student;
        }
        return R.drawable.head_view_student;
    }

    /**
     * 获取用户的角色头像的角色角标
     */
    public static int getUserRoleAvatarLogo(String userRole) {
        // 主讲（publisher）、助教（teacher）、主持人（host）、学生或观众（student）、其他没有角色（unknow）
        if ("publisher".equalsIgnoreCase(userRole)) {
            return R.drawable.head_view_publisher_logo;
        }

        if ("teacher".equalsIgnoreCase(userRole)) {
            return R.drawable.head_view_teacher_logo;
        }
        if ("host".equalsIgnoreCase(userRole)) {
            return R.drawable.head_view_host_logo;
        }
        return -1;
    }
    /**
     * 获取角色对应的名字的颜色
     */
    public static ForegroundColorSpan getUserRoleColorSpan(String userRole) {
        // 主讲（publisher）、助教（teacher）、主持人（host）、学生或观众（student）、其他没有角色（unknow）
        if ("publisher".equalsIgnoreCase(userRole)) {
            return new ForegroundColorSpan(Color.parseColor("#12ad1a"));
        }
        if ("teacher".equalsIgnoreCase(userRole)) {
            return new ForegroundColorSpan(Color.parseColor("#12ad1a"));
        }
        if ("host".equalsIgnoreCase(userRole)) {
            return new ForegroundColorSpan(Color.parseColor("#12ad1a"));
        }
        if ("student".equalsIgnoreCase(userRole)) {
            return new ForegroundColorSpan(Color.parseColor("#79808b"));
        }
        return new ForegroundColorSpan(Color.parseColor("#79808b"));
    }
    /**
     * 获取竖屏直播角色对应的名字的颜色
     */
    public static ForegroundColorSpan getVerticalUserRoleColorSpan(String userRole) {
        // 主讲（publisher）、助教（teacher）、主持人（host）、学生或观众（student）、其他没有角色（unknow）
        if ("publisher".equalsIgnoreCase(userRole)) {
            return new ForegroundColorSpan(Color.parseColor("#FF842F"));
        }
        if ("teacher".equalsIgnoreCase(userRole)) {
            return new ForegroundColorSpan(Color.parseColor("#0AC7FF"));
        }
        if ("host".equalsIgnoreCase(userRole)) {
            return new ForegroundColorSpan(Color.parseColor("#1BBD79"));
        }
        if ("student".equalsIgnoreCase(userRole)) {
            return new ForegroundColorSpan(Color.parseColor("#FFDD99"));
        }
        return new ForegroundColorSpan(Color.parseColor("#FFDD99"));
    }
    /**
     * 获取竖屏直播角色对应的名字的颜色
     */
    public static RadiusBackgroundSpan getVerticalUserRoleLabelSpan(String userRole) {
        int radius = 30;
        // 主讲（publisher）、助教（teacher）、主持人（host）、学生或观众（student）、其他没有角色（unknow）
        if ("publisher".equalsIgnoreCase(userRole)) {
            return new RadiusBackgroundSpan(Color.parseColor("#CCFF842F"),radius);
        }
        if ("teacher".equalsIgnoreCase(userRole)) {
            return new RadiusBackgroundSpan(Color.parseColor("#CC0088FE"),radius);
        }
        if ("host".equalsIgnoreCase(userRole)) {
            return new RadiusBackgroundSpan(Color.parseColor("#CC1BBD79"),radius);
        }
        if ("student".equalsIgnoreCase(userRole)) {
            return new RadiusBackgroundSpan(Color.parseColor("#FFDD99"),radius);
        }
        return new RadiusBackgroundSpan(Color.parseColor("#FFDD99"),radius);
    }
    public static int getVerticalUserRoleBg(String userRole) {
        int radius = 30;
        // 主讲（publisher）、助教（teacher）、主持人（host）、学生或观众（student）、其他没有角色（unknow）
        if ("publisher".equalsIgnoreCase(userRole)) {
            return R.drawable.shape_vertical_chat_top_role_publisher;
        }
        if ("teacher".equalsIgnoreCase(userRole)) {
            return R.drawable.shape_vertical_chat_top_role_teacher;
        }
        if ("host".equalsIgnoreCase(userRole)) {
            return R.drawable.shape_vertical_chat_top_role_host;
        }
        if ("student".equalsIgnoreCase(userRole)) {
            return 0;
        }
        return 0;
    }
    /**
     * 获取竖屏直播角色对应的名字的颜色
     */
    public static String getVerticalUserRoleName(String userRole) {
        // 主讲（publisher）、助教（teacher）、主持人（host）、学生或观众（student）、其他没有角色（unknow）
        if ("publisher".equalsIgnoreCase(userRole)) {
            return "讲师";
        }
        if ("teacher".equalsIgnoreCase(userRole)) {
            return "助教";
        }
        if ("host".equalsIgnoreCase(userRole)) {
            return "主持";
        }
        if ("student".equalsIgnoreCase(userRole)) {
            return "";
        }
        return "";
    }

}
