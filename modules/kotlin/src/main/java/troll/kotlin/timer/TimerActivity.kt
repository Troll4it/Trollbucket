package troll.kotlin.timerimport android.view.LayoutInflaterimport kotlinx.coroutines.MainScopeimport kotlinx.coroutines.flow.launchInimport kotlinx.coroutines.flow.onEachimport troll.btc.extensions.onClickimport troll.btc.extensions.throttleFirstimport troll.btc.util.time.timer.FlowTimerimport troll.eth.base.viewbinding.BaseActivityimport troll.kotlin.databinding.ActivityTimerBinding/** * author : TangPeng * date : 11/22/22 17:10 * description : */class TimerActivity : BaseActivity<ActivityTimerBinding>() {    override fun getBinding(inflater: LayoutInflater): ActivityTimerBinding =        ActivityTimerBinding.inflate(inflater)    override fun flowData() {    }    override fun flowView() {        val flowTimer = FlowTimer(10, MainScope(), onStart = {            println("倒计时 onStart ")        }, onTick = {            println("倒计时 onTick $it ")        }, onStop = {            println("倒计时 onStop ")        }, onFinish = {            println("倒计时 onFinish ")        }, onCancel = {            println("倒计时 onCancel ")        })        bd.timerCancel.onClick().throttleFirst().onEach {            println("当前时间 取消")            flowTimer.cancel()        }.launchIn(MainScope())        bd.timerStart.onClick {            flowTimer.start()        }        bd.timerStop.onClick {            flowTimer.stop()        }        bd.timerRestart.onClick {            flowTimer.reStart()        }        bd.timerCancel.onClick {            flowTimer.cancel()        }    }    fun sayHello(): Unit {        return Unit    }}