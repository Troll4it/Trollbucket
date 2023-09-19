package troll.kotlin.button

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import troll.btc.log.TLog

class PressButtonTwo @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val TAG = "按钮2222222"
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        TLog.i("${event?.action}", TAG)

        when (event?.action) {
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


        return super.onTouchEvent(event)

    }
}