package br.com.quiz.ui.sobre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.quiz.R
import br.com.quiz.source.PerfilRepositorio
import br.com.quiz.source.UsuarioRepositorio
import br.com.quiz.source.local.AppDataBase
import br.com.quiz.source.local.PerfilLocalDataSource
import br.com.quiz.source.local.UsuarioLocalDataSource
import br.com.quiz.ui.questoes.PresenterQuestoes
import br.com.quiz.util.AppExecutors
import br.com.quiz.util.replaceFragmentInActivity

class SobreActivity : AppCompatActivity() {
    private lateinit var presenter: PresenterSobre

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sobre)

        val sobreFragment = supportFragmentManager.findFragmentById(
            R.id.homeContentFrame
        ) as SobreFragment? ?: SobreFragment
            .newInstance().also {
                replaceFragmentInActivity(it, R.id.homeContentFrame)
            }

        val localDB = AppDataBase.getInstance(applicationContext)

        val usuarioLocal = UsuarioLocalDataSource.getInstance(
            AppExecutors(),
            localDB.usuarioDao()
        )

        val repositorio = UsuarioRepositorio(usuarioLocal)
        presenter = PresenterSobre(repositorio,sobreFragment,this)
        }
}
