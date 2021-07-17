package com.dudegenuine.manualbook.feature.di.module.factory

import android.text.Spanned

interface IHtmlSpanned{
    fun fromString(s: String): Spanned
}