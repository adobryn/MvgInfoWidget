package com.dobrynland.mvginfowidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import androidx.lifecycle.LiveData
import com.dobrynland.mvginfowidget.data.DepartureInfo
import com.dobrynland.mvginfowidget.rest.DepartureInfoRepository

/**
 * Implementation of App Widget functionality.
 */
class MvgInfoWidget : AppWidgetProvider() {
    var widgetText = ""

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
        showFirstDepartureInfo()
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        Log.i(MvgInfoWidget::class.simpleName, "updateAppWidget")

        val views = RemoteViews(context.packageName, R.layout.mvg_info_widget)
        views.setTextViewText(R.id.appwidget_text, widgetText)

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    private val repository: DepartureInfoRepository = DepartureInfoRepository()

    private fun getFirstDepartureInfo(): LiveData<List<DepartureInfo>> {
        return repository.getDepartures()
    }

    private fun showFirstDepartureInfo() {
        getFirstDepartureInfo().observeForever {
            widgetText = it[0].toString()
        }
    }

}



