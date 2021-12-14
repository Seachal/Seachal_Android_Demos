package com.seachal.seachaltest.permission

import android.annotation.TargetApi
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.view.Window
import android.view.WindowManager
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.reflect.InvocationTargetException


/**
 * Created by WanZhiYuan on 2018/10/30 0030.
 * 适配几个厂商的刘海屏
 * @update 2019/12/25
 * @author WanZhiYuan
 * @detail 适配多家厂商的权限管理页跳转
 */
object PhoneUtils {
    private val TAG = "PhoneUtils"

    private val VIVO_NOTCH = 0x00000020//是否有刘海
    private val VIVO_FILLET = 0x00000008//是否有圆角

    private val XIAOMI_FLAG = 0x00000100 //开启配置
    private val XIAOMI_FLAG_TWO = 0x00000200 //竖屏配置
    private val XIAOMI_FLAG_THREE = 0x00000400 //横屏配置

    //华为
    const val KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level"
    const val KEY_EMUI_VERSION = "ro.build.version.emui"
    const val KEY_EMUI_CONFIG_HW_SYS_VERSION = "ro.confg.hw_systemversion"

    //小米
    private val KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code"
    private val KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name"
    private val KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage"

    //OPPO
    private val KEY_OPPO_ROM = "ro.build.version.opporom"
    //vivo
    private val KEY_VIVO_ROM = "ro.vivo.os.version"

    /**
     * 适配技术地址：https://www.jianshu.com/p/561f7241153b/
     * 适配刘海屏(如果页面存在状态栏，不需要适配，全屏和沉浸页需要适配)
     * Android O适配系统的API
     */
    fun adaptLiuHaiScreen(window: Window) {
//        if (window == null) {
//            return
//        }
        //Android 适配刘海屏,根据系统自己的方法,只适配华为
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //因为Android O build/prop文件不可读，使用另一种获取品牌方式
//            if (OSUtils.getRomType() == OSUtils.ROM_TYPE.EMUI_ROM) {
//                //华为系统
//                adaptHuaweiLiuHaiScreen(window)
//            }
            if (!TextUtils.isEmpty(getSystemProperty(KEY_EMUI_API_LEVEL, ""))
                    || !TextUtils.isEmpty(getSystemProperty(KEY_EMUI_VERSION, ""))
                    || !TextUtils.isEmpty(getSystemProperty(KEY_EMUI_CONFIG_HW_SYS_VERSION, ""))) {
                //华为系统
                adaptHuaweiLiuHaiScreen(window)
            }

            //需要测试 暂时注释
//            else if (OSUtils.getRomType() == OSUtils.ROM_TYPE.MIUI_ROM) {
//                //小米系统
//                adaptXiaoMiLiuHaiScreen(window.context, window)
//            }
//            else if (OSUtils.getRomType() == OSUtils.ROM_TYPE.OPPO_ROM) {
//                //oppo系统 OPPO目前在设置 -- 显示 -- 应用全屏显示 -- 凹形区域显示控制，里面有关闭凹形区域开关 不提供api直接适配
//            } else if (OSUtils.getRomType() == OSUtils.ROM_TYPE.VIVO_ROM) {
//                //vivo系统 vivo在设置--显示与亮度--第三方应用显示比例中可以切换是否全屏显示还是安全区域显示 不提供api直接适配
//            } else {
//                //适配Android P 需要对应构建版本升级到28
////                adaptAndroidPLiuHaiScreen(window)
//            }
        }

    }

