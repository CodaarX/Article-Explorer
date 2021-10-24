package com.decagon.edconnect.utils

import android.content.Context
import android.media.AudioManager

import android.media.SoundPool

import android.media.MediaPlayer




object AudioPlayer {
    var mediaPlayer: MediaPlayer? = null
    private var soundPool: SoundPool? = null
    var isplayingAudio = false
    fun playAudio(context: Context?, id: Int) {
        mediaPlayer = MediaPlayer.create(context, id)
        soundPool = SoundPool(4, AudioManager.STREAM_MUSIC, 100)
    }

    fun stopAudio() {
        isplayingAudio = false
        mediaPlayer?.stop()
    }

    fun pauseAudio() {
        mediaPlayer?.pause()
    }
}
