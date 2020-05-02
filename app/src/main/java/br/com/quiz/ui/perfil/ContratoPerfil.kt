package br.com.quiz.ui.perfil

import br.com.quiz.source.model.Usuario
import br.com.quiz.util.BasePresenter
import br.com.quiz.util.BaseView

class ContratoPerfil {
    interface View : BaseView<ContratoPerfil.Presenter> {
        fun exibirDadosUsuario(usuario: Usuario)
    }
    interface Presenter : BasePresenter {
    }
}