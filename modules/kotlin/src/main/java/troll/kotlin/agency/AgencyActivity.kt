package troll.kotlin.agency

import android.view.LayoutInflater
import androidx.activity.viewModels
import troll.btc.extensions.onClick
import troll.eth.base.viewbinding.BaseActivity
import troll.kotlin.databinding.ActivityAgencyBinding

class AgencyActivity : BaseActivity<ActivityAgencyBinding>() {


    private val vm: AgencyViewModel by viewModels()

    override fun getBinding(inflater: LayoutInflater): ActivityAgencyBinding =
        ActivityAgencyBinding.inflate(inflater)

    override fun flowData() {
        val baseAgencyImpl = BaseAgencyImpl()
        bd.agencyButton.onClick {
            Agency(baseAgencyImpl).print()
        }
    }

    override fun flowView() {
    }
}