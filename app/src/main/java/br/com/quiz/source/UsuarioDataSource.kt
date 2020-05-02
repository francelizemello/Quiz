package br.com.quiz.source

import br.com.quiz.source.model.Usuario

interface UsuarioDataSource {

    fun pegarUsuario(callbackResponse: CallbackResponse<Usuario>)

    fun deletaUsuario(acao: () -> Unit)

    fun salvarUsuario(
        usuario:Usuario?
    )
}