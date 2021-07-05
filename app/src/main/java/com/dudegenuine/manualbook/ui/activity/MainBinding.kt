package com.dudegenuine.manualbook.ui.activity

import android.content.Intent
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes
import androidx.appcompat.app.AlertDialog
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

    fun AppCompatActivity.popping(view: View, @MenuRes menu: Int, listener: (MenuItem) -> Boolean){
        PopupMenu(view.context, view).apply {
            inflate(menu)
            setOnMenuItemClickListener(listener)
            show()
        }
    }

    fun AppCompatActivity.about(title: String, message: String) {
        AlertDialog.Builder(this).apply {
            setTitle(title)
            setMessage(message)
            create()
            show()
        }
    }

    fun AppCompatActivity.share(title: String, message: String) {
        try {
            startActivity(
                Intent.createChooser(Intent().apply {
                    action = Intent.ACTION_SEND
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TITLE, title)
                    putExtra(Intent.EXTRA_TEXT, message) // data = contentUri
                    flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                }, "Choose One")
            )
        }catch (e: Exception){
            about("Invalid", "${e.message}")
        }
    }
}