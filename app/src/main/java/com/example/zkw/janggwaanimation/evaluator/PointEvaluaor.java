package com.example.zkw.janggwaanimation.evaluator;

import android.animation.TypeEvaluator;

import com.example.zkw.janggwaanimation.bean.Point;

/**
 * Created by zkw on 2016/8/4.
 */
public class PointEvaluaor implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y  = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
        Point point = new Point(x,y);
        return point;
    }
}
