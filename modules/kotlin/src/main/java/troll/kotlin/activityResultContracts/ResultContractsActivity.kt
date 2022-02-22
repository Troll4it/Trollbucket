package troll.kotlin.activityResultContractsimport android.content.Contextimport android.content.Intentimport android.view.LayoutInflaterimport androidx.activity.result.contract.ActivityResultContractimport troll.eth.base.viewbinding.BaseActivityimport troll.kotlin.coroutines.CoroutinesActivityimport troll.kotlin.databinding.ActivityResultContractBindingclass ResultContractsActivity : BaseActivity<ActivityResultContractBinding>() {    override fun getBinding(inflater: LayoutInflater): ActivityResultContractBinding =        ActivityResultContractBinding.inflate(inflater)    private val launcher = registerForActivityResult(ResultContract()) {        println("返回结果$it")    }    override fun flowData() {    }    override fun flowView() {        bd.contractButton.setOnClickListener {            launcher.launch("测试数据数据")        }    }}class ResultContract : ActivityResultContract<String, String>() {    // 传递数据    override fun createIntent(context: Context, input: String?): Intent {        return Intent(context, CoroutinesActivity::class.java).apply {            putExtra("name", input)        }    }    // 解析数据    override fun parseResult(resultCode: Int, intent: Intent?): String {        return intent?.getStringExtra("result") ?: ""    }}