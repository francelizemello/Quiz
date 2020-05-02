package br.com.quiz.ui.sobre

import br.com.quiz.source.model.Usuario
import br.com.quiz.util.BasePresenter
import br.com.quiz.util.BaseView

class ContratoSobre {
    interface View : BaseView<ContratoSobre.Presenter> {
        fun exibirDialog()
        fun isExibirDialog():Int
    }
    interface Presenter : BasePresenter {
        fun salvarUsuario(usuario: Usuario?)
    }
}