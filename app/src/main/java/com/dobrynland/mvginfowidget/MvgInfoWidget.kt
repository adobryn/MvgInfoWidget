package com.dobrynland.mvginfowidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import com.dobrynland.mvginfowidget.data.AppContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Implementation of App Widget functionality.
 */
class MvgInfoWidget : AppWidgetProvider(), CoroutineScope {
    lateinit var container: AppContainer
    var widgetText = ""

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        container = AppContainer()
        launch {
            val result =  callGetApi()
            onResult(result) // onResult is called on the main thread
        }
    }

    override fun onDisabled(context: Context) {
        job.cancel()
    }

    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        Log.i(MvgInfoWidget::class.simpleName, "updateAppWidget")

        //widgetText = getWidgetText()
        val views = RemoteViews(context.packageName, R.layout.mvg_info_widget)
        views.setTextViewText(R.id.appwidget_text, widgetText)

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    suspend fun callGetApi(): String {
        return container.departureInfoRepository.getDepartures().departures.toString()
    }

    fun onResult(result: String) {
        widgetText = result
    }
}



