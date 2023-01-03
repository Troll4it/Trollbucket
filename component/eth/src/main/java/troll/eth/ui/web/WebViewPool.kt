package troll.eth.ui.webimport android.content.Contextimport android.content.MutableContextWrapperimport troll.btc.log.TLogimport java.util.*/** * author : TangPeng * date : 12/6/22 19:20 * description : */class WebViewPool {    companion object {        private const val TAG = "WebViewPool"        @Volatile        private var instance: WebViewPool? = null        fun getInstance(): WebViewPool {            return instance ?: synchronized(this) {                instance ?: WebViewPool().also { instance = it }            }        }    }    private val lock = byteArrayOf()    private val pool = Stack<BaseWebView>()    private var maxSize = 1    /**     * 设置 webview 池容量     */    fun setMaxPoolSize(size: Int) {        synchronized(lock) { maxSize = size }    }    fun init(context: Context, initSize: Int = maxSize) {        for (i in 0 until initSize) {            pool.push(BaseWebView(MutableContextWrapper(context)).apply {                webChromeClient = BaseWebChromeClient()                webViewClient = BaseWebViewClient()            })        }    }    fun getView(context: Context): BaseWebView {        synchronized(lock) {            val webView: BaseWebView            if (pool.size > 0) {                webView = pool.pop()                TLog.d("getWebView from pool", TAG)            } else {                webView = BaseWebView(MutableContextWrapper(context))                TLog.d("getWebView from create", TAG)            }            val contextWrapper = webView.context as MutableContextWrapper            contextWrapper.baseContext = context            // 默认设置            webView.webChromeClient = BaseWebChromeClient()            webView.webViewClient = BaseWebViewClient()            return webView        }    }    /**     * 回收 WebView     */    fun recycle(webView: BaseWebView) {        // 释放资源        webView.release()        // 根据池容量判断是否销毁 【也可以增加其他条件 如手机低内存等等】        val contextWrapper = webView.context as MutableContextWrapper        contextWrapper.baseContext = webView.context.applicationContext        synchronized(lock) {            if (pool.size < maxSize) {                pool.push(webView)            } else {                webView.destroy()            }        }    }}