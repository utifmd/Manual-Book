package com.dudegenuine.manualbook.ui.extention

import androidx.recyclerview.widget.DiffUtil
import com.dudegenuine.domain.Chapter
import com.dudegenuine.domain.Quran
import com.dudegenuine.domain.Search

/**
 * Manual Book created by utifmd on 24/06/21.
 */
class DataComparator<T>(
    private val oldData: List<T>,
    private val newData: List<T>): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldData[oldItemPosition] == newData[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        (oldData[oldItemPosition] as Chapter).id == (newData[newItemPosition] as Chapter).id ||
        (oldData[oldItemPosition] as Search).verseId == (newData[newItemPosition] as Search).verseId ||
        (oldData[oldItemPosition] as Quran).id == (newData[newItemPosition] as Quran).id

        /*&& oldData[oldItemPosition].pages == newData[newItemPosition].pages &&
        oldData[oldItemPosition].nameArabic == newData[newItemPosition].nameArabic &&
        oldData[oldItemPosition].nameComplex == newData[newItemPosition].nameComplex &&
        oldData[oldItemPosition].nameSimple == newData[newItemPosition].nameSimple &&
        oldData[oldItemPosition].versesCount == newData[newItemPosition].versesCount &&
        oldData[oldItemPosition].revelationPlace == newData[newItemPosition].revelationPlace &&
        oldData[oldItemPosition].translatedName == newData[newItemPosition].translatedName*/
}