package com.seachal.seachaltest.TextView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.seachal.seachaltest.R;
import com.seachal.seachaltest.View.CustomSpan.SpannableStringUtils;
import com.seachal.seachaltest.customview.CustomTextViewTestActivity;

/**
 * *
 * *
 * Project_Name:SeachalDemos
 * <p>
 * <p>
 * Android中实现TextView超链接五种方式
 * https://www.jianshu.com/p/29a379512a13
 * https://blog.csdn.net/lyankj/article/details/51882335
 *
 * @author zhangxc
 * @date 2019-09-25 10:33
 * *
 */
public class LinkTextViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_textview);


        //         1 .
        TextView mTextView = (TextView) findViewById(R.id.text);

        //将TextView的显示文字设置为SpannableString
        mTextView.setText(getClickableSpan());
        //设置该句使文本的超连接起作用
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());


        //       解析 2.
        //解析 html
        TextView textView1 = findViewById(R.id.text1);
        CharSequence charSequence;
        //      String content = "<p>简介：</p><p>1.nickname:wildma！</p><p>2.职业：android攻城狮</p>";

        String content = "<a href='http://www.baidu.com'>百度一下</a>";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            charSequence = Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY);
        } else {
            charSequence = Html.fromHtml(content);
        }
        textView1.setText(charSequence);


        //解析 html
        String html = "http://www.baidu.com";
        mTextView.setAutoLinkMask(Linkify.ALL);//布局中设置android:autoLink 后这一句就不需要了
        mTextView.setText(html);


        TextView textView2 = findViewById(R.id.text2);
        textView2.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        textView2.getPaint().setAntiAlias(true);

        TextView textView21 = findViewById(R.id.text21);
        textView21.setText(String.format(getString(R.string.is_about_to_arrive_underline), "333"));
        textView21.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        textView21.getPaint().setAntiAlias(true);


        TextView textView3 = findViewById(R.id.text3);
        //        //在textview中设置图片
        //        SpannableString spannableString5 = new SpannableString("\n设置图片，在第一个字符上设置");
        //        spannableString5.setSpan(new DynamicDrawableSpan() {
        //            @Override
        //            public Drawable getDrawable() {
        //                Drawable d = getResources().getDrawable(R.drawable.ic_launcher_background);
        //                d.setBounds(0, 0, 50, 50);
        //                return d;
        //            }
        //        }, 2, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //        textView3.append(spannableString5);
        //在textview中设置图片
        SpannableString spannableString5 = new SpannableString("淘宝 包邮商品赶快下单吧");
        spannableString5.setSpan(new DynamicDrawableSpan() {
            @Override
            public Drawable getDrawable() {
                Drawable d = getResources().getDrawable(R.drawable.shape_mall_rectangle_tag_platform);
                d.setBounds(0, 0, 50, 50);
                return d;
            }
        }, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView3.append(spannableString5);


        //        TextView tv_show = findViewById(R.id.tv_show);
        //        SpannableStringBuilder ssb = new SpannableStringBuilder("这是一个字符串");
        //        //第一个参数是样式，第二和第三个参数是要改变的区间，最后一个参数对TextView没有用
        //        //当是EditText的时候决定是否会对两侧新输入的文字进行同样的改变
        //        //这里的设置是对两侧都不改变
        //        ssb.setSpan(new UnderlineSpan(),0,2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //        ssb.setSpan(new BackgroundColorSpan(Color.GREEN),0,1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //        ssb.setSpan(new ForegroundColorSpan(Color.RED),1,2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //        //获取图片
        //        Drawable d = getResources().getDrawable(R.drawable.ic_launcher);
        //        //这行不能少 设置固有宽高
        //        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        //        ImageSpan imageSpan = new ImageSpan(d);
        //        //替换一个文字为图片
        //        ssb.setSpan(imageSpan,2,3,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //        tv_show.setText(ssb);
        //        //用超链接标记文本
        //        ssb.setSpan(new URLSpan("dsaas"), 3, 4,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //        //斜粗体
        //        ssb.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 4, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //        //改变大小
        //        //这个是相对大小
        //        //ssb.setSpan(new RelativeSizeSpan(1.5f), 5, 6,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //        //绝对大小 设置数值
        //        ssb.setSpan(new AbsoluteSizeSpan(30), 5, 6,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //        ssb.setSpan(new StyleSpan(Typeface.ITALIC), 5, 6,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //        //删除线标
        //        ssb.setSpan(new StrikethroughSpan(), 6, 7,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView tv_my_span = findViewById(R.id.tv_my_span);
        SpannableStringUtils.getLabelStyleText(this, new String[]{"淘宝，包邮商品赶快下单"}, tv_my_span, R.drawable.shape_mall_rectangle_tag_platform);
        //

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_my_span.invalidate();
                tv_my_span.measure(100, 100);
                tv_my_span.setWidth(tv_my_span.getMeasuredWidth());
            }
        }, 50);
        tv_my_span.setText("为啥没生效");


        CheckBox checkbox = findViewById(R.id.checkbox);


        CheckBox checkbox2 = findViewById(R.id.checkbox2);
        //      临时
        checkbox2.setChecked(false);
        checkbox2.setCompoundDrawables(ContextCompat.getDrawable(LinkTextViewActivity.this, R.drawable.mall_red_checkbox_style), null, null, null);


        CheckBox checkbox3 = findViewById(R.id.checkbox3);
        checkbox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox3.isChecked()) {
                    checkbox3.setChecked(true);
                } else {
                    checkbox3.setChecked(false);
                }
            }
        });


        CheckBox checkbox4 = findViewById(R.id.checkbox4);
        checkbox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox4.isChecked()) {
                    checkbox4.setChecked(false);
                } else {
                    checkbox4.setChecked(true);
                }
            }
        });


        //
        CheckBox checkbox5 = findViewById(R.id.checkbox5);
        checkbox5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox5.isChecked()) {
                    checkbox5.setChecked(true);
                } else {
                    checkbox5.setChecked(false);
                }
            }
        });

        CheckBox checkbox6 = findViewById(R.id.checkbox6);
        checkbox6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox6.isChecked()) {
                } else {
                }
            }
        });

        Drawable drawable = getResources().getDrawable(R.drawable.mall_red_checkbox_style);


        CheckBox checkbox7 = findViewById(R.id.checkbox7);
        checkbox7.setEnabled(false);
        checkbox7.setChecked(true);
        Log.i("checkbox7", String.valueOf(checkbox7.isChecked()));
        //        checkbox7.setCompoundDrawables(ContextCompat.getDrawable(LinkTextViewActivity.this,R.drawable.mall_red_checkbox_style),null,null,null );
        checkbox7.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(LinkTextViewActivity.this, R.drawable.mall_red_checkbox_style), null, null, null);


        Drawable image = LinkTextViewActivity.this.getResources().getDrawable(R.drawable.mall_red_checkbox_style);
        int h = image.getIntrinsicHeight();
        int w = image.getIntrinsicWidth();
        image.setBounds(0, 0, w, h);


        CheckBox checkbox8 = findViewById(R.id.checkbox8);
        checkbox8.setEnabled(false);
        checkbox8.setChecked(false);
        //        checkbox8.setCompoundDrawables(ContextCompat.getDrawable(LinkTextViewActivity.this,R.drawable.mall_red_checkbox_style),null,null,null );
        checkbox8.setCompoundDrawables(image, null, null, null);
        Log.i("checkbox8", String.valueOf(checkbox8.isChecked()));


        CheckBox checkbox9 = findViewById(R.id.checkbox9);
        checkbox9.setChecked(true);
        checkbox9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()) {
                    return;
                }
            }
        });


        //        Context context = EcejApplicationContext.context;
        //
        //        int color =  ContextCompat.getColor(context,R.color.colorAccent);
        //
        //
        //        Log.i("checkbox8","color:"+color);

        Button btn_clickable_false = findViewById(R.id.btn_clickable_false);
        Button btn_clickable_true = findViewById(R.id.btn_clickable_true);
        btn_clickable_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_clickable_false.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });




        /**
         * 由此可以得出结论，CheckBox  设置setOnClickListener，
         *
         * 应该用于查询 CheckBox当前的选中状态，然后做相应的逻辑处理，不应该用于改变CheckBox的选中状态
         *
         */


    }

    //设置超链接文字
    private SpannableString getClickableSpan() {
        String string1 = "使用该软件，即表示您同意该软件的使用条款和隐私政策-测试";
        SpannableString spanStr = new SpannableString(string1);
        //设置下划线文字,16、20 是根据问题长度计算到使用条款的位置。
        spanStr.setSpan(new UnderlineSpan(), 16, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的单击事件，调到其他 activity
        spanStr.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                startActivity(new Intent(LinkTextViewActivity.this, CustomTextViewTestActivity.class));
            }
        }, 16, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的前景色
        spanStr.setSpan(new ForegroundColorSpan(Color.RED), 16, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        /*第二个文字*/

        //设置下划线文字
        spanStr.setSpan(new UnderlineSpan(), 21, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的单击事件， 调用自带浏览器。https://blog.csdn.net/bzlj2912009596/article/details/80673555
        spanStr.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                //  startActivity(new Intent(LinkTextViewActivity.this, CustomTextViewTestActivity.class));
                Uri uri = Uri.parse("https://www.baidu.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        }, 21, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的前景色
        spanStr.setSpan(new ForegroundColorSpan(Color.BLUE), 21, string1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanStr;
    }
}
