package br.com.quiz.ui.questoes

import br.com.quiz.source.model.Perfil
import br.com.quiz.util.BasePresenter
import br.com.quiz.util.BaseView

class ContratoQuestoes {
    interface View : BaseView<Presenter> {
        fun responderPergunta()
        fun irProximaPergunta()
        fun exibirEfeitoQuestaoCerta()
        fun exibirEfeitoQuestaoErrada()
        fun isFimJogo() : Int
        fun carregarQuestao(int: Int)
        fun showDialogAviso()
        fun irTelaResultado()
    }

    interface Presenter : BasePresenter {
        fun salvarPerfil(perfil: Perfil?)
    }
}