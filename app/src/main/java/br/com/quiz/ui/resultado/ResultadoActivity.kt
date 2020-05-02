package br.com.quiz.ui.resultado

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.quiz.R
import br.com.quiz.ui.home.HomeActivity
import br.com.quiz.ui.questoes.QuestoesActivity
import br.com.quiz.util.NIVEL
import br.com.quiz.util.PONTOS
import br.com.quiz.util.replaceFragmentInActivity


class ResultadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var data = intent.extras
        val nivel =data!!.getString(NIVEL)
        val pontos = data!!.getString(PONTOS)

        val sobreFragment = supportFragmentManager.findFragmentById(
            R.id.homeContentFrame
        ) as ResultadoFragment? ?: ResultadoFragment
            .newInstance(nivel!!, pontos!!).also {
                replaceFragmentInActivity(it, R.id.homeContentFrame)
            }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    override fun onBackPressed() {
        super.onBackPressed()
        var intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}
