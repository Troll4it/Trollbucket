package troll.kotlin.serviceimport android.content.Intentimport android.os.Buildimport android.view.LayoutInflaterimport androidx.annotation.RequiresApiimport troll.btc.extensions.onClickimport troll.eth.base.viewbinding.BaseActivityimport troll.kotlin.databinding.ActivityServiceBinding/** * author : TangPeng * date : 9/14/22 14:27 * description : */class ServiceActivity : BaseActivity<ActivityServiceBinding>() {    override fun getBinding(inflater: LayoutInflater): ActivityServiceBinding =        ActivityServiceBinding.inflate(inflater)    @RequiresApi(Build.VERSION_CODES.O)    override fun flowData() {        bd.serviceShow.onClick {            val intent = Intent(this, WidgetService::class.java)            startForegroundService(intent)        }    }    override fun flowView() {    }}