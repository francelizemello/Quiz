package br.com.quiz.ui.questoes

import android.content.Context
import android.graphics.Color
import android.util.Log
import br.com.quiz.source.CallbackResponse
import br.com.quiz.source.PerfilRepositorio
import br.com.quiz.source.model.Perfil
import br.com.quiz.util.*
import kotlinx.android.synthetic.main.fragment_home.*

class PresenterQuestoes(
    var repositorio: PerfilRepositorio,
    val fragment: ContratoQuestoes.View,
    val context: Context
) : ContratoQuestoes.Presenter {

    init {
        fragment.presenter = this
    }

    override fun start() {
        repositorio.pegarPerfil(object : CallbackResponse<Perfil> {
            override fun sucesso(response: Perfil) {
                if (response.scoreNivel1 < 5)
                    fragment.carregarQuestao(0)
                else if (response.scoreNivel2 < 5 && response.scoreNivel1 >= 5)
                    fragment.carregarQuestao(5)
                if (response.scoreNivel3 < 5 && response.scoreNivel2 >= 5)
                    fragment.carregarQuestao(9)
                if (response.scoreNivel4 < 5 && response.scoreNivel3 >= 5)
                    fragment.carregarQuestao(14)
            }

            override fun erro() {
                fragment.carregarQuestao(0)
            }
        })
    }

    override fun salvarPerfil(perfil: Perfil?) {
    }

    override fun atualizarPerfil(perfil: Perfil?) {
        repositorio.pegarPerfil(object : CallbackResponse<Perfil> {
            override fun sucesso(response: Perfil) {
                val isFimJogo = fragment.isFimJogo()
                if (newPerfil(perfil, response).perguntaAtual == isFimJogo) {
                    Log.e("atualizarPerfil ", "entrou aqui " + response.perguntaAtual)
                    repositorio.updatePerfil(newPerfil(perfil, response))
                    fragment.irTelaResultado()
                } else {
                    repositorio.updatePerfil(newPerfil(perfil, response))
                    fragment.defaultColorText()
                    fragment.desmarcarRadio()
                    fragment.habilitarBotao()
                    Log.e("atualizarPerfil 2 ", "entrou aqui " + isFimJogo)
                    Log.e("atualizarPerfil 3 ", "entrou aqui " + response.perguntaAtual)
                    Log.e(
                        "atualizarPerfil 4 ",
                        "entrou aqui " + newPerfil(perfil, response).perguntaAtual
                    )
                    fragment.carregarQuestao(newPerfil(perfil, response).perguntaAtual)
                }
            }

            override fun erro() {
                Log.e("erro atualizarPerfil ", "entrou aqui " + perfil!!.perguntaAtual)
                fragment.defaultColorText()
                fragment.desmarcarRadio()
                fragment.habilitarBotao()
                repositorio.salvarPerfil(perfil)
                fragment.carregarQuestao(perfil!!.perguntaAtual)

            }
        })
    }

    private fun newPerfil(perfil: Perfil?, response: Perfil): Perfil {
        return Perfil(
            1,
            perfil!!.scoreTotal,
            perfil!!.perguntaAtual,
            if (perfil!!.scoreNivel1 == 0) response.scoreNivel1 else perfil!!.scoreNivel1,
            if (perfil!!.scoreNivel2 == 0) response.scoreNivel2 else perfil.scoreNivel2,
            if (perfil!!.scoreNivel3 == 0) response.scoreNivel3 else perfil.scoreNivel3,
            if (perfil!!.scoreNivel4 == 0) response.scoreNivel4 else perfil.scoreNivel4
        )
    }


}