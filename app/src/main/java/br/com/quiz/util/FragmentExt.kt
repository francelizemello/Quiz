package br.com.quiz.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

const val TAG_DIALOG = "dialog"

fun Fragment.pegaFragmentTranscation(): FragmentTransaction {
    val ft = fragmentManager!!.beginTransaction()
    val prev = fragmentManager!!.findFragmentByTag(TAG_DIALOG)
    if (prev != null) {
        ft.remove(prev)
    }
    ft.addToBackStack(null)
    return ft
}