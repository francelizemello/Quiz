package br.com.quiz.util

import android.content.Context
object PreferencesUtil {

    @JvmStatic
    fun savePreff(context: Context, key: String, value: String) {
        val sharedPref = context.getSharedPreferences("QUIZZ", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(key, value)
            apply()
            commit()
        }
    }

    @JvmStatic
    fun getPreff(context: Context?, key: String, default_value: String): String {
        context?.let {
            val sharedPref = context.getSharedPreferences("QUIZZ", Context.MODE_PRIVATE) ?: return ""
            return with(sharedPref) {
                getString(key, default_value)
            }!!
        } ?: run {

            return ""
        }

    }

    @JvmStatic
    fun clearPreff(context: Context, key: String, value: String) {
        val sharedPref = context.getSharedPreferences("QUIZZ", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            clear()
            apply()
            commit()
        }
    }

    @JvmStatic
    fun savePref(context: Context, key: String, value: String) {
        val sharedPref = context.getSharedPreferences("QUIZ", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(key, value)
            apply()
            commit()
        }
    }

    @JvmStatic
    fun getPref(context: Context?, key: String, default_value: String): String {
        context?.let {
            val sharedPref = context.getSharedPreferences("QUIZ", Context.MODE_PRIVATE) ?: return ""
            return with(sharedPref) {
                getString(key, default_value)
            }!!
        } ?: run {

            return ""
        }

    }

    @JvmStatic
    fun clearPref(context: Context, key: String, value: String) {
        val sharedPref = context.getSharedPreferences("QUIZ", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            clear()
            apply()
            commit()
        }
    }

}