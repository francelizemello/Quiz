package br.com.quiz.ui.perfil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.quiz.R
import br.com.quiz.source.UsuarioRepositorio
import br.com.quiz.source.local.AppDataBase
import br.com.quiz.source.local.UsuarioLocalDataSource
import br.com.quiz.ui.home.HomeActivity
import br.com.quiz.ui.sobre.PerfilFragment
import br.com.quiz.ui.sobre.PresenterSobre
import br.com.quiz.ui.sobre.SobreFragment
import br.com.quiz.util.AppExecutors
import br.com.quiz.util.replaceFragmentInActivity

class PerfilActivity : AppCompatActivity() {

    private lateinit var presenter: PresenterPerfil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val sobreFragment = supportFragmentManager.findFragmentById(
            R.id.homeContentFrame
        ) as PerfilFragment? ?: PerfilFragment
            .newInstance().also {
                replaceFragmentInActivity(it, R.id.homeContentFrame)
            }

        val localDB = AppDataBase.getInstance(applicationContext)

        val usuarioLocal = UsuarioLocalDataSource.getInstance(
            AppExecutors(),
            localDB.usuarioDao()
        )

        val repositorio = UsuarioRepositorio(usuarioLocal)
        presenter = PresenterPerfil(repositorio,sobreFragment,this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        var intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}
