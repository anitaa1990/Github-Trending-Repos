package com.an.github.utils

object AnimUtils {

//    fun slideView(view: View,
//                  recyclerView: RecyclerView,
//                  adapter: RecyclerView.Adapter<*>) {
//
//        val slideAnimator = ValueAnimator
//                .ofFloat(0f, 0.69f)
//                .setDuration(500)
//
//        slideAnimator.addUpdateListener { animation1 ->
//            val value = animation1.animatedValue as Float
//            val lp = view.layoutParams as ConstraintLayout.LayoutParams
//            lp.matchConstraintPercentHeight = value
//            view.layoutParams = lp
//        }
//
//        val animationSet = AnimatorSet()
//        animationSet.addListener(object : Animator.AnimatorListener {
//            override fun onAnimationStart(animation: Animator) {}
//            override fun onAnimationEnd(animation: Animator) {
//                recyclerView.adapter = adapter
//                recyclerView.scheduleLayoutAnimation()
//            }
//
//            override fun onAnimationCancel(animation: Animator) {}
//            override fun onAnimationRepeat(animation: Animator) {}
//        })
//        animationSet.interpolator = AccelerateDecelerateInterpolator()
//        animationSet.play(slideAnimator)
//        animationSet.start()
//    }
}
