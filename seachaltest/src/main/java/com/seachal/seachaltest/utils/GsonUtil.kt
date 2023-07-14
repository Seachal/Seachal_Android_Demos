package com.seachal.seachaltest.utils

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object GsonUtil {
    private val gson = Gson()

    @JvmStatic
    fun <T> toBean(json: String, typeOfT: Type): T? {
        return try {
            gson.fromJson<T>(json, typeOfT)
        } catch (e: JsonSyntaxException) {
            return null
        }

    }

    fun <T> toJson(been: T, typeOfT: Type): String {
        return gson.toJson(been, typeOfT)
    }


    //各种BaseMultiItemQuickAdapter多类型Item的列表转换工具
    inline fun <reified T> convertList(list: List<Any>?): MutableList<T> {
        val dataList = mutableListOf<T>()
        list?.forEach { listItem ->
            val bean = toBean<T>(
                toJson(
                    listItem,
                    object : TypeToken<Any>() {}.type
                ), object : TypeToken<T>() {}.type
            )
            bean?.let { beanItem ->
                dataList.add(beanItem)
            }
        }
        return dataList
    }

    inline fun <reified T> convertBean(data: Any?): T? {
        return toBean<T>(
            toJson(data, object : TypeToken<Any>() {}.type),
            object : TypeToken<T>() {}.type
        )
    }

}
