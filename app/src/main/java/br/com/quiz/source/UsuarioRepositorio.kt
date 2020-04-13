package br.com.quiz.source

import br.com.quiz.source.model.Usuario

class UsuarioRepositorio
    (
    private val localDataSource: UsuarioDataSource
) : UsuarioDataSource {
    override fun pegarUsuario(callbackResponse: CallbackResponse<Usuario>) {
        localDataSource.pegarUsuario(callbackResponse)
    }

    override fun deletaUsuario(acao: () -> Unit) {
        localDataSource.deletaUsuario { acao() }

    }

    override fun salvarUsuario(usuario: Usuario?,acao: () -> Unit) {
        localDataSource.salvarUsuario(usuario,acao)
    }

    private lateinit var user : Usuario

    companion object {

        private var INSTANCE: UsuarioRepositorio? = null

        @JvmStatic
        fun getInstance(
            usuarioLocalDataSource: UsuarioDataSource
        ): UsuarioRepositorio {
            return INSTANCE ?: UsuarioRepositorio(usuarioLocalDataSource)
                .apply { INSTANCE = this }
        }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }

}
