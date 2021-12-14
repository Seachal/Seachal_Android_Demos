//package com.seachal.seachaltest.kotlin;
//
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.RadioButton;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.seachal.seachaltest.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class JavaMainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_java_main);
//        //        1
//        //        List<TextView> textViews = new ArrayList<TextView>();
//
//        //     2
//        TextView textView = new Button(JavaMainActivity.this);
//        // 👆 这是多态
//        List<Button> buttons = new ArrayList<Button>();
////        List<TextView> textViews = buttons;
//        // 👆 多态用在这里会报错 incompatible types: List<Button> cannot be converted to List<TextView>
//
//
//        //     3
//        //Java 的泛型类型会在编译时发生类型擦除，为了保证类型安全，不允许这样赋值。至于什么是类型擦除，这里就不展开了。
//        //
//        //你可以试一下，在 Java 里用数组做类似的事情，是不会报错的，这是因为数组并没有在编译时擦除类型：
//        //        ☕️
//        TextView[] textViews1 = new TextView[10];
//
//        //==========        Java 中的 ? extends
//
//        //        1
//        //        ☕️
//        List<Button> buttons2 = new ArrayList<Button>();
//        //             👇 这个 ? extends 叫做「上界通配符」，可以使 Java 泛型具有「协变性 Covariance」，协变就是允许上面的赋值是合法的。
//        List<? extends TextView> textViews2 = buttons2;
//
//    }
//
//    //     2
//    //    ☕️ 这里 Button 是 TextView 的子类，满足了泛型类型的限制条件，因而能够成功赋值。
//    List<? extends TextView> textViews3 = new ArrayList<TextView>(); // 👈 本身
//    List<? extends TextView> textViews4 = new ArrayList<Button>(); // 👈 直接子类
//    List<? extends TextView> textViews5 = new ArrayList<RadioButton>(); // 👈 间接子类
//
//
//    //  3
///*    由于它满足 ? extends TextView 的限制条件，所以 get 出来的对象，肯定是 TextView 的子类型，根据多态的特性，能够赋值给 TextView，啰嗦一句，赋值给 View 也是没问题的。
//
//    到了 add 操作的时候，我们可以这么理解：
//
//    List<? extends TextView> 由于类型未知，它可能是 List<Button>，也可能是 List<TextView>。
//    对于前者，显然我们要添加 TextView 是不可以的。
//    实际情况是编译器无法确定到底属于哪一种，无法继续执行下去，就报错了。
//    那我干脆不要 extends TextView ，只用通配符 ? 呢？*/
//    List<? extends TextView> textViews = new ArrayList<Button>();
//    TextView textView = textViews.get(0); // 👈 get 可以
//     textViews.add(textView);
//    //         👆 add 会报错，no suitable method found for add(TextView)
//
//
//    //=======    java 中的 ? super
//
//
////    List<? super Button> buttons = new ArrayList<Button>(); // 👈 本身
////    List<? super Button> buttons2 = new ArrayList<TextView>(); // 👈 直接父类
////    List<? super Button> buttons3 = new ArrayList<Object>(); // 👈 间接父类
//
//    List<? super Button> buttonsd4 = new ArrayList<TextView>();
//    Object object = buttonsd4.get(0); // 👈 get 出来的是 Object 类型
//    Button button = (Button) object;
////    buttonsd4; // 👈 add 操作是可以的
//
//
//}