package troll.kotlin.widgetimport android.appwidget.AppWidgetManagerimport android.appwidget.AppWidgetProviderimport android.content.ComponentNameimport android.content.Contextimport android.content.Intentimport android.view.Viewimport android.widget.RemoteViewsimport troll.kotlin.R/** * author : TangPeng * date : 10/20/22 16:36 * description : */class AdaptiveWidget : AppWidgetProvider() {    private val ACTION_WEATHER_REFRESH = "com.troll.widget.adapt"    override fun onEnabled(context: Context?) {        super.onEnabled(context)    }    override fun onReceive(context: Context?, intent: Intent?) {        super.onReceive(context, intent)        if (intent?.action == ACTION_WEATHER_REFRESH) {            val num = intent.getIntExtra("num", 0)            if (context != null) {                refreshWidget(context, num)            }        }    }    private fun refreshWidget(context: Context, num: Int) {        val remoteView = createRemoteView(context, num)        val appWidgetManager = AppWidgetManager.getInstance(context)        val componentName = ComponentName(context, AdaptiveWidget::class.java)        appWidgetManager.updateAppWidget(componentName, remoteView)    }    override fun onUpdate(        context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?    ) {        super.onUpdate(context, appWidgetManager, appWidgetIds)        appWidgetIds?.forEach {            val remoteView = createRemoteView(context, 0)            appWidgetManager?.updateAppWidget(it, remoteView)        }    }    private fun createRemoteView(context: Context?, it: Int): RemoteViews {        val remoteViews = RemoteViews(context?.packageName, R.layout.widget_adaptive)        when (it) {            0 -> {                remoteViews.setViewVisibility(R.id.widget_tv_one, View.VISIBLE)            }            1 -> {                remoteViews.setViewVisibility(R.id.widget_tv_two, View.VISIBLE)            }            2 -> {                remoteViews.setViewVisibility(R.id.widget_tv_three, View.VISIBLE)            }            3 -> {                remoteViews.setViewVisibility(R.id.widget_tv_four, View.VISIBLE)            }            4 -> {                remoteViews.setViewVisibility(R.id.widget_tv_one, View.GONE)                remoteViews.setViewVisibility(R.id.widget_tv_two, View.GONE)                remoteViews.setViewVisibility(R.id.widget_tv_three, View.GONE)                remoteViews.setViewVisibility(R.id.widget_tv_four, View.GONE)            }        }        return remoteViews    }}