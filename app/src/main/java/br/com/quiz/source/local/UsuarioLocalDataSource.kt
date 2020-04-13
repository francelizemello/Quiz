package br.com.quiz.source.local

import android.util.Log
import androidx.annotation.VisibleForTesting
import br.com.quiz.source.CallbackResponse
import br.com.quiz.source.UsuarioDataSource
import br.com.quiz.source.model.Usuario
import br.com.quiz.util.AppExecutors

class UsuarioLocalDataSource(
    val appExecutors: AppExecutors,
    val usuarioDao: UsuarioDao

) : UsuarioDataSource {
    override fun pegarUsuario(callbackResponse: CallbackResponse<Usuario>) {
        appExecutors.diskIO.execute {
            val usuarios = usuarioDao.pegarUsuario()
            appExecutors.mainThread.execute {
                if (usuarios.isEmpty()) {
                    callbackResponse.erro()
                } else {
                    Log.e("entrou aquiii ", usuarios[0].nome)
                    callbackResponse.sucesso(usuarios[0])
                }
            }
        }
    }

    override fun salvarUsuario(usuario: Usuario?, acao: () -> Unit) {
        appExecutors.diskIO.execute {
            try {
                usuarioDao.deletarUsuario()
                usuarioDao.savarUsuario(usuario)
                appExecutors.mainThread.execute(acao)

            } catch (e: Exception) {
                e.printStackTrace()
                appExecutors.mainThread.execute(acao)
            }
        }
    }


    override fun deletaUsuario(acao: () -> Unit) {
        appExecutors.diskIO.execute {
            try {
                usuarioDao.deletarUsuario()
                appExecutors.mainThread.execute(acao)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        private var INSTANCE: UsuarioLocalDataSource? = null

        @JvmStatic
        fun getInstance(
            appExecutors: AppExecutors,
            usuarioDao: UsuarioDao
        ): UsuarioLocalDataSource {
            if (INSTANCE == null) {
                synchronized(UsuarioLocalDataSource::javaClass) {
                    INSTANCE = UsuarioLocalDataSource(appExecutors, usuarioDao)
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