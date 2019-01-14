package com.an.github.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

public class AnimUtils {

    public static void slideView(View view,
                                 RecyclerView recyclerView,
                                 RecyclerView.Adapter adapter) {

        ValueAnimator slideAnimator = ValueAnimator
                .ofFloat(0f, 0.69f)
                .setDuration(500);

        slideAnimator.addUpdateListener(animation1 -> {
            Float value = (Float) animation1.getAnimatedValue();
            ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) view.getLayoutParams();
            lp.matchConstraintPercentHeight = value;
            view.setLayoutParams(lp);
        });

        AnimatorSet animationSet = new AnimatorSet();
        animationSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}
            @Override
            public void onAnimationEnd(Animator animation) {
                recyclerView.setAdapter(adapter);
                recyclerView.scheduleLayoutAnimation();
            }
            @Override
            public void onAnimationCancel(Animator animation) {}
            @Override
            public void onAnimationRepeat(Animator animation) {}
        });
        animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animationSet.play(slideAnimator);
        animationSet.start();
    }
}
