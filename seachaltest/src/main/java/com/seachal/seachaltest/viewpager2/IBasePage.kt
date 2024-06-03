package com.seachal.seachaltest.viewpager2

interface IBasePage {
    fun getContentLayoutId(): Int

    fun initData()

    fun initListeners()

    fun loadData()
}