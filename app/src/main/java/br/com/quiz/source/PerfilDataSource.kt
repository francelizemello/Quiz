package br.com.quiz.source

import br.com.quiz.source.model.Perfil

interface PerfilDataSource {
    fun pegarPerfil(callbackResponse: CallbackResponse<Perfil>)

    fun deletaPerfil(acao: () -> Unit)

    fun salvarPerfil(
        perfil: Perfil?
    )
    fun updatePerfil(
        perfil: Perfil?
    )
}