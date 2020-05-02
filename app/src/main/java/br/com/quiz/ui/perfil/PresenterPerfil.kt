package br.com.quiz.ui.perfil

import android.content.Context
import android.util.Log
import br.com.quiz.source.CallbackResponse
import br.com.quiz.source.PerfilRepositorio
import br.com.quiz.source.UsuarioRepositorio
import br.com.quiz.source.model.Perfil
import br.com.quiz.source.model.Usuario

class PresenterPerfil(
    var repositorio: UsuarioRepositorio,
    var repositorioPerfil: PerfilRepositorio,
    val fragment: ContratoPerfil.View,
    val context: Context
) : ContratoPerfil.Presenter {

    init {
        fragment.presenter = this
    }

    override fun getPerfil() {
        repositorioPerfil.pegarPerfil(object : CallbackResponse<Perfil> {
            override fun sucesso(response: Perfil) {
                fragment.exibirDadosPerfil(response)
            }

            override fun erro() {
            }
        })
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