package br.com.quiz.ui.nivel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.quiz.R
import br.com.quiz.ui.splash.NivelFragment
import br.com.quiz.util.NIVEL
import br.com.quiz.util.replaceFragmentInActivity

class NivelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nivel)

        var data = intent.extras
        val nivel =data!!.getString(NIVEL)

        val sobreFragment = supportFragmentManager.findFragmentById(
            R.id.homeContentFrame
        ) as NivelFragment? ?: NivelFragment
            .newInstance(nivel!!).also {
                replaceFragmentInActivity(it, R.id.homeContentFrame)
            }
    }
}
