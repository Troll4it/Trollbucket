package troll.eth.base.viewbinding.dialogimport android.os.Bundleimport android.view.*import androidx.fragment.app.DialogFragmentimport androidx.fragment.app.FragmentManagerimport androidx.viewbinding.ViewBindingimport com.gyf.immersionbar.ImmersionBarimport troll.eth.R/** * author : TangPeng * date : 8/12/22 11:29 * description : */abstract class BaseDialog<BD : ViewBinding> : DialogFragment() {    lateinit var bd: BD        private set    var full: Boolean = false    var local: DialogLocal = DialogLocal.CENTER    override fun onCreate(savedInstanceState: Bundle?) {        super.onCreate(savedInstanceState)        when (local) {            DialogLocal.CENTER -> {                setStyle(STYLE_NO_TITLE, R.style.CenterDialog)            }        }    }    override fun onStart() {        super.onStart()        setDialogGravity()    }    override fun onCreateView(        inflater: LayoutInflater,        container: ViewGroup?,        savedInstanceState: Bundle?    ): View? {        bd = getBinding(inflater, container)        return bd.root    }    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {        super.onViewCreated(view, savedInstanceState)        if (full) {            fullDialog()        }        flowData()        flowView()    }    abstract fun flowData()    abstract fun flowView()    abstract fun getBinding(        inflater: LayoutInflater,        container: ViewGroup? = null,        attachToRoot: Boolean = false    ): BD    fun showDialog(manager: FragmentManager?, tag: String = this.javaClass.simpleName) {        manager?.run {            val ft = this.beginTransaction()            ft.add(this@BaseDialog, tag)            ft.commitAllowingStateLoss()        }    }    private fun setDialogGravity() {        dialog?.window?.run {            val params = this.attributes            if (isWidthMatchParent) { // 撑满整个屏幕                params.width = WindowManager.LayoutParams.MATCH_PARENT            } else {                params.width = WindowManager.LayoutParams.WRAP_CONTENT            }            if (height > 0) {                params.height = height            } else {                params.height = WindowManager.LayoutParams.WRAP_CONTENT            }            params.gravity = when (local) {                DialogLocal.CENTER -> {                    Gravity.CENTER                }                DialogLocal.TOP -> {                    Gravity.TOP                }                DialogLocal.BOTTOM -> {                    Gravity.BOTTOM                }            }            this.attributes = params        }    }    var isWidthMatchParent = false    private var height: Int = -1    fun setDialogHeight(height: Int) {        this.height = height    }    private fun fullDialog() {        ImmersionBar.with(this, true) //                .titleBar(toolbar)            .statusBarDarkFont(true) //                .navigationBarColor(R.color.btn3)            .keyboardEnable(true)            .addTag(this.javaClass.simpleName)            .init()    }    /**     * 设置背景透明度     */    fun setBgAlpha(alpha: Float) {        activity?.let {            val lp = it.window.attributes            lp.alpha = alpha            val window = it.window            if (window != null) {                if (alpha == 1f) {                    //不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug                    window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)                } else {                    //此行代码主要是解决在华为手机上半透明效果无效的bug                    window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)                }                window.attributes = lp            }        }    }    fun dismissDialog() {        dialog?.dismiss()        dialog?.let { ImmersionBar.with(requireActivity(), it).init() };    }}