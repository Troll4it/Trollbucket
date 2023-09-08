package troll.kotlin.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

class PickView @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {

    private val waveLength = 400
    private var dx = 0f
    private var dy = 0

    private val path = Path()
    private val bottomPaint = Paint().apply {
        isAntiAlias = true
        color = Color.RED
        style = Paint.Style.FILL_AND_STROKE
        strokeWidth = 10f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        path.reset()

        val halfWaveLength = waveLength / 2

        val dividingLineHeight = height / 2  // 曲线的中间位置

        if (dy < dividingLineHeight + 150) {
            dy += 1
        }
        path.moveTo((-waveLength + dx), (dividingLineHeight - dy).toFloat())
        for (i in -waveLength until width.plus(waveLength)) {
            path.rQuadTo((halfWaveLength / 2).toFloat(), -100f, halfWaveLength.toFloat(), 0f)
            path.rQuadTo((halfWaveLength / 2).toFloat(), 100f, halfWaveLength.toFloat(), 0f)
        }
        path.lineTo(width.toFloat(), height.toFloat())
        path.lineTo(0f, height.toFloat())
        path.close()
        bottomPaint.shader = LinearGradient(
            width / 2f,
            height / 2f,
            width / 2f,
            height.toFloat(),
            Color.parseColor("#FDD1D6"),
            Color.parseColor("#FDA3C6"),
            Shader.TileMode.CLAMP
        )
        canvas?.drawPath(path, bottomPaint)
    }

    fun startAnimation() {
        ValueAnimator.ofInt(0, waveLength).apply {
            duration = 1000
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener { animation ->
                dx = animation?.animatedValue.toString().toFloat()
                postInvalidateDelayed(400)
            }
        }.start()

    }
}