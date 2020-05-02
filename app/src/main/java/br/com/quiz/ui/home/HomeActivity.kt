package br.com.quiz.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.quiz.R
import br.com.quiz.source.PerfilRepositorio
import br.com.quiz.source.local.AppDataBase
import br.com.quiz.source.local.PerfilLocalDataSource
import br.com.quiz.ui.questoes.PresenterQuestoes
import br.com.quiz.ui.splash.HomeFragment
import br.com.quiz.util.AppExecutors
import br.com.quiz.util.replaceFragmentInActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var presenter: PresenterHome

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val homeFragment = supportFragmentManager.findFragmentById(
            R.id.homeContentFrame
        ) as HomeFragment? ?: HomeFragment
            .newInstance().also {
                replaceFragmentInActivity(it, R.id.homeContentFrame)
            }

        val localDB = AppDataBase.getInstance(applicationContext)

        val perfilLocl = PerfilLocalDataSource.getInstance(
            AppExecutors(),
            localDB.perfilDao()
        )

        val repositorio = PerfilRepositorio(perfilLocl)
        presenter = PresenterHome(repositorio, homeFragment, this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()

    }

}
