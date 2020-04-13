package br.com.quiz.ui.questoes

import br.com.quiz.source.CallbackResponse
import br.com.quiz.source.PerfilRepositorio
import br.com.quiz.source.model.Perfil

class PresenterQuestoes (
    var repositorio: PerfilRepositorio,
    val fragment: ContratoQuestoes.View
) : ContratoQuestoes.Presenter {


    init {
        fragment.presenter = this
    }
    override fun start() {
      fragment.carregarQuestao(0)
    }

    override fun salvarPerfil(perfil: Perfil?) {
        repositorio.salvarPerfil(perfil)
            repositorio.pegarPerfil(object : CallbackResponse<Perfil> {
            override fun sucesso(response: Perfil) {
                val isFimJogo = fragment.isFimJogo()
                if(response.perguntaAtual==isFimJogo){
                     fragment.irTelaResultado()
                }else{
                    fragment.carregarQuestao(response.perguntaAtual)
                }
            }
            override fun erro() {

            }
        })
    }

}