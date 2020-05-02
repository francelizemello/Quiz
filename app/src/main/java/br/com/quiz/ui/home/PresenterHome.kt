package br.com.quiz.ui.home

import android.content.Context
import android.util.Log
import br.com.quiz.source.CallbackResponse
import br.com.quiz.source.PerfilRepositorio
import br.com.quiz.source.model.Perfil

class PresenterHome(
    var repositorio: PerfilRepositorio,
    val fragment: ContratoHome.View,
    val context: Context
) : ContratoHome.Presenter {

    init {
        fragment.presenter = this
    }


    override fun start() {
        repositorio.pegarPerfil(object : CallbackResponse<Perfil> {
            override fun sucesso(response: Perfil) {
                Log.e("sucesso get ", " " + response.scoreTotal)
                Log.e("sucesso 2 get  ", " " + response.perguntaAtual)
                Log.e("sucesso 3 get ", " " + response.scoreNivel1)
                fragment.ativarCard(response)
                fragment.ativarBotaoNovoJogo(response)
            }

            override fun erro() {
                Log.e("erro pegarPerfil ", "entrou aquiii")
                fragment.ativarCard(Perfil(0, 0, 0, 0, 0, 0, 0))
                fragment.ativarBotaoNovoJogo(Perfil(0, 0, 0, 0, 0, 0, 0))
            }
        })
    }

    override fun deletarPerfil() {
        repositorio.deletaPerfil { }
    }
}