package br.com.quiz.util

import android.R
import android.content.Context
import android.media.MediaPlayer
import android.view.View
import android.widget.Toast

var player: MediaPlayer? = null

fun play(context: Context) {
    if (player == null) {
        player = MediaPlayer.create(context, br.com.quiz.R.raw.song)
        player!!.setOnCompletionListener { stopPlayer() }
    }
    player!!.start()
}

fun stopPlayer() {
    if (player != null) {
        player!!.release()
        player = null
    }
}
