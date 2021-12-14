package com.seachal.seachaltest.kotlin.lambda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.seachal.seachaltest.R
import com.seachal.testkotlin.callback.CallBackImpByKotlin
import kotlinx.android.synthetic.main.activity_lambda.*

class LambdaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lambda)

        tv_1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
//                TODO("Not yet implemented")

            }

        })

//        tv_2.setOnClickListener()


//   =============
        //回调用lambda
        CallBackImpByKotlin().callBackMethod1 { code, msg ->
            //your code
            print("callBackMethod1")
        }
//有返回值的回调匿名函数写法
        CallBackImpByKotlin().callBackMethod3(fun(code, msg): Boolean {
            print("callBackMethod3 匿名函数")
            return true
        })
//有返回值的回调lambda写法
        CallBackImpByKotlin().callBackMethod3 { code, msg ->
            print("callBackMethod3 lambda")
            return@callBackMethod3 true
        }
////错误的匿类写法  ---------报错-------------
//        CallBackImpByKotlin().callBackMethod4(CallBackImpByKotlin.CallBack() {
//            @Override
//            fun callBack(code: Int, msg: String) {
//
//            }
//        })

////不像java能用lambda代替匿名类 ---------报错-------------
//        CallBackImpByKotlin().callBackMethod4 { code, msg ->
//            //your code
//        }

//是不是很抓狂，到底要我怎么写

//Kotlin匿名类的正确写法  ,
        CallBackImpByKotlin().callBackMethod4(object : CallBackImpByKotlin.CallBack {
            override fun callBack(code: Int, msg: String) {
                //your code
            }

        })


//     CallBackImpByJava是用Java定义的回调， 匿名类回调
        CallBackImpByJava().callBackMethod4(object : CallBackImpByJava.CallBack {
            override fun callBack(code: Int, msg: String?) {
                TODO("Not yet implemented")
            }

        })

       //     CallBackImpByJava是用Java定义的回调， lambda回调
        CallBackImpByJava().callBackMethod4({code,msg -> })

//     CallBackImpByJava是用Java定义的回调， lambda回调, 简化的写法。最后一个参数是 lambda，移到括号外面
        CallBackImpByJava().callBackMethod4(){code,msg -> }


        //     CallBackImpByJava是用Java定义的回调， lambda回调, 简化的写法， Lambda 是函数唯一的参数，你还可以直接把括号去了：
        CallBackImpByJava().callBackMethod4{ code, msg ->
            //your code
            print("callBackMethod1")
        }

//     CallBackImpByJava是用Java定义的回调， lambda回调, 简化的写法,移到括号外面




    }
}