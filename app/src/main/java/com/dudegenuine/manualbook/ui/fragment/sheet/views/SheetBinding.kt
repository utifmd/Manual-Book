package com.dudegenuine.manualbook.ui.fragment.sheet.views

import android.content.Context
import android.content.res.ColorStateList
import androidx.fragment.app.Fragment
import com.dudegenuine.manualbook.R
import com.dudegenuine.manualbook.ui.extention.themeColor
import com.google.android.material.shape.MaterialShapeDrawable

/**
 * Manual Book created by utifmd on 27/06/21.
 */
object SheetBinding {
    fun Fragment.materialSheetBackground(
        backgroundContext: Context ): MaterialShapeDrawable{
        return  MaterialShapeDrawable(
            backgroundContext,
            null,
            R.attr.bottomSheetStyle,
            0
        ).apply {
            fillColor = ColorStateList.valueOf(
                backgroundContext.themeColor(R.attr.colorPrimarySurfaceVariant)
            )
            elevation = resources.getDimension(R.dimen.plane_08)
            initializeElevationOverlay(requireContext())
        }
    }
}