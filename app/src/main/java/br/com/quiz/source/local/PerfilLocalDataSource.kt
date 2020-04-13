package br.com.quiz.source.local

import android.util.Log
import androidx.annotation.VisibleForTesting
import br.com.quiz.source.CallbackResponse
import br.com.quiz.source.PerfilDataSource
import br.com.quiz.source.UsuarioDataSource
import br.com.quiz.source.model.Perfil
import br.com.quiz.source.model.Usuario
import br.com.quiz.util.AppExecutors

class PerfilLocalDataSource(
    val appExecutors: AppExecutors,
    val perfilDao: PerfilDao

) : PerfilDataSource {
    override fun pegarPerfil(callbackResponse: CallbackResponse<Perfil>) {
        appExecutors.diskIO.execute {
            val usuarios = perfilDao.pegarPerfil()
            appExecutors.mainThread.execute {
                if (usuarios.isEmpty()) {
                    callbackResponse.erro()
                } else {
                    Log.e("entrou aquiii ", usuarios[0].scoreTotal.toString())
                    callbackResponse.sucesso(usuarios[0])
                }
            }
        }
    }

    override fun salvarPerfil(perfil: Perfil?) {
        appExecutors.diskIO.execute {
            try {
                perfilDao.deletarPerfil()
                perfilDao.salvarPerfil(perfil)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    override fun deletaPerfil(acao: () -> Unit) {
        appExecutors.diskIO.execute {
            try {
                perfilDao.deletarPerfil()
                appExecutors.mainThread.execute(acao)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        private var INSTANCE: PerfilLocalDataSource? = null

        @JvmStatic
        fun getInstance(
            appExecutors: AppExecutors,
            perfilDao: PerfilDao
        ): PerfilLocalDataSource {
            if (INSTANCE == null) {
                synchronized(PerfilLocalDataSource::javaClass) {
                    INSTANCE = PerfilLocalDataSource(appExecutors, perfilDao)
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }
}