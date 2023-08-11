package troll.kotlin.animation

import android.animation.TypeEvaluator

class PointEvaluator : TypeEvaluator<Point> {
    override fun evaluate(fraction: Float, startValue: Point?, endValue: Point?): Point {
        return if (startValue == null || endValue == null) {
            Point(0f, 0f)
        } else {
            val x = startValue.x.plus(fraction.times(endValue.x.minus(startValue.x)))
            val y = startValue.y.plus(fraction.times(endValue.y.minus(startValue.y)))
            return Point(x, y)
        }
    }

}