package com.seachal.seachaltest.ObjectAnimator

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.BounceInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.seachal.seachaltest.R
import kotlinx.android.synthetic.main.activity_object_animator.tv_hello
import kotlinx.android.synthetic.main.activity_object_animator.tv_hello2
import kotlinx.android.synthetic.main.activity_object_animator.tv_hello3
import kotlinx.android.synthetic.main.activity_object_animator.tv_hello4


class ObjectAnimatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object_animator)

        animator1(tv_hello)
        animator2(tv_hello2)
        animator3(tv_hello3)
        animator4(tv_hello4)
   



    }

    fun animator1( view : View) {

        val animator: ObjectAnimator = ObjectAnimator.ofFloat(view, "translationX", 0f, 20f, 0f)
        animator.setDuration(30000) // 设置动画持续时间为3秒，一次动画持续的时间

//        animator.repeatCount = ObjectAnimator.INFINITE // 设置动画无限次重复

        animator.interpolator = BounceInterpolator() // 设置反弹插值器

        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                // 动画结束后重新开始
                view.visibility = android.view.View.GONE
            }
        })
        view.setOnClickListener {
            animator.start() // 开始动画
        }
    }

    fun animator2( view : View){
        val animator = ObjectAnimator.ofFloat(view, "translationX", -20f, 20f, 0f)
        animator.duration = 3000
        animator.repeatCount = ValueAnimator.INFINITE // // 设置动画重复次数为无限次
        animator.repeatMode = ValueAnimator.REVERSE  // // 设置动画重复模式为反向

        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                view?.visibility = View.GONE
            }
        })
        view.setOnClickListener {
            animator.start()
        }
    }

    fun animator3( view : View){
        val animator = ObjectAnimator.ofFloat(view, "translationX", -20f, 20f, 0f)
        animator.duration = 3000

//         这里注释后没有执行 3s.
//        animator.repeatCount = ValueAnimator.INFINITE // // 设置动画重复次数为无限次
//        animator.repeatMode = ValueAnimator.REVERSE  // // 设置动画重复模式为反向

        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                view?.visibility = View.GONE
            }
        })
        view.setOnClickListener {
            animator.start()
        }
    }

    fun animator4( view : View){
        val animator = ObjectAnimator.ofFloat(view, "translationX", -20f, 20f, 0f)
        animator.duration = 1500   // 一次动画持续的时间
        animator.repeatCount = 2 // // 设置动画重复次数为无限次
//        animator.repeatMode = ValueAnimator.REVERSE  // // 设置动画重复模式为反向

        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                view?.visibility = View.GONE
            }
        })
        view.setOnClickListener {
            animator.start()
        }
    }
}