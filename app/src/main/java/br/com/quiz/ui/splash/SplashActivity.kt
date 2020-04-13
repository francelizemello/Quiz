package br.com.quiz.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.quiz.R
import br.com.quiz.util.replaceFragmentInActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        var data = intent.extras

        val splashFragment = supportFragmentManager.findFragmentById(
            R.id.homeContentFrame
        ) as SplashFragment? ?: SplashFragment
            .newInstance().also {
                replaceFragmentInActivity(it, R.id.homeContentFrame)
            }

    }

}