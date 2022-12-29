package troll.eth.ui.webimport android.annotation.SuppressLintimport android.content.Contextimport android.util.AttributeSetimport android.view.ViewGroupimport android.webkit.WebViewimport android.webkit.WebViewClientimport androidx.lifecycle.Lifecycleimport androidx.lifecycle.LifecycleEventObserverimport androidx.lifecycle.LifecycleOwner/** * author : TangPeng * date : 12/6/22 11:30 * description : */class BaseWebView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :    WebView(context, attrs), LifecycleEventObserver {    init {        setWebContentsDebuggingEnabled(true)        isVerticalScrollBarEnabled = false        isHorizontalFadingEdgeEnabled = false        WebUtil.defaultSetting(context, this)    }    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {        when (event) {            Lifecycle.Event.ON_RESUME -> onResume()            Lifecycle.Event.ON_STOP -> onPause()            Lifecycle.Event.ON_DESTROY -> {                source.lifecycle.removeObserver(this)                onDestroy()            }        }    }    override fun getUrl(): String? {        return super.getOriginalUrl() ?: return super.getUrl()    }    override fun canGoBack(): Boolean {        val backForwardList = copyBackForwardList()        val currentIndex = backForwardList.currentIndex - 1        if (currentIndex >= 0) {            val item = backForwardList.getItemAtIndex(currentIndex)            if (item?.url == "about:blank") {                return false            }        }        return super.canGoBack()    }    @SuppressLint("SetJavaScriptEnabled")    override fun onResume() {        super.onResume()        settings.javaScriptEnabled = true    }    fun release() {        (parent as ViewGroup?)?.removeView(this)        removeAllViews()        stopLoading()        setCustomWebViewClient(null)        setCustomWebChromeClient(null)        loadUrl("about:blank")        clearHistory()    }    fun setCustomWebViewClient(client: BaseWebViewClient?) {        if (client == null) {            super.setWebViewClient(WebViewClient())        } else {            super.setWebViewClient(client)        }    }    fun setCustomWebChromeClient(client: BaseWebChromeClient?) {        super.setWebChromeClient(client)    }    fun setLifecycleOwner(owner: LifecycleOwner) {        owner.lifecycle.addObserver(this)    }    fun onDestroy() {        settings.javaScriptEnabled = false    }}