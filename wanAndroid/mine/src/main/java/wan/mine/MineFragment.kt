package wan.mineimport android.view.LayoutInflaterimport com.alibaba.android.arouter.launcher.ARouterimport troll.btc.extensions.onClickimport troll.btc.extensions.startAcimport troll.eth.base.viewbinding.BaseFragmentimport wan.common.RoutePathimport wan.mine.databinding.FragmentMineBinding/** * author : TangPeng * date : 3/2/22 15:40 * description : */class MineFragment : BaseFragment<FragmentMineBinding>() {    override fun getBinding(inflater: LayoutInflater): FragmentMineBinding =        FragmentMineBinding.inflate(inflater)    override fun flowData() {    }    override fun flowView() {        bd.mineLogin.onClick {            ARouter.getInstance().build(RoutePath.LOGIN_ACTIVITY).navigation()        }    }}