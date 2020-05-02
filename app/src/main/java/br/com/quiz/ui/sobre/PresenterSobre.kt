package br.com.quiz.ui.sobre

import android.content.Context
import android.util.Log
import br.com.quiz.source.CallbackResponse
import br.com.quiz.source.UsuarioRepositorio
import br.com.quiz.source.model.Usuario

class PresenterSobre(
    var repositorio: UsuarioRepositorio,
    val fragment: ContratoSobre.View,
    val context: Context
) : ContratoSobre.Presenter {

    init {
        fragment.presenter = this
    }

    override fun salvarUsuario(usuario: Usuario?) {
        repositorio.salvarUsuario(usuario)
    }

    override fun start() {
        repositorio.pegarUsuario(object : CallbackResponse<Usuario> {
            override fun sucesso(response: Usuario) {
              Log.e("tem algo ", "sucesso")
            }

            override fun erro() {
                fragment.exibirDialog()
            }
        })
    }

}