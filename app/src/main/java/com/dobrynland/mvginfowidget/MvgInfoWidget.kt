package com.dobrynland.mvginfowidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import com.dobrynland.mvginfowidget.data.DepartureInfo
import com.dobrynland.mvginfowidget.rest.RestApi
import com.dobrynland.mvginfowidget.rest.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    Log.i(MvgInfoWidget::class.simpleName, "updateAppWidget")

    getInfo()
   /* GlobalScope.launch {
        val result = mvgApiClient.getDepartureInfoList()
        Log.d("Result status: ", result.)
        Log.d("Result: ", result.body().toString())
    }*/

    val widgetText = ""

    val views = RemoteViews(context.packageName, R.layout.mvg_info_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}

fun getInfo() {
    val service = RetrofitHelper.getInstance().create(RestApi::class.java).getDepartureInfoList()

    service.enqueue(object : Callback<List<DepartureInfo>> {
        override fun onResponse(
            call: Call<List<DepartureInfo>>?,
            response: Response<List<DepartureInfo>>?
        ) {
            if (response?.body() != null) {
                Log.i("Response: ", response.body().toString())
            }
            //recyclerAdapter.setMovieListItems(response.body()!!)
        }

        override fun onFailure(call: Call<List<DepartureInfo>>?, t: Throwable?) {
            Log.e("Error fetching response: ", t?.message!!)
        }
    })
}

