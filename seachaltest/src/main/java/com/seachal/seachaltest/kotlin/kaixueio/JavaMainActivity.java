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
//        // ð è¿æ¯å¤æ
//        List<Button> buttons = new ArrayList<Button>();
////        List<TextView> textViews = buttons;
//        // ð å¤æç¨å¨è¿éä¼æ¥é incompatible types: List<Button> cannot be converted to List<TextView>
//
//
//        //     3
//        //Java çæ³åç±»åä¼å¨ç¼è¯æ¶åçç±»åæ¦é¤ï¼ä¸ºäºä¿è¯ç±»åå®å¨ï¼ä¸åè®¸è¿æ ·èµå¼ãè³äºä»ä¹æ¯ç±»åæ¦é¤ï¼è¿éå°±ä¸å±å¼äºã
//        //
//        //ä½ å¯ä»¥è¯ä¸ä¸ï¼å¨ Java éç¨æ°ç»åç±»ä¼¼çäºæï¼æ¯ä¸ä¼æ¥éçï¼è¿æ¯å ä¸ºæ°ç»å¹¶æ²¡æå¨ç¼è¯æ¶æ¦é¤ç±»åï¼
//        //        âï¸
//        TextView[] textViews1 = new TextView[10];
//
//        //==========        Java ä¸­ç ? extends
//
//        //        1
//        //        âï¸
//        List<Button> buttons2 = new ArrayList<Button>();
//        //             ð è¿ä¸ª ? extends å«åãä¸çééç¬¦ãï¼å¯ä»¥ä½¿ Java æ³åå·æãååæ§ Covarianceãï¼ååå°±æ¯åè®¸ä¸é¢çèµå¼æ¯åæ³çã
//        List<? extends TextView> textViews2 = buttons2;
//
//    }
//
//    //     2
//    //    âï¸ è¿é Button æ¯ TextView çå­ç±»ï¼æ»¡è¶³äºæ³åç±»åçéå¶æ¡ä»¶ï¼å èè½å¤æåèµå¼ã
//    List<? extends TextView> textViews3 = new ArrayList<TextView>(); // ð æ¬èº«
//    List<? extends TextView> textViews4 = new ArrayList<Button>(); // ð ç´æ¥å­ç±»
//    List<? extends TextView> textViews5 = new ArrayList<RadioButton>(); // ð é´æ¥å­ç±»
//
//
//    //  3
///*    ç±äºå®æ»¡è¶³ ? extends TextView çéå¶æ¡ä»¶ï¼æä»¥ get åºæ¥çå¯¹è±¡ï¼è¯å®æ¯ TextView çå­ç±»åï¼æ ¹æ®å¤æçç¹æ§ï¼è½å¤èµå¼ç» TextViewï¼å°å¦ä¸å¥ï¼èµå¼ç» View ä¹æ¯æ²¡é®é¢çã
//
//    å°äº add æä½çæ¶åï¼æä»¬å¯ä»¥è¿ä¹çè§£ï¼
//
//    List<? extends TextView> ç±äºç±»åæªç¥ï¼å®å¯è½æ¯ List<Button>ï¼ä¹å¯è½æ¯ List<TextView>ã
//    å¯¹äºåèï¼æ¾ç¶æä»¬è¦æ·»å  TextView æ¯ä¸å¯ä»¥çã
//    å®éæåµæ¯ç¼è¯å¨æ æ³ç¡®å®å°åºå±äºåªä¸ç§ï¼æ æ³ç»§ç»­æ§è¡ä¸å»ï¼å°±æ¥éäºã
//    é£æå¹²èä¸è¦ extends TextView ï¼åªç¨ééç¬¦ ? å¢ï¼*/
//    List<? extends TextView> textViews = new ArrayList<Button>();
//    TextView textView = textViews.get(0); // ð get å¯ä»¥
//     textViews.add(textView);
//    //         ð add ä¼æ¥éï¼no suitable method found for add(TextView)
//
//
//    //=======    java ä¸­ç ? super
//
//
////    List<? super Button> buttons = new ArrayList<Button>(); // ð æ¬èº«
////    List<? super Button> buttons2 = new ArrayList<TextView>(); // ð ç´æ¥ç¶ç±»
////    List<? super Button> buttons3 = new ArrayList<Object>(); // ð é´æ¥ç¶ç±»
//
//    List<? super Button> buttonsd4 = new ArrayList<TextView>();
//    Object object = buttonsd4.get(0); // ð get åºæ¥çæ¯ Object ç±»å
//    Button button = (Button) object;
////    buttonsd4; // ð add æä½æ¯å¯ä»¥ç
//
//
//}