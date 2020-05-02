package br.com.quiz.ui.questoes

import androidx.appcompat.widget.AppCompatRadioButton
import br.com.quiz.source.model.Perfil
import br.com.quiz.util.BasePresenter
import br.com.quiz.util.BaseView

class ContratoQuestoes {
    interface View : BaseView<Presenter> {
        fun responderPergunta()
        fun irProximaPergunta()
        fun exibirEfeitoQuestaoCerta(radioButton: AppCompatRadioButton)
        fun exibirEfeitoQuestaoErrada(radioButton: AppCompatRadioButton)
        fun isFimJogo(): Int
        fun carregarQuestao(int: Int)
        fun showDialogAviso()
        fun irTelaResultado()
        fun defaultColorText()
        fun habilitarBotao()
        fun desabilitaBotao()
        fun desmarcarRadio()
    }

    interface Presenter : BasePresenter {
        fun salvarPerfil(perfil: Perfil?)
        fun atualizarPerfil(perfil: Perfil?)
    }
}