//    /**
//     * android9.0是否有设置刘海屏
//     */
//    @TargetApi(28)
//    fun hasAndroidPNotchInScreen(window: Window, callback: (Boolean) -> Unit = {}) {
//        if (window == null) {
//            callback(false)
//        }
//        val decorView = window.decorView
//        if (decorView == null) {
//            callback(false)
//        }
//        decorView.post {
//            val rootWindowInsets = decorView.rootWindowInsets
//            if (rootWindowInsets != null) {
//                val displayCutout = rootWindowInsets.displayCutout
//                if (displayCutout != null) {
//                    Log.e(TAG, "AndroidP的安全区域距离屏幕左边的距离 SafeInsetLeft:" + displayCutout.getSafeInsetLeft())
//                    Log.e(TAG, "AndroidP的安全区域距离屏幕右部的距离 SafeInsetRight:" + displayCutout.getSafeInsetRight())
//                    Log.e(TAG, "AndroidP的安全区域距离屏幕顶部的距离 SafeInsetTop:" + displayCutout.getSafeInsetTop())
//                    Log.e(TAG, "AndroidP的安全区域距离屏幕底部的距离 SafeInsetBottom:" + displayCutout.getSafeInsetBottom())
//                    val displayList = displayCutout.getBoundingRects()
//                    if (displayList == null || displayList.isEmpty()) {
//                        callback(false)
//                    } else {
//                        callback(true)
//                        Log.e(TAG, "AndroidP的刘海屏数量:" + displayList.size)
//                        displayList.forEach {
//                            Log.e(TAG, "AndroidP的刘海屏区域：" + it)
//                        }
//                        val lp = window.attributes
//                        if (lp != null) {
//                            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
//                            window.attributes = lp
//                        }
//                    }
//                } else {
//                    callback(false)
//                    Log.e(TAG, "AndroidP没有设置刘海屏 displayCutout = null")
//                }
//            } else {
//                callback(false)
//                Log.e(TAG, "AndroidP没有设置刘海屏 rootWindowInsets = null")
//            }
//        }
//    }

