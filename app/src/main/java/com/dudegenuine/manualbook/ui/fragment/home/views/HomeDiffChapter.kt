package com.dudegenuine.manualbook.ui.fragment.home.views

import androidx.recyclerview.widget.DiffUtil
import com.dudegenuine.domain.Chapter

/**
 * Manual Book created by utifmd on 24/06/21.
 */
class HomeDiffChapter(
    private val oldChapters: List<Chapter>,
    private val newChapters: List<Chapter>): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldChapters.size

    override fun getNewListSize(): Int = newChapters.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldChapters[oldItemPosition] == newChapters[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldChapters[oldItemPosition].id == newChapters[newItemPosition].id &&
        oldChapters[oldItemPosition].pages == newChapters[newItemPosition].pages &&
        oldChapters[oldItemPosition].nameArabic == newChapters[newItemPosition].nameArabic &&
        oldChapters[oldItemPosition].nameComplex == newChapters[newItemPosition].nameComplex &&
        oldChapters[oldItemPosition].nameSimple == newChapters[newItemPosition].nameSimple &&
        oldChapters[oldItemPosition].versesCount == newChapters[newItemPosition].versesCount &&
        oldChapters[oldItemPosition].revelationPlace == newChapters[newItemPosition].revelationPlace &&
        oldChapters[oldItemPosition].translatedName == newChapters[newItemPosition].translatedName
}