package wan.projectimport android.view.LayoutInflaterimport androidx.fragment.app.viewModelsimport androidx.recyclerview.widget.LinearLayoutManagerimport com.alibaba.android.arouter.launcher.ARouterimport troll.eth.base.viewbinding.BaseFragmentimport wan.common.RoutePathimport wan.common.RouterKeyimport wan.project.databinding.FragmentProjectBinding/** * author : TangPeng * date : 3/2/22 15:28 * description : 项目 */class ProjectFragment : BaseFragment<FragmentProjectBinding>() {    private val vm: ProjectViewModel by viewModels()    private val adapter = ProjectAdapter {        ARouter.getInstance()            .build(RoutePath.PROJECT_TREE)            .withInt(RouterKey.ID, it.id)            .navigation()    }    override fun getBinding(inflater: LayoutInflater): FragmentProjectBinding =        FragmentProjectBinding.inflate(inflater)    override fun flowData() {        vm.getProjectTree()        vm.liveProjectTree.observe(this) {            adapter.submitList(it)        }    }    override fun flowView() {        bd.projectRv.layoutManager = LinearLayoutManager(requireContext())        bd.projectRv.adapter = adapter    }}