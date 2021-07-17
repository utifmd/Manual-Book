package com.dudegenuine.manualbook.ui.fragment.quran

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.dudegenuine.domain.Quran
import com.dudegenuine.manualbook.BuildConfig
import com.dudegenuine.manualbook.NavGraphHomeFeatureDirections
import com.dudegenuine.manualbook.databinding.FragmentQuranBinding
import com.dudegenuine.manualbook.ui.activity.MainActivity
import com.dudegenuine.manualbook.ui.extention.*
import com.dudegenuine.manualbook.ui.fragment.quran.media.AudioQuranPlayer
import com.dudegenuine.manualbook.ui.fragment.quran.views.QuranAdapter
import com.dudegenuine.manualbook.ui.fragment.quran.views.StateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlin.LazyThreadSafetyMode.NONE

/**
 * Manual Book created by utifmd on 27/06/21.
 */
class QuranFragment: BaseFragment<FragmentQuranBinding>(),
    QuranAdapter.Listener, AudioQuranPlayer.Listener {
    private val TAG: String = javaClass.simpleName

    private val args: QuranFragmentArgs by navArgs()

    private val vueModel: QuranViewModel by viewModels {
        BaseViewModelFactory(QuranViewModel.QuranFactory, this, arguments)
    }

    private val padLeft: String by lazy(NONE) {
        args.chapter.id.toString().padStart(3, '0')
    }

    private val quranAdapter = QuranAdapter()
    private var player = AudioQuranPlayer()
    private var hasPlayedAt = 0

    override fun bindView(): FragmentQuranBinding =
        FragmentQuranBinding.inflate(layoutInflater)

    override fun bindViewModel(): BaseViewModel = vueModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindInitialListener()
        bindEnterReturnTransition()
        bindExitRenterTransition()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindInitialView()
        observeQuranPaging()
    }

    private fun observeQuranPaging() {
        vueModel.quran.observe(viewLifecycleOwner, {
            lifecycleScope.launch {
                quranAdapter.submitData(it)
            }
        })
    }

    override fun onItemSelected(quran: Quran) =
        vueModel.onItemSelected(quran, args.chapter)

    override fun onAudioSelected(quran: Quran, position: Int) {
        player.playUrl("${BuildConfig.BASE_AUDIO_URL}${quran.audioUrl}").apply {
            hasPlayedAt = position+1
        }
    }

    override fun onEventPlaySelected(view: View) {
        val padRight = hasPlayedAt.toString().padStart(3, '0')

        player.playUrl("${BuildConfig.BASE_AUDIO_URL}AbdulBaset/Mujawwad/mp3/$padLeft$padRight.mp3")
    }

    override fun onAudioCompletion(media: MediaPlayer) {
        media.reset()
        hasPlayedAt += 1
        val padRight = hasPlayedAt.toString().padStart(3, '0')

        if (hasPlayedAt <= args.chapter.versesCount) binding.apply {
            player.playUrl("${BuildConfig.BASE_AUDIO_URL}AbdulBaset/Mujawwad/mp3/$padLeft$padRight.mp3")
            recyclerView.smoothScrollToPosition(hasPlayedAt)
        } else
            vueModel.onAudioPlayed()
    }

    private fun bindInitialView() = binding.apply {
        lifecycleOwner = this@QuranFragment
        viewModel = vueModel

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = quranAdapter.apply {
                setListener(this@QuranFragment)
                withLoadStateHeaderAndFooter(
                    header = StateAdapter(vueModel) { quranAdapter.retry() },
                    footer = StateAdapter(vueModel) { quranAdapter.retry() }
                )
            }

            setHasFixedSize(true)
        }
    }

    private fun bindInitialListener() {
        vueModel.quranListener = this@QuranFragment
        player.setListener( this@QuranFragment )
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}