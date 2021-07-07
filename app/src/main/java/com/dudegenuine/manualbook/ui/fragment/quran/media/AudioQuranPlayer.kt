package com.dudegenuine.manualbook.ui.fragment.quran.media

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import java.io.File
import java.io.FileDescriptor
import java.io.FileInputStream
import java.io.IOException

/**
 * Manual Book created by utifmd on 07/07/21.
 */
class AudioQuranPlayer: MediaPlayer() {
    private val TAG: String = javaClass.simpleName
    private lateinit var listener: Listener

    private var isPaused: Boolean = false

    init {
        setAudioAttributes(
            AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build())
        setOnCompletionListener { listener.onAudioCompletion(it) }
        setOnPreparedListener { it.start() }
    }

    fun playUrl(url: String) {
        if (isPaused){
            seekTo(currentPosition)
            start()
            isPaused = false
        }else{
            try {
                if (isPlaying){
                    pause()
                    isPaused = true
                }else{
                    setDataSource(url)
                    prepareAsync()
                }
            }catch (e: IOException){
                Log.d(TAG, "io: ${e.localizedMessage}")
            }catch (e:IllegalStateException){
                Log.d(TAG, "state: ${e.localizedMessage}")
            }catch (e:IllegalArgumentException){
                Log.d(TAG, "argue: ${e.localizedMessage}")
            }
        }
    }

    fun playPath(path: String) {
        val file = File(path)
        if (file.exists()) {
            try {
                val fis = FileInputStream(file)
                val fileD: FileDescriptor = fis.getFD()

                setDataSource(fileD)
                prepare()
                start()
            }catch (e: IOException){
                Log.d(TAG, "io: ${e.localizedMessage}")
            }catch (e:IllegalStateException){
                Log.d(TAG, "state: ${e.localizedMessage}")
            }catch (e:IllegalArgumentException){
                Log.d(TAG, "argue: ${e.localizedMessage}")
            }
        }
    }

    fun setListener(listener: Listener){
        this.listener = listener
    }

    interface Listener{
        fun onAudioCompletion(media: MediaPlayer)
    }
}