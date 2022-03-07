package wan.projectimport android.view.LayoutInflaterimport androidx.activity.viewModelsimport androidx.recyclerview.widget.GridLayoutManagerimport com.alibaba.android.arouter.facade.annotation.Autowiredimport com.alibaba.android.arouter.facade.annotation.Routeimport troll.btc.extensions.onClickimport troll.eth.base.viewbinding.BaseActivityimport wan.common.RoutePathimport wan.project.databinding.ActivityProjectTreeBinding/** * author : TangPeng * date : 3/4/22 16:58 * description : */@Route(path = RoutePath.PROJECT_TREE)class ProjectTreeActivity : BaseActivity<ActivityProjectTreeBinding>() {    @Autowired    @JvmField    var id: Int = 0    private val vm: ProjectTreeViewModel by viewModels()    private val adapter by lazy {        ProjectTreeAdapter {            println("测试 ${it.envelopePic}")        }    }    override fun getBinding(inflater: LayoutInflater): ActivityProjectTreeBinding =        ActivityProjectTreeBinding.inflate(inflater)    override fun flowData() {        vm.getData(id)        vm.liveData.observe(this) {            adapter.submitList(it)        }    }    override fun flowView() {        bd.projectTvBack.onClick {            finish()        }        bd.projectRv.layoutManager = GridLayoutManager(this, 2)        bd.projectRv.adapter = adapter    }}