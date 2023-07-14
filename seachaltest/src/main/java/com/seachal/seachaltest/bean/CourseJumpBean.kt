package com.seachal.seachaltest.bean

/**
 **
 * *
 * Project_Name:zxxk
 * @Description: TODO
 * @author zhangxc
 * @date 2023/7/13 10:03
 * @Version：1.0
 */
// 如果解析失败  chapterId = 0
//data class CourseJumpBean(val courseId: Long, val chapterId: Long)

// 如果解析失败  chapterId = null
data class CourseJumpBean(val courseId: Long?, val chapterId: Long?)


data class CourseJumpBeanString(val courseId: String?, val chapterId: String?)