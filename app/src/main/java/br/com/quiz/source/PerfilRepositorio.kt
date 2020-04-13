package br.com.quiz.source

import br.com.quiz.source.model.Perfil

class PerfilRepositorio (
    private val localDataSource: PerfilDataSource
) : PerfilDataSource {
    override fun pegarPerfil(callbackResponse: CallbackResponse<Perfil>) {
        localDataSource.pegarPerfil(callbackResponse)
    }

    override fun deletaPerfil(acao: () -> Unit) {
        localDataSource.deletaPerfil { acao() }
    }

    override fun salvarPerfil(perfil: Perfil?) {
      localDataSource.salvarPerfil(perfil)
    }
}