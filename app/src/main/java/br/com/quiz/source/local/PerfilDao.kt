package br.com.quiz.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.quiz.source.model.Perfil

@Dao
interface PerfilDao {
    @Query("SELECT * FROM  perfil")
    fun pegarPerfil(): List<Perfil>

    @Insert
    fun salvarPerfil(perfil: Perfil?)

    @Query("DELETE FROM perfil")
    fun deletarPerfil()
}