package com.dudegenuine.manualbook.ui.extention

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.dudegenuine.local.model.common.NavState

/**
 * Manual Book created by utifmd on 18/06/21.
 */
abstract class BaseFragment<VB: ViewBinding>: Fragment(){
    private lateinit var _binding: VB // ? = null
    val binding get() = _binding

    /*private lateinit var _viewModel: ViewModel // ? = null
    val viewModel get() = _viewModel*/

    abstract fun bindView(): VB

    abstract fun bindViewModel(): BaseViewModel?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        _binding = bindView()
        // _viewModel = bindViewModel()

        bindViewModel()?.let {

            setUpVueModel(it)
            setUpSnackPop(this, it.snackPopError, Snackbar.LENGTH_LONG)
        }

        return binding.root
    }

    private fun setUpVueModel(viewModel: BaseViewModel?) {
        viewModel?.navigation?.observe(viewLifecycleOwner, {
            it?.getContentIfNotHandled()?.let { command ->
                when(command){
                    is NavState.TO ->
                        command.extra?.let { isExtra ->
                            findNavController().navigate(command.direction,
                                isExtra
                            )
                        }

                    is NavState.BACK ->
                        findNavController().navigateUp()
                }
            }
        })
    }

    /*open fun bindExtras(): FragmentNavigator.Extras =
        FragmentNavigatorExtras()*/

    /*public void restorePreviousState(){
    // getting recyclerview position
    mListState = mSavedInstanceState.getParcelable(SAVED_RECYCLER_VIEW_STATUS_ID);
    // getting recyclerview items
    mDataset = mSavedInstanceState.getParcelableArrayList(SAVED_RECYCLER_VIEW_DATASET_ID);
    // Restoring adapter items
    mAdapter.setItems(mDataset);
    // Restoring recycler view position
    mRvMedia.getLayoutManager().onRestoreInstanceState(mListState);
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        binding.apply {
            val parcelize = recyclerView.layoutManager?.onSaveInstanceState()

            outState.apply {
                putParcelable(TAG_LIST_POSITION, parcelize)
                putParcelableArrayList()
            }
        }
    }*/
}