package com.seachal.testkotlin.callback

/**
 * 参考： [Java,Kotlin,Dart中实现回调的区别_ReminderFish-CSDN博客_dart 回调](https://blog.csdn.net/kingyc123456789/article/details/107799532)
 */
/*----回调定义----*/
class CallBackImpByKotlin {
//    callBackMethod4 被调用
    interface CallBack {
        fun callBack(code: Int, msg: String)
    }

    //回调不能为空
    fun callBackMethod1(callBack: (code: Int, msg: String) -> Unit) {
        callBack.invoke(1, "msg")
    }

    //回调可以为空
    fun callBackMethod2(callBack: ((code: Int, msg: String) -> Unit)?) {
        callBack?.invoke(1, "msg")
    }

    //有返回值的回调
    fun callBackMethod3(callBack: ((code: Int, msg: String) -> Boolean)) {
        callBack.invoke(1, "msg")
    }

//  接收的参数是   CallBack 类型
    fun callBackMethod4(callBack: CallBack) {
        callBack.callBack(1, "msg")
    }
}
