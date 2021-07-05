package com.dudegenuine.manualbook.feature.di.module.utility

import android.content.Context

/**
 * Manual Book created by utifmd on 04/07/21.
 */
interface IUtility {
    // fun htmlToSting(rawText: String): String
    fun getWidgetAlertMessage(context: Context, title: String, message: String)

    fun getWidgetShareText(context: Context, title: String, text: String)
}