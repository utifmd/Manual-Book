package com.dudegenuine.manualbook.ui.activity

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
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
import com.dudegenuine.manualbook.ui.fragment.sheet.BottomSheetFragment
import kotlin.LazyThreadSafetyMode.NONE

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener,
        (MenuItem) -> Boolean {
    private val TAG: String = javaClass.simpleName
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val sheetFragment: BottomSheetFragment by lazy(NONE) {
        supportFragmentManager.findFragmentById(R.id.bottom_sheet_fragment) as BottomSheetFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bindFragmentTransition()

        binding.apply {
            lifecycleOwner = this@MainActivity

            sheetFragment.close()
        }

        navController = findNavController(R.id.nav_host_fragment).apply { // setupActionBarWithNavController(navController, appBarConfiguration)
            appBarConfiguration = AppBarConfiguration(this.graph)

            addOnDestinationChangedListener(this@MainActivity)
        }
    }

    private val homeDestiny: (Bundle?) -> Unit = { _ ->
        setBottomAppBar(binding, R.drawable.ic_baseline_search_24, getString(R.string.app_name)) {
            navController.navigate(NavGraphHomeFeatureDirections.actionGlobalToSearch())
        }

        binding.apply {
            bottomAppBarContentContainer.setOnClickListener {
                popping(it, R.menu.bottom_app_bar_menu_home, this@MainActivity)
            }

            sheetFragment.close()
        }
    }

    private val quranDestiny: (Bundle?) -> Unit = { arguments ->
        val chapter = arguments?.getSerializable("chapter") as Chapter
        /*val mediaPlayer: MediaPlayer = MediaPlayer().apply {
            setDataSource("https://download.quranicaudio.com/quran/abdullaah_3awwaad_al-juhaynee//002.mp3")
            setAudioAttributes(AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build())
            prepare()
        }*/

        setBottomAppBar(binding, R.drawable.ic_baseline_volume_up_24, chapter.nameComplex){
            Log.d(TAG, "media player triggered")
            /*if(!mediaPlayer.isPlaying) mediaPlayer.apply {
                start()
                binding.fab.hide()
            } else mediaPlayer.apply {
                pause()
                binding.fab.show()
            }*/
        }

        binding.apply {
            bottomAppBarContentContainer.setOnClickListener {
                sheetFragment.toggle()
            }

            sheetFragment.openData(chapter)
        }
    }

    private val verseDestiny: (Bundle?) -> Unit = { arguments ->
        // val chapter = arguments?.getSerializable("chapter") as Chapter
        val quran = arguments?.getSerializable("quran") as Quran

        setBottomAppBar(binding, null, quran.verseKey) { }

        binding.apply {
            bottomAppBarContentContainer.setOnClickListener {
                Log.d(TAG, "verseDestiny")
            }

            sheetFragment.close()
        }
    }

    private val searchDestiny: (Bundle?) -> Unit = { _ ->
        setBottomAppBar(binding, null, getString(R.string.app_name)) { }
        binding.apply {
            bottomAppBarContentContainer.setOnClickListener {
                Log.d(TAG, "searchDestiny")
            }

            sheetFragment.close()
        }
    }

    private fun detailDestiny(arguments: Bundle?) {
        val chapter = arguments?.getSerializable("chapter") as Chapter

        setBottomAppBar(binding, R.drawable.ic_baseline_arrow_forward_24, chapter.nameSimple) {
            navController.navigate(NavGraphHomeFeatureDirections.actionGlobalToQuran(chapter))
        }

        binding.apply {
            bottomAppBarContentContainer.setOnClickListener {
                Log.d(TAG, "detailDestiny")
            }

            sheetFragment.close()
        }
    }

    override fun onDestinationChanged(
        controller: NavController, destination: NavDestination, arguments: Bundle? ) {

        when (destination.id) {
            R.id.homeFragment -> homeDestiny(arguments)
            R.id.detailFragment -> detailDestiny(arguments)
            R.id.quranFragment -> quranDestiny(arguments)
            R.id.verseFragment -> verseDestiny(arguments)
            R.id.searchFragment -> searchDestiny(arguments)
        }
    }

    override fun onNavigateUp(): Boolean =
        navController.navigateUp(appBarConfiguration) || super.onNavigateUp()

    override fun invoke(item: MenuItem): Boolean {
        val title = "${resources.getString(R.string.app_name)} ${BuildConfig.VERSION_NAME}"
        when(item.itemId){
            R.id.menu_item_about -> about(title, "developed by utifmd@gmail.com")
            R.id.menu_item_share -> share(title, "https://utifmd.github.io/portfolio/")
        }

        return true
    }
}