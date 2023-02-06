package wan.projectimport android.view.LayoutInflaterimport androidx.activity.viewModelsimport androidx.lifecycle.Lifecycleimport androidx.lifecycle.flowWithLifecycleimport androidx.lifecycle.lifecycleScopeimport androidx.recyclerview.widget.DividerItemDecorationimport com.alibaba.android.arouter.facade.annotation.Autowiredimport com.alibaba.android.arouter.facade.annotation.Routeimport com.alibaba.android.arouter.launcher.ARouterimport kotlinx.coroutines.launchimport troll.btc.extensions.onClickimport wan.common.CommonActivityimport wan.common.RoutePathimport wan.common.RouterKeyimport wan.common.launchWithLoadingimport wan.project.databinding.ActivityProjectTreeBinding/** * author : TangPeng * date : 3/4/22 16:58 * description : */@Route(path = RoutePath.PROJECT_TREE)class ProjectTreeActivity : CommonActivity<ActivityProjectTreeBinding>() {    @Autowired    @JvmField    var id: Int = 0    private val vm: ProjectTreeViewModel by viewModels()    private val adapter by lazy {        ProjectTreeAdapter {            ARouter.getInstance().build(RoutePath.WEB_ACTIVITY).withString(RouterKey.URL, it.link)                .withString(RouterKey.TITLE, it.title).navigation()        }    }    override fun getBinding(inflater: LayoutInflater): ActivityProjectTreeBinding =        ActivityProjectTreeBinding.inflate(inflater)    override fun flowData() {        id = intent.getIntExtra(RouterKey.ID, 0)        launchWithLoading {            vm.getData(id)        }        lifecycleScope.launch {            vm.dataState.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collect {                adapter.submitList(it)            }        }    }    override fun flowView() {        bd.projectTvBack.onClick {            finish()        }        bd.projectRv.addItemDecoration(            DividerItemDecoration(                this, DividerItemDecoration.VERTICAL            )        )        bd.projectRv.adapter = adapter    }}