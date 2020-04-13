package br.com.quiz.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.quiz.R
import br.com.quiz.ui.splash.HomeFragment
import br.com.quiz.ui.splash.SobreFragment
import br.com.quiz.util.replaceFragmentInActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val homeFragment = supportFragmentManager.findFragmentById(
            R.id.homeContentFrame
        ) as HomeFragment? ?: HomeFragment
            .newInstance().also {
                replaceFragmentInActivity(it, R.id.homeContentFrame)
            }
    }

}
