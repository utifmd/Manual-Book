package com.dudegenuine.manualbook.ui.fragment.home.views

import androidx.recyclerview.widget.DiffUtil
import com.dudegenuine.domain.Chapter
import com.dudegenuine.domain.Quran
import com.dudegenuine.domain.Search

/**
 * Manual Book created by utifmd on 24/06/21.
 */
class DataComparator(
    private val oldData: List<Chapter>,
    private val newData: List<Chapter>): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldData[oldItemPosition] == newData[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldData[oldItemPosition].id == newData[newItemPosition].id &&
        oldData[oldItemPosition].pages == newData[newItemPosition].pages &&
        oldData[oldItemPosition].nameArabic == newData[newItemPosition].nameArabic &&
        oldData[oldItemPosition].nameComplex == newData[newItemPosition].nameComplex &&
        oldData[oldItemPosition].nameSimple == newData[newItemPosition].nameSimple &&
        oldData[oldItemPosition].versesCount == newData[newItemPosition].versesCount &&
        oldData[oldItemPosition].revelationPlace == newData[newItemPosition].revelationPlace &&
        oldData[oldItemPosition].translatedName == newData[newItemPosition].translatedName
}