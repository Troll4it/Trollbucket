package troll.kotlin.channelbusimport android.view.LayoutInflaterimport com.troll.bus.channel.ChannelBusimport com.troll.bus.channel.Eventsimport kotlinx.coroutines.Dispatchersimport troll.btc.extensions.onClickimport troll.eth.base.viewbinding.BaseActivityimport troll.kotlin.databinding.ActivityChannelBusBindingclass ChannelBusActivity : BaseActivity<ActivityChannelBusBinding>() {    override fun getBinding(inflater: LayoutInflater): ActivityChannelBusBinding =        ActivityChannelBusBinding.inflate(inflater)    override fun flowData() {        ChannelBus.bus.receive(lifecycleOwner = this, context =  Dispatchers.Main) {            bd.busTv.text = "收到数据"        }    }    override fun flowView() {        bd.busButtonSend.onClick {            ChannelBus.bus.send(Events.EVENT_1)        }    }}