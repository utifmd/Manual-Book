package com.dudegenuine.manualbook.ui.fragment.search.views

import androidx.recyclerview.widget.DiffUtil
import com.dudegenuine.domain.Search

/**
 * Manual Book created by utifmd on 24/06/21.
 */
class DataComparator(
    private val oldData: List<Search>,
    private val newData: List<Search>): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldData[oldItemPosition] == newData[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldData[oldItemPosition].verseId == newData[newItemPosition].verseId &&
        oldData[oldItemPosition].currentPage == newData[newItemPosition].currentPage &&
        oldData[oldItemPosition].nameTrans == newData[newItemPosition].nameTrans &&
        oldData[oldItemPosition].textTrans == newData[newItemPosition].textTrans &&
        oldData[oldItemPosition].textOrig == newData[newItemPosition].textOrig &&
        oldData[oldItemPosition].query == newData[newItemPosition].query &&
        oldData[oldItemPosition].totalPages == newData[newItemPosition].totalPages &&
        oldData[oldItemPosition].totalResults == newData[newItemPosition].totalResults
}