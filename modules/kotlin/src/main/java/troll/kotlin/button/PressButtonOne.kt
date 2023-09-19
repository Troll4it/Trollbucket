package troll.kotlin.button

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import troll.btc.log.TLog

class PressButtonOne @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    val TAG = "按钮11111111"

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        TLog.i("${ev?.action}", TAG)
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                TLog.i("down  down  down  down", TAG)
            }

            MotionEvent.ACTION_CANCEL -> {
                TLog.i("cancel  cancel  cancel  cancel", TAG)

            }

            MotionEvent.ACTION_MOVE -> {
                TLog.i("move  move  move  move", TAG)
            }

            MotionEvent.ACTION_UP -> {
                TLog.i("up  up  up  up", TAG)
            }

            MotionEvent.ACTION_BUTTON_PRESS -> {
                TLog.i("press  press  press  press", TAG)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    }
}