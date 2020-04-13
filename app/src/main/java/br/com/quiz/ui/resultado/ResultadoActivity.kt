package br.com.quiz.ui.resultado

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.quiz.R
import br.com.quiz.util.replaceFragmentInActivity


class ResultadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)


        val sobreFragment = supportFragmentManager.findFragmentById(
            R.id.homeContentFrame
        ) as ResultadoFragment? ?: ResultadoFragment
            .newInstance().also {
                replaceFragmentInActivity(it, R.id.homeContentFrame)
            }
    }
}
