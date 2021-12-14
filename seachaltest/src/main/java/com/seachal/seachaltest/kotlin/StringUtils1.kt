package com.seachal.seachaltest.kotlin


/**
 *   @author : zhangxc
 *   @time   : 2021/03/30
 *   @desc : 字符串工具类 top-level
 *
 */

/**
 * @Description:
 * @Author: zhangx
 * @Date:  2021/3/30 15:52
 * @Update: 2021/3/30 15:52
 */
public  fun removeSensitiveInfo(account: String?): String {
    return account?.apply {
        when {
            account.contains("@") -> {
                //隐藏邮箱中间部分
                account.replace("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)".toRegex(), "$1****$3$4")
            }
            account.length == 11 -> {
                //隐藏手机号码中间四位
                 account.replace("(\\d{3})\\d{4}(\\d{4})".toRegex(), "$1****$2")
                //隐藏手机号码中间四位

                //隐藏手机号码中间四位
                 account.replace("(\\d{3})\\d{4}(\\d{4})".toRegex(), "$1****$2")
            }
            else -> {
                account
            }
        }
    } ?: ""
}

