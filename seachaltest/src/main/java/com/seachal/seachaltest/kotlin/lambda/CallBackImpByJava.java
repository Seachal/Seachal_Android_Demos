package com.seachal.seachaltest.kotlin.lambda;

/**
 * *
 * *
 * Project_Name:SeachalDemos
 *
 * @author zhangxc
 * @date 2020/12/15 17:01
 * *
 */
public class CallBackImpByJava {

    interface CallBack {
         void  callBack(int code,String msg);
    }


    //  接收的参数是   CallBack 类型
    public void callBackMethod4(CallBack callBack) {
        callBack.callBack(1, "msg");
    }
}
