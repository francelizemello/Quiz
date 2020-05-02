package br.com.quiz.ui.perfil

import android.content.Context
import br.com.quiz.source.CallbackResponse
import br.com.quiz.source.UsuarioRepositorio
import br.com.quiz.source.model.Usuario

class PresenterPerfil (
    var repositorio: UsuarioRepositorio,
    val fragment: ContratoPerfil.View,
    val context: Context
) : ContratoPerfil.Presenter {

    init {
        fragment.presenter = this
    }

    override fun start() {
        repositorio.pegarUsuario(object : CallbackResponse<Usuario> {
            override fun sucesso(response: Usuario) {
                fragment.exibirDadosUsuario(response)
            }
            override fun erro() {
            }
        })
    }


}