//    /**
//     * 适配AndroidP的刘海屏
//     */
//    @TargetApi(28)
//    fun adaptAndroidPLiuHaiScreen(window: Window) {
//        hasAndroidPNotchInScreen(window, callback = {
//            if (it) {
//                val lp = window.attributes
//                if (lp != null) {
//                    lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
//                    window.attributes = lp
//                }
//            }
//        })
//    }

    /**
     * 华为适配技术地址：https://devcenter-test.huawei.com/consumer/cn/devservice/doc/50114
     * 适配华为的刘海屏
     */
    private fun adaptHuaweiLiuHaiScreen(window: Window) {
        if (window == null) {
            return
        }
        if (hasNotchInScreenAtHuawei(window.context)) {
            setFullScreenWindowLayoutInDisplayCutout(window)
        }
    }

    /**
     * 华为手机是否有刘海屏
     * true：是刘海屏；false：非刘海屏。
     */
    fun hasNotchInScreenAtHuawei(context: Context): Boolean {
        var ret = false
        try {
            val cl = context.classLoader
            val HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil")
            val get = HwNotchSizeUtil.getMethod("hasNotchInScreen")
            ret = get.invoke(HwNotchSizeUtil) as Boolean
        } catch (e: ClassNotFoundException) {
            Log.e(TAG, "hasNotchInScreenAtHuawei ClassNotFoundException")
        } catch (e: NoSuchMethodException) {
            Log.e(TAG, "hasNotchInScreenAtHuawei NoSuchMethodException")
        } catch (e: Exception) {
            Log.e(TAG, "hasNotchInScreenAtHuawei Exception")
        } finally {
            if (ret) {
                Log.e(TAG, "华为有设置刘海屏")
            } else {
                Log.e(TAG, "华为没有设置刘海屏")
            }
            return ret
        }
    }

    /**
     * 华为手机获取刘海尺寸：width、height
     * int[0]值为刘海宽度 int[1]值为刘海高度。
     */
    fun getHuaweiNotchSize(context: Context): IntArray {
        var ret = intArrayOf(0, 0)
        try {
            val cl = context.classLoader
            val HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil")
            val get = HwNotchSizeUtil.getMethod("getNotchSize")
            ret = get.invoke(HwNotchSizeUtil) as IntArray
        } catch (e: ClassNotFoundException) {
            Log.e("test", "getHuaweiNotchSize ClassNotFoundException")
        } catch (e: NoSuchMethodException) {
            Log.e("test", "getHuaweiNotchSize NoSuchMethodException")
        } catch (e: Exception) {
            Log.e("test", "getHuaweiNotchSize Exception")
        } finally {
            return ret
        }
    }

    /*刘海屏全屏显示FLAG*/
    val FLAG_NOTCH_SUPPORT = 0x00010000

    /**
     * 设置应用窗口在华为刘海屏手机使用刘海区
     * @param window 应用页面window对象
     */
    fun setFullScreenWindowLayoutInDisplayCutout(window: Window?) {
        if (window == null) {
            return
        }
        val layoutParams = window.attributes
        try {
            val layoutParamsExCls = Class.forName("com.huawei.android.view.LayoutParamsEx")
            val con = layoutParamsExCls.getConstructor(WindowManager.LayoutParams::class.java)
            val layoutParamsExObj = con.newInstance(layoutParams)
            val method = layoutParamsExCls.getMethod("addHwFlags", Int::class.javaPrimitiveType)
            method.invoke(layoutParamsExObj, FLAG_NOTCH_SUPPORT)
        } catch (e: ClassNotFoundException) {
            Log.e(TAG, "hw add notch screen flag api error", e)
        } catch (e: NoSuchMethodException) {
            Log.e(TAG, "hw add notch screen flag api error", e)
        } catch (e: IllegalAccessException) {
            Log.e(TAG, "hw add notch screen flag api error", e)
        } catch (e: InstantiationException) {
            Log.e(TAG, "hw add notch screen flag api error", e)
        } catch (e: InvocationTargetException) {
            Log.e(TAG, "hw add notch screen flag api error", e)
        } catch (e: Exception) {
            Log.e(TAG, "other Exception")
        }
    }

    /**
     * vivo是否有刘海屏
     * 官方适配地址：https://dev.vivo.com.cn/documentCenter/doc/103
     * 只有一个获取高度的api
     */
    fun hasNotchAtVivo(context: Context): Boolean {
        var ret = false
        try {
            val classLoader = context.classLoader
            val FtFeature = classLoader.loadClass("android.util.FtFeature")
            val method = FtFeature.getMethod("isFeatureSupport", Int::class.javaPrimitiveType)
            ret = method.invoke(FtFeature, VIVO_NOTCH) as Boolean
        } catch (e: ClassNotFoundException) {
            Log.e(TAG, "hasNotchAtVivo ClassNotFoundException")
        } catch (e: NoSuchMethodException) {
            Log.e(TAG, "hasNotchAtVivo NoSuchMethodException")
        } catch (e: Exception) {
            Log.e(TAG, "hasNotchAtVivo Exception")
        } finally {
            if (ret) {
                Log.e(TAG, "vivo有设置刘海屏")
            } else {
                Log.e(TAG, "vivo没有设置刘海屏")
            }
            return ret
        }
    }

    /**
     * 获取vivo刘海尺寸
     * vivo不提供接口获取刘海尺寸，目前vivo的刘海宽为100dp,高为27dp,屏幕圆角24dp
     * 不排除以后会有新的大小出现
     * int[0]值为刘海宽度 int[1]值为刘海高度 int[2]值为屏幕圆角。dp
     */
    fun getVivoNotchSize(): IntArray {
        return intArrayOf(100, 27, 24)
    }

    /**
     * OPPO是否配置刘海屏
     * 官方适配地址：https://open.oppomobile.com/service/message/detail?id=61876
     */
    fun hasNotchAtOPPO(context: Context): Boolean {
        var ret = false
        try {
            ret = context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism") as Boolean
        } catch (e: Exception) {
            Log.e(TAG, "hasNotchAtOPPO Exception")
        } finally {
            if (ret) {
                Log.e(TAG, "OPPO有设置刘海屏")
            } else {
                Log.e(TAG, "OPPO没有设置刘海屏")
            }
            return ret
        }
    }

    /**
     * 获取oppo刘海屏高度
     * 官方文档表示 OPPO 手机的刘海高度和状态栏的高度是一致的，所以直接获取状态栏高度
     * 后续机型凹型屏的大小、尺寸、位置可能变化
     */
    fun getOppoNotchSize(context: Context): Int {
        var statusBarHeight = 0
        if (context == null) {
            return statusBarHeight
        }
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = context.resources.getDimensionPixelSize(resourceId)
        }
        return statusBarHeight
    }

    /**
     * 获取oppo刘海屏坐标
     */
    fun getOppoNotchCoord(): String {
        var value = ""
        var cls: Class<*>? = null
        try {
            cls = Class.forName("android.os.SystemProperties")
            val hideMethod = cls!!.getMethod("get",
                    String::class.java)
            val `object` = cls.newInstance()
            value = hideMethod.invoke(`object`, "ro.oppo.screen.heteromorphism") as String
        } catch (e: ClassNotFoundException) {
            Log.e(TAG, "getOppoNotchCoord error() ", e)
        } catch (e: NoSuchMethodException) {
            Log.e(TAG, "getOppoNotchCoord error() ", e)
        } catch (e: InstantiationException) {
            Log.e(TAG, "getOppoNotchCoord error() ", e)
        } catch (e: IllegalAccessException) {
            Log.e(TAG, "getOppoNotchCoord error() ", e)
        } catch (e: IllegalArgumentException) {
            Log.e(TAG, "getOppoNotchCoord error() ", e)
        } catch (e: InvocationTargetException) {
            Log.e(TAG, "getOppoNotchCoord error() ", e)
        }
        return value
    }

    /**
     * 适配小米notch机型的刘海屏(未测试)
     * 官方适配地址：https://dev.mi.com/console/doc/detail?pId=1293
     */
    private fun adaptXiaoMiLiuHaiScreen(context: Context, window: Window) {
        if (context == null || window == null) {
            return
        }
        if (hasNotchAtXiaoMi(context) && !hasXiaoMiLiuHaiScreen(context)) {
            val flag: Int = XIAOMI_FLAG or XIAOMI_FLAG_TWO
            statementNotchAtXiaoMi(flag, window)
        }
    }

    /**
     * 小米是否是notch机型
     */
    fun hasNotchAtXiaoMi(context: Context): Boolean {
        var ret = false
        if (context == null) {
            return ret
        }
        try {
            val c: Class<*> = Class.forName("android.os.SystemProperties")
            val get = c.getMethod("get", String::class.java)
            val isMotch = get.invoke(c, "ro.miui.notch")
            if (isMotch is Int) {
                if (isMotch == 1) {
                    ret = true
                } else {
                    ret = false
                }
            }
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (ret) {
                Log.e(TAG, "小米是notch手机")
            } else {
                Log.e(TAG, "小米不是notch手机")
            }
            return ret
        }

    }

    /**
     * 小米是否隐藏屏幕刘海
     */
    @TargetApi(17)
    fun hasXiaoMiLiuHaiScreen(context: Context): Boolean {
        var ret = true
        if (context == null) {
            return ret
        }
        try {
            ret = Settings.Global.getInt(context.getContentResolver(), "force_black", 0) == 1
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (ret) {
                Log.e(TAG, "小米隐藏了刘海")
            } else {
                Log.e(TAG, "小米没有隐藏刘海")
            }
            return ret
        }
    }

    /**
     * 获取小米当前设备刘海宽度
     */
    fun getXiaoMiNotchWidth(context: Context): Int {
        var statusBarWidth = 0
        if (context == null) {
            return statusBarWidth
        }
        val resourceId = context.getResources().getIdentifier("notch_width", "dimen", "android")
        if (resourceId > 0) {
            statusBarWidth = context.getResources().getDimensionPixelSize(resourceId)
        }
        return statusBarWidth
    }

    /**
     * 获取小米当前设备刘海高度
     */
    fun getXiaoMiNotchHeight(context: Context): Int {
        var statusBarHeight = 0
        if (context == null) {
            return statusBarHeight
        }
        val resourceId = context.getResources().getIdentifier("notch_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId)
        }
        return statusBarHeight
    }

    /**
     * 小米声明使用耳朵区
     * 0x00000100 开启配置
     * 0x00000200 竖屏配置
     * 0x00000400 横屏配置
     */
    fun statementNotchAtXiaoMi(flag: Int, window: Window) {
        try {
            val method = Window::class.java.getMethod("addExtraFlags",
                    Int::class.javaPrimitiveType)
            method.invoke(window, flag)
        } catch (e: ClassNotFoundException) {
            Log.i(TAG, "xiaomi class not found.")
        } catch (e: NoSuchMethodException) {
            Log.i(TAG, "xiaomi method not found.")
        } catch (e: Exception) {
            Log.i(TAG, "xiaomi exception：${e.toString()}")
        }
    }

    /**
     * 通过反射获取系统配置文件中是否存在该包名来判断是否匹配厂商
     */
    fun getSystemProperty(key: String, defaultValue: String): String {
        try {
            val clz = Class.forName("android.os.SystemProperties")
            val get = clz.getMethod("get", String::class.java, String::class.java)
            return get.invoke(clz, key, defaultValue) as String
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return defaultValue
    }


    /**
     * 适配跳转到华为、小米、魅族和索尼的权限设置页，其他的就跳转到系统设置页
     */
    fun getPermissionSetting(packageName: String): Intent {
        val intent = Intent()
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        when {
            //获取系统厂商的方法五花八门，必须根据系统去调用对的api才能正常获取到对应的厂商信息
            //而且调用对应系统的权限管理页面也是不一样的
            //如果都获取不到那么就调用跳转到系统设置页
            !TextUtils.isEmpty(getSystemProperty(KEY_EMUI_API_LEVEL, ""))
                    || !TextUtils.isEmpty(getSystemProperty(KEY_EMUI_VERSION, ""))
                    || !TextUtils.isEmpty(getSystemProperty(KEY_EMUI_CONFIG_HW_SYS_VERSION, "")) -> {
                //华为系统
                intent.component = ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity")
            }
            !TextUtils.isEmpty(getSystemProperty(KEY_MIUI_VERSION_CODE, ""))
                    || !TextUtils.isEmpty(getSystemProperty(KEY_MIUI_VERSION_NAME, ""))
                    || !TextUtils.isEmpty(getSystemProperty(KEY_MIUI_INTERNAL_STORAGE, "")) -> {
                //小米
                //还要区分版本，不同版本调用的类还不一样 - -!!
                val rom = getMiuiVersion()
                intent.action = "miui.intent.action.APP_PERM_EDITOR"
                intent.putExtra("extra_pkgname", packageName)
                if ("V6" == rom || "V7" == rom) {
                    intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity")
                } else if ("V8" == rom || "V9" == rom) {
                    intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity")
                }
            }
//                !TextUtils.isEmpty(getSystemProperty(KEY_OPPO_ROM, "")) -> {
//                    //oppo 有问题，oppo手机中找不到这个类，难道还要注册到我们自己的manifest里面？？？
//                    intent.putExtra("packageName", packageName)
//                    intent.component = ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.PermissionManagerActivity")
//                }
            Build.DISPLAY.contains("FLYME") -> {
                //魅族
                intent.action = "com.meizu.safe.security.SHOW_APPSEC"
                intent.addCategory(Intent.CATEGORY_DEFAULT)
                intent.putExtra("packageName", packageName)
            }
            Build.MANUFACTURER == "Sony" -> {
                //索尼
                intent.putExtra("packageName", packageName)
                intent.component = ComponentName("com.sonymobile.cta", "com.sonymobile.cta.SomcCTAMainActivity")
            }
            else -> {
                //这是跳转到应用详情页
//                if (Build.VERSION.SDK_INT >= 9) {
//                    intent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
//                    intent.data = Uri.fromParts("package", packageName, null)
//                } else if (Build.VERSION.SDK_INT <= 8) {
//                    intent.action = Intent.ACTION_VIEW
//                    intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails")
//                    intent.putExtra("com.android.settings.ApplicationPkgName", packageName)
//                }
                //这是系统设置页
                intent.action = Settings.ACTION_SETTINGS
            }
        }
        return intent
    }

    /**
     * 获取miui版本号
     */
    private fun getMiuiVersion(): String? {
        val propName = "ro.miui.ui.version.name"
        val line: String
        var input: BufferedReader? = null
        try {
            val p = Runtime.getRuntime().exec("getprop $propName")
            input = BufferedReader(
                    InputStreamReader(p.inputStream), 1024)
            line = input!!.readLine()
            input!!.close()
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        } finally {
            input?.close()
        }
        return line
    }
}