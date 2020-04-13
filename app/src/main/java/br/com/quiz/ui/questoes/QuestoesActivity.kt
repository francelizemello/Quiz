package br.com.quiz.ui.questoes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.quiz.R
import br.com.quiz.source.PerfilRepositorio
import br.com.quiz.source.local.AppDataBase
import br.com.quiz.source.local.PerfilLocalDataSource
import br.com.quiz.ui.splash.NivelFragment
import br.com.quiz.ui.splash.QuestoesFragment
import br.com.quiz.util.AppExecutors
import br.com.quiz.util.NIVEL
import br.com.quiz.util.replaceFragmentInActivity

class QuestoesActivity : AppCompatActivity() {

    private lateinit var presenter: PresenterQuestoes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questoes)

        var data = intent.extras
        val nivel =data!!.getString(NIVEL)

        val sobreFragment = supportFragmentManager.findFragmentById(
            R.id.homeContentFrame
        ) as QuestoesFragment? ?: QuestoesFragment
            .newInstance(nivel!!).also {
                replaceFragmentInActivity(it, R.id.homeContentFrame)
            }
        val localDB = AppDataBase.getInstance(applicationContext)

        val perfilLocl = PerfilLocalDataSource.getInstance(
            AppExecutors(),
            localDB.perfilDao()
        )

        val repositorio = PerfilRepositorio(perfilLocl)
        presenter = PresenterQuestoes(repositorio,sobreFragment)
    }
}
