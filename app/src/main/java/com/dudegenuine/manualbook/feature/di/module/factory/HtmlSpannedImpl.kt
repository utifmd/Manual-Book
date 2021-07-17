package com.dudegenuine.manualbook.feature.di.module.factory

import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.core.text.toSpanned

class HtmlSpannedImpl: IHtmlSpanned {
    override fun fromString(s: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            HtmlCompat.fromHtml(s, FROM_HTML_MODE_LEGACY)
        }else s.toSpanned()
    }
}
/*fun provideDateLastWeek(current: Date){
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.time = current
    calendar.add(Calendar.DAY_OF_YEAR, -7)
    val newDate = calendar.time

    val date = dateFormat.format(newDate)
}

fun getWidgetAlertMessage(context: Context, title: String, message: String) {
    AlertDialog.Builder(context).apply {
        setTitle(title)
        setMessage(message)
        create()
        show()
    }
}

fun getWidgetShareText(context: Context, title: String, text: String) {
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
}*/