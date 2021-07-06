package com.dudegenuine.manualbook.ui.fragment.quran

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.dudegenuine.domain.Quran
import com.dudegenuine.manualbook.NavGraphHomeFeatureDirections
import com.dudegenuine.manualbook.databinding.FragmentQuranBinding
import com.dudegenuine.manualbook.ui.extention.BaseFragment
import com.dudegenuine.manualbook.ui.extention.BaseViewModel
import com.dudegenuine.manualbook.ui.extention.bindEnterReturnTransition
import com.dudegenuine.manualbook.ui.extention.bindExitRenterTransition
import com.dudegenuine.manualbook.ui.fragment.quran.views.QuranAdapter
import com.dudegenuine.manualbook.ui.fragment.quran.views.StateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.LazyThreadSafetyMode.NONE
import java.io.IOException

/**
 * Manual Book created by utifmd on 27/06/21.
 */

class QuranFragment: BaseFragment<FragmentQuranBinding>(), QuranAdapter.Listener {
    private val TAG: String = javaClass.simpleName

    private val args: QuranFragmentArgs by navArgs()
    private val vueModel: QuranViewModel by viewModels()

    private val chapterId: Int by lazy(NONE){ args.chapter.id }
    private val pageStart: Int by lazy(NONE){ args.chapter.pages[0] }
    private val pageFinish: Int by lazy(NONE){ args.chapter.pages[1] }

    private val player: MediaPlayer = MediaPlayer()
    private var hasPlayedAt = 0
    private var hasPausedAt = 0

    override fun bindView(): FragmentQuranBinding =
        FragmentQuranBinding.inflate(layoutInflater)

    override fun bindViewModel(): BaseViewModel = vueModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val quranAdapter = QuranAdapter(vueModel)

        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = quranAdapter.apply {
                    setListener(this@QuranFragment)
                }

                setHasFixedSize(true)
            }
        }

        lifecycleScope.launch {
            vueModel.quran(args.chapter.id, pageStart, pageFinish).collectLatest {
                quranAdapter.apply {
                    submitData(it)
                    withLoadStateFooter(
                        footer = StateAdapter { quranAdapter.retry() } // header = StateAdapter { quranAdapter.retry() },
                    )
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindEnterReturnTransition()
        bindExitRenterTransition()

        player.apply {
            setAudioAttributes(AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build())

            setOnCompletionListener {
                reset()
                hasPausedAt = 0
                hasPlayedAt += 1

                val padLeft = chapterId.toString().padStart(3, '0')
                val padRight = hasPlayedAt.toString().padStart(3, '0')

                if (hasPlayedAt <= args.chapter.versesCount){
                    playAudio("https://verses.quran.com/AbdulBaset/Mujawwad/mp3/$padLeft$padRight.mp3")
                    Log.d(TAG, "onCreate: padLeft $padLeft padRight $padRight")
                    binding.recyclerView.smoothScrollToPosition(hasPlayedAt)
                }else Log.d(TAG, "onCreate: finish.")
            }
        }
    }

    override fun onItemSelected(quran: Quran) {
        Log.d(TAG, "onItemSelected: ${quran.verseKey}")
        findNavController().navigate(
            NavGraphHomeFeatureDirections.actionGlobalToVerse( quran, args.chapter )
        )
    }

    override fun onPlayAudioSelected(quran: Quran, position: Int) {
        playAudio("https://verses.quran.com/${quran.audioUrl}").apply {
            hasPlayedAt = position+1

            Log.d(TAG, "onPlayAudioSelected: hasPlayedAt $hasPlayedAt verses count ${args.chapter.versesCount}")
        }
    }

    private fun playAudio(audioUrl: String) {
        if (player.isPlaying){
            hasPausedAt = player.currentPosition
            player.pause()
        }else{
            try {
                if (hasPausedAt != 0) player.apply {
                    seekTo(hasPausedAt)
                    start()
                }else player.apply {
                    setDataSource(audioUrl)
                    prepare()
                    start()
                }
            }catch (e:IOException){
                Log.d(TAG, "io: ${e.localizedMessage}")
            }catch (e:IllegalStateException){
                Log.d(TAG, "state: ${e.localizedMessage}")
            }catch (e:IllegalArgumentException){
                Log.d(TAG, "argue: ${e.localizedMessage}")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        player.release()
    }
}