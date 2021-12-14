package com.seachal.seachaltest.kotlin.lambda;

/**
 * *
 * *
 * Project_Name:SeachalDemos
 *
 * @author zhangxc
 * @date 2020/12/15 15:46
 * *
 */
public class JavaCallBack {



    /*----回调定义----*/
    static class CallBackImp {
        interface CallBack {
            void callBack(int code, String msg);
        }

        void callBackMethod(CallBack callBack) {
            callBack.callBack(1, "msg");
        }
    }

    public static void main(String[] args) {
        /*----回调使用----*/
        //回调用匿名类调用
        new CallBackImp().callBackMethod(new CallBackImp.CallBack() {
            @Override
            public void callBack(int code, String msg) {
                //your code
            }
        });
        //回调用lambda
        new CallBackImp().callBackMethod((code, msg) -> {
            //your code
        });

        // java 中只有一个提示，但是可以有两种写法
        new CallBackImp().callBackMethod(new CallBackImp.CallBack() {
            @Override
            public void callBack(int code, String msg) {
                //your code
            }
        });

    }


}
