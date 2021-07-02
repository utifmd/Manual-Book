package com.dudegenuine.manualbook.ui.activity

import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import com.dudegenuine.manualbook.R
import com.dudegenuine.manualbook.databinding.ActivityMainBinding
import com.dudegenuine.manualbook.ui.extention.bindExitRenterTransition

/**
 * Manual Book created by utifmd on 25/06/21.
 */
object MainBinding {
    fun AppCompatActivity.setBottomAppBar(
        binding: ActivityMainBinding,
        @DrawableRes drawableRes: Int? = null,
        titleRes: String, fabListener: (View) -> Unit) {

        binding.run {
            drawableRes?.let {
                fab.apply {
                    setImageResource(drawableRes)
                    setOnClickListener(fabListener)
                    show()
                }
            } ?: fab.hide()

            bottomAppBarTitle.apply {
                text = titleRes
                visibility = View.VISIBLE
            }
            bottomAppBar.apply {
                visibility = View.VISIBLE
                // replaceMenu(menuRes)
                performShow()
            }
        } // fab.contentDescription = getString(R.string.fab_compose_email_content_description) /**/ // setImageState(intArrayOf(-android.R.attr.state_activated), true)
    }
    fun AppCompatActivity.bindFragmentTransition(){
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            ?.childFragmentManager?.fragments?.first()?.apply {
                bindExitRenterTransition()
            }
    }
}