package com.dudegenuine.manualbook.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.navigateUp
import com.dudegenuine.domain.Chapter
import com.dudegenuine.domain.Quran
import com.dudegenuine.manualbook.BuildConfig
import com.dudegenuine.manualbook.NavGraphHomeFeatureDirections
import com.dudegenuine.manualbook.R
import com.dudegenuine.manualbook.databinding.ActivityMainBinding
import com.dudegenuine.manualbook.ui.activity.MainBinding.about
import com.dudegenuine.manualbook.ui.activity.MainBinding.bindFragmentTransition
import com.dudegenuine.manualbook.ui.activity.MainBinding.popping
import com.dudegenuine.manualbook.ui.activity.MainBinding.setBottomAppBar
import com.dudegenuine.manualbook.ui.activity.MainBinding.share
import com.dudegenuine.manualbook.ui.extention.BaseActivity
import com.dudegenuine.manualbook.ui.extention.BaseViewModel
import com.dudegenuine.manualbook.ui.fragment.quran.QuranViewModel
import com.dudegenuine.manualbook.ui.fragment.quran.views.QuranAdapter
import com.dudegenuine.manualbook.ui.fragment.sheet.BottomSheetFragment

class MainActivity : BaseActivity<ActivityMainBinding>(),
    NavController.OnDestinationChangedListener, QuranAdapter.Listener.Callback {
    private val TAG: String = javaClass.simpleName

    private val vueModel: MainViewModel by viewModels()
    private val quranVueModel: QuranViewModel by viewModels()

    override fun bindView(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun bindViewModel(): BaseViewModel = vueModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindInitialViews()
        bindFragmentTransition()
        bindInitialNavigation()
        bindInitialListener()
    }

    private val onHomeDestiny: (Bundle?) -> Unit = { _ ->
        binding.apply {
            setBottomAppBar(this,
                R.drawable.ic_baseline_search_24,
                getString(R.string.app_name)
            ) { vueModel.navigateBy() }
            bottomAppBarContentContainer.setOnClickListener{ onAppbarSelected(it) }
            sheetFragment.close()
        }
    }

    private val onQuranDestiny: (Bundle?) -> Unit = { arguments ->
        val chapter = arguments?.getSerializable("chapter") as Chapter
        var isPaused = true

        binding.apply {
            setBottomAppBar(this, R.drawable.ic_baseline_play_circle_24 , chapter.nameComplex) {
                quranVueModel.playButtonSelect(it)
                sheetFragment.close()

                if(isPaused) fab.apply {
                    setImageResource(R.drawable.ic_baseline_pause_circle_24)
                    isPaused = false
                }else fab.apply {
                    setImageResource(R.drawable.ic_baseline_play_circle_24)
                    isPaused = true
                }
            }
            bottomAppBarContentContainer.setOnClickListener { sheetFragment.toggle() }
            sheetFragment.openData(chapter)
        }
    }

    private val onVerseDestiny: (Bundle?) -> Unit = { arguments ->
        val quran = arguments?.getSerializable("quran") as Quran // val chapter = arguments?.getSerializable("chapter") as Chapter

        binding.apply {
            setBottomAppBar(this, null, quran.verseKey) { }
            bottomAppBarContentContainer.setOnClickListener { onAppbarSelected(it) }
            sheetFragment.close()
        }
    }

    private val onSearchDestiny: (Bundle?) -> Unit = { _ ->
        binding.apply {
            setBottomAppBar(this, null, getString(R.string.app_name)) { }
            bottomAppBarContentContainer.setOnClickListener { onAppbarSelected(it) }
            sheetFragment.close()
        }
    }

    // TODO: 07/07/21 check the chapter state inconsistency 
    private val onDetailDestiny: (Bundle?) -> Unit = { arguments ->
        val chapter = arguments?.getSerializable("chapter") as Chapter?

        chapter?.let { binding.apply {
            setBottomAppBar (this,
                R.drawable.ic_baseline_arrow_forward_24,
                chapter.nameSimple
            ) { vueModel.navigateBy(chapter) }

            bottomAppBarContentContainer.setOnClickListener { onAppbarSelected(it) }
            sheetFragment.close()
        }}
    }

    override fun onDestinationChanged(
        controller: NavController, destination: NavDestination, arguments: Bundle? ) {

        when (destination.id) {
            R.id.homeFragment -> onHomeDestiny(arguments)
            R.id.detailFragment -> onDetailDestiny(arguments)
            R.id.quranFragment -> onQuranDestiny(arguments)
            R.id.verseFragment -> onVerseDestiny(arguments)
            R.id.searchFragment -> onSearchDestiny(arguments)
        }
    }

    private fun onAppbarSelected(view: View){
        popping(view, R.menu.bottom_app_bar_menu_home) { itemMenu ->
            val title = "${resources.getString(R.string.app_name)} ${BuildConfig.VERSION_NAME}"
            when(itemMenu.itemId){
                R.id.menu_item_about -> about(title, "developed by utifmd@gmail.com")
                R.id.menu_item_share -> share(title, "https://utifmd.github.io/portfolio/")
            }
            true
        }
    }

    override fun onAudioPlayed() {
        Log.d(TAG, "onAudioPlayed: callback triggered")
        binding.fab.setImageResource(R.drawable.ic_baseline_play_circle_24)
    }

    override fun onNavigateUp(): Boolean =
        navController.navigateUp(appBarConfiguration) || super.onNavigateUp()

    private fun bindInitialNavigation() =
        navController.addOnDestinationChangedListener (this@MainActivity )

    private fun bindInitialViews() = binding.apply {
        lifecycleOwner = this@MainActivity

        sheetFragment.close()
    }

    private fun bindInitialListener() {
        quranVueModel.quranCallback = this@MainActivity
    }
}