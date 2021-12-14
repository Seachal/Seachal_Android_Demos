package com.seachal.seachaltest.kotlin.kaixueio

/**
 **
 * *
 * Project_Name:SeachalDemos
 * @author zhangxc
 * @date 2020/12/15 16:07
 *
 * 参考 ：[Kotlin 的 Lambda 表达式，大多数人学得连皮毛都不算](https://kaixue.io/kotlin-lambda/)
 * *
 */
//fun a(funParam: Fun): String {
//    return funParam(1);
//}

//   "(Int) -> String" 是函数类型
fun a(funParam: (Int) -> String): String {
    return funParam(0)
}


fun b(param: Int): String {
    print(param.toString())
    return  param.toString()
}

fun main() {
// 这种写法是正常， 需要加上 :: ,因为加了两个冒号，这个函数才变成了一个对象。
    a(::b)
//    这种写法是会报错的，
//    a(b)

    val d = ::b


    val e1 = d

//  这种写法是调用，  注意调用和引用的区别。
    val d2 = b(212)


    b(1) // 调用函数
    d(2) // 用对象 a 后面加上括号来实现 b() 的等价操作
    (::b)(3) // 用对象 ::b 后面加上括号来实现 b() 的等价操作


    d(4) // 实际上会调用 d.invoke(4)
    (::b)(5) // 实际上会调用 (::b).invoke(5)


//    所以你可以对一个函数类型的对象调用 invoke()，但不能对一个函数这么做：
//    b.invoke(1) // 报错
    ::b.invoke(6) // 不会报错
    d.invoke(7)

}









