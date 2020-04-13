package br.com.quiz.util

import android.R
import android.app.Activity
import android.app.Dialog
import android.view.Window
import android.widget.Button


fun showDialog(activity: Activity) {
    val dialog = Dialog(activity)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(false)
    dialog.setContentView(br.com.quiz.R.layout.layout_pergunta_one)
    val dialogButton = dialog.findViewById(br.com.quiz.R.id.btEntendi) as Button
    dialogButton.setOnClickListener { dialog.dismiss() }
    dialog.show()

}