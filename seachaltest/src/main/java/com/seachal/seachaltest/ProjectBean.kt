package com.seachal.seachaltest

/**
 **
 * *
 * Project_Name:SeachalDemos
 * @author zhangxc
 * @date 2020/3/14 11:27
 * 用于测试 从 生成 kotlin bean
 * *
 */
data class ProjectBean(
    val code: String,
    val `data`: Data,
    val msg: String
)

data class Data(
    val enableStatus: String,
    val endDay: String,
    val maxMoney: String,
    val mixMoney: String,
    val startDay: String
)