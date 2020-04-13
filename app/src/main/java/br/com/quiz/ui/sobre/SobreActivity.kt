package br.com.quiz.ui.sobre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.quiz.R
import br.com.quiz.ui.splash.SobreFragment
import br.com.quiz.ui.splash.SplashFragment
import br.com.quiz.util.replaceFragmentInActivity

class SobreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sobre)

        val sobreFragment = supportFragmentManager.findFragmentById(
            R.id.homeContentFrame
        ) as SobreFragment? ?: SobreFragment
            .newInstance().also {
                replaceFragmentInActivity(it, R.id.homeContentFrame)
            }
        }
}
