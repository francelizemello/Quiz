package br.com.quiz.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.quiz.source.model.Usuario

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM  usuario")
    fun pegarUsuario(): List<Usuario>

    @Insert
    fun savarUsuario(usuario: Usuario?)

    @Query("DELETE FROM usuario")
    fun deletarUsuario()
}