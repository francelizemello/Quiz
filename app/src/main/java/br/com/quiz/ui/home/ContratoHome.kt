package br.com.quiz.ui.home

import br.com.quiz.source.model.Perfil
import br.com.quiz.util.BasePresenter
import br.com.quiz.util.BaseView

interface ContratoHome {
    interface View : BaseView<ContratoHome.Presenter> {
        fun novoJogo ()
        fun ativarBotaoNovoJogo (perfil: Perfil)
        fun ativarCard (perfil: Perfil)
        fun irPerfil ()
        fun verificarNivel (string: String)
        fun irNivel (string: String)
    }
    interface Presenter : BasePresenter {
        fun deletarPerfil ()

    }
}