package com.seachal.seachaltest.kotlin.DiYiHangDaiMa

/**
 **
 * *
 * Project_Name:SeachalDemos
 * @author zhangxc
 * @date 2021/3/30 14:41
 * *
 */
class TestDiYiHangDaiMa {



    public fun  ss(){
        val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
        val lambda = { fruit: String -> fruit.length }
        val maxLengthFruit = list.maxBy(lambda)

    }
}