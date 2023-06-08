## layer-list实现阴影效果

- 为控件实现阴影效果，可以有多种方式：
    - 多个drawable层叠在一起（不好的实现是多个View层叠达到多个drawable的层叠效果，相对好的实现是在同一个View钟实现多个drawable的层叠）
    - 自定义view
    - Material Design中设置Z轴的方式

- 本文的思路是多个drawable叠在一起，但是不额外使用View，通过layer-list可以将多个item按照顺序层叠在一起显示。首先来看效果图：

![LayerShadow](https://github.com/xpleemoon/layer-list-shadow-demo/blob/master/gif/LayerShadow.gif?raw=true)

- 第一个和第二个控件是用来展示layer-list实现阴影效果的基本款，而第三个控件是综合上述两个控件效果，再集合selector实现的。

- 默认状态：

``` xml
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- 阴影：左偏移2dp，上偏移4dp -->
    <item
        android:left="2dp"
        android:top="4dp">
        <shape>
            <solid android:color="@android:color/holo_blue_dark" />
            <corners android:radius="10dp" />
        </shape>
    </item>
    <!-- 前景：：底偏移4dp，右偏移2dp -->
    <item
        android:bottom="4dp"
        android:right="2dp">
        <shape>
            <solid android:color="@android:color/holo_blue_bright" />
            <corners android:radius="10dp" />
        </shape>
    </item>
</layer-list>
```

- 点击状态：

``` xml
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- 为了达到点击的真实感，将原来默认状态的前景色设置为阴影，并将前景设为无透明-->
    <item
        android:left="2dp"
        android:top="4dp">
        <shape>
            <solid android:color="@android:color/holo_blue_bright" />
            <corners android:radius="10dp" />
        </shape>
    </item>
    <item
        android:bottom="4dp"
        android:right="2dp">
        <shape>
            <corners android:radius="10dp" />
        </shape>
    </item>
</layer-list>
```

> layer-list的item可以通过以下属性设置偏移量：
>
> - android:top 顶部的偏移量
> - android:bottom 底部的偏移量
> - android:left 左边的偏移量
> - android:right 右边的偏移量

- selector，使用上述layer-list：

``` xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/layer_list_btn_pressed" android:state_pressed="true" />
    <item android:drawable="@drawable/layer_list_btn_pressed" android:state_selected="true" />
    <item android:drawable="@drawable/layer_list_btn" />

</selector>
```

- 最后再来看下，布局代码：

``` xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context=".MainActivity">

    <TextView
        android:layout_width="100dp"
        android:layout_height="50dp"
        android:background="@drawable/layer_list_btn"
        android:clickable="true"
        android:gravity="center"
        android:text="默认状态" />

    <TextView
        android:layout_width="100dp"
        android:layout_height="50dp"
        android:layout_marginTop="@dimen/activity_vertical_margin"
        android:background="@drawable/layer_list_btn_pressed"
        android:clickable="true"
        android:gravity="center"
        android:text="点击状态" />

    <TextView
        android:layout_width="100dp"
        android:layout_height="50dp"
        android:layout_marginTop="@dimen/activity_vertical_margin"
        android:background="@drawable/selector_btn"
        android:clickable="true"
        android:gravity="center"
        android:text="点我" />
</LinearLayout>
```

- 第一个和第二个TextView分别引用了对应的layer-list（默认和点击）作为背景，第三个引用了selector

- ***[源码地址](https://github.com/seachal/layer-list-shadow-demo)***