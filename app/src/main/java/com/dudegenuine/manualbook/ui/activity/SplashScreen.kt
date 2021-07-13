package com.dudegenuine.manualbook.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dudegenuine.manualbook.ui.fragment.home.HomeViewModel
import com.dudegenuine.repos.network.Resource

/**
 * Manual Book created by utifmd on 09/07/21.
 */
class SplashScreen: AppCompatActivity() {
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val starting = Intent(this@SplashScreen, MainActivity::class.java)

        /*val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            startActivity( starting ).apply {
                this@SplashScreen.finish()

                Log.d(javaClass.simpleName, "onCreate: applied")
            }
            Log.d(javaClass.simpleName, "onCreate: postDelayed")
        }, 3000)*/


        homeViewModel.chapters.observe(this@SplashScreen, {
            when(it.status){
                Resource.Status.LOADING -> Log.d(javaClass.name, "onCreate: loading..")
                Resource.Status.ERROR -> Log.d(javaClass.name, "onCreate: error ${it.message}")
                else -> startActivity( starting ).apply { finish() }
            }
        })
    }
}