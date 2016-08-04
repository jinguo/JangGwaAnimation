package com.example.zkw.janggwaanimation.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.example.zkw.janggwaanimation.bean.Point;
import com.example.zkw.janggwaanimation.evaluator.ColorEvaluator;
import com.example.zkw.janggwaanimation.evaluator.PointEvaluaor;


/**
 * Created by zkw on 2016/8/4.
 */
public class AnimationView extends View{
    public static final float RADIUS = 70f;
    private Point currentPoint;
    private Paint mPaint;
    private String color;

    public AnimationView(Context context) {
        super(context);
    }

    public AnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentPoint == null) {
            currentPoint = new Point(RADIUS, RADIUS);
            drawCircle(canvas);
            startAnimation();
        } else {
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas) {
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        canvas.drawCircle(x, y, RADIUS, mPaint);
    }

    private void startAnimation() {
        Point startPoint = new Point(getWidth() / 2,RADIUS);
        Point endPoint = new Point(getWidth() / 2, getHeight() - RADIUS);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new PointEvaluaor(), startPoint, endPoint);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(this, "color", new ColorEvaluator(), "#0000FF", "#FF0000");
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(this,"alpha",1.0f,0.1f);
        AnimatorSet animationSet = new AnimatorSet();
        animationSet.play(objectAnimator1).with(valueAnimator).with(objectAnimator);
        animationSet.setDuration(3000);
        animationSet.setInterpolator(new BounceInterpolator());
        animationSet.start();
    }

}
