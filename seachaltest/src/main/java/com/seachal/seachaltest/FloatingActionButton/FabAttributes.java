package com.seachal.seachaltest.FloatingActionButton;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/*
 * 项目名:    SuspensionFAB
 * 包名       com.azhon.suspensionfab
 * 文件名:    FabAttributes
 * 创建者:    ZSY
 * 创建时间:  2017/6/28 on 18:20
 */
public final class FabAttributes {

    private Builder builder;

    private FabAttributes(Builder builder) {
        this.builder = builder;
    }

    public Builder getBuilder() {
        return builder;
    }

    public static class Builder {
        //插入的图片
        public Drawable src;
        //fab的背景色
        public int backgroundTint = Color.parseColor("#FF6633");
        //fab的阴影大小
        public int elevation = 0;
        //fab的大小
        public int fabSize = FloatingActionButton.SIZE_NORMAL;
        //fab按下的阴影大小
        public int pressedTranslationZ = 0;
        //fab按下的颜色
        public int rippleColor;
        //
        public int maxImageSize;
        //fab标记
        public Object tag;

        public Builder() {
        }

        public Builder setSrc(Drawable src) {
            this.src = src;
            return this;
        }

        public Builder setMaxImageSize(int size) {
            this.maxImageSize = size;
            return this;
        }

        public Builder setBackgroundTint(int backgroundTint) {
            this.backgroundTint = backgroundTint;
            return this;
        }

        public Builder setElevation(int elevation) {
            this.elevation = elevation;
            return this;
        }

        public Builder setFabSize(int fabSize) {
            this.fabSize = fabSize;
            return this;
        }

        public Builder setPressedTranslationZ(int pressedTranslationZ) {
            this.pressedTranslationZ = pressedTranslationZ;
            return this;
        }

        public Builder setRippleColor(int rippleColor) {
            this.rippleColor = rippleColor;
            return this;
        }

        public Builder setTag(Object tag) {
            this.tag = tag;
            return this;
        }

        public FabAttributes build() {
            return new FabAttributes(this);
        }
    }
}
