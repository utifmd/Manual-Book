package com.dudegenuine.manualbook.feature.di.module.utility

import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.Html
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.text.HtmlCompat
import dagger.Module
import dagger.Provides
import java.text.SimpleDateFormat
import java.util.*

/**
 * Manual Book created by utifmd on 26/06/21.
 */

class UtilityModule: IUtility {
    /*fun provideDateLastWeek(current: Date){
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.time = current
        calendar.add(Calendar.DAY_OF_YEAR, -7)
        val newDate = calendar.time

        val date = dateFormat.format(newDate)
    }*/

    override fun getWidgetAlertMessage(context: Context, title: String, message: String) {
        AlertDialog.Builder(context).apply {
            setTitle(title)
            setMessage(message)
            create()
            show()
        }
    }

    override fun getWidgetShareText(context: Context, title: String, text: String) {
        try {
            context.startActivity(
                Intent.createChooser(Intent().apply {
                    action = Intent.ACTION_SEND
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TITLE, title)
                    putExtra(Intent.EXTRA_TEXT, text) // data = contentUri
                    flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                }, "Choose One")
            )
        }catch (e: Exception){
            getWidgetAlertMessage(context, "Invalid", "${e.message}")
        }
    }
}