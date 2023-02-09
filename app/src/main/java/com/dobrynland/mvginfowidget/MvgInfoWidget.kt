package com.dobrynland.mvginfowidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import com.dobrynland.mvginfowidget.rest.RestApi
import com.dobrynland.mvginfowidget.rest.RetrofitHelper
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Implementation of App Widget functionality.
 */
class MvgInfoWidget : AppWidgetProvider() {
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
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

@OptIn(DelicateCoroutinesApi::class)
internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    Log.i(MvgInfoWidget::class.simpleName, "updateAppWidget")

    val mvgApi = RetrofitHelper.getInstance().create(RestApi::class.java)
    GlobalScope.launch {
        val result = mvgApi.getDepartureInfoList()
        Log.d("Result status: ", result.message())
        Log.d("Result: ", result.body().toString())
    }

    val widgetText = ""

    val views = RemoteViews(context.packageName, R.layout.mvg_info_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}

