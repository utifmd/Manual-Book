package com.dudegenuine.manualbook.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.dudegenuine.manualbook.R
import com.dudegenuine.manualbook.ui.fragment.home.HomeViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main){
    private val TAG: String = javaClass.simpleName
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadChapter()
    }
}