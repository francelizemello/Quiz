package br.com.quiz.source.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
class Perfil (
    @PrimaryKey(autoGenerate = true)
    var id :Int,
    @SerializedName("scoreTotal")
    val scoreTotal:Int,
    @SerializedName("perguntaAtual")
    val perguntaAtual: Int,
    @SerializedName("scoreNivel1")
    val scoreNivel1: Int,
    @SerializedName("scoreNivel2")
    val scoreNivel2: Int,
    @SerializedName("scoreNivel3")
    val scoreNivel3: Int,
    @SerializedName("scoreNivel4")
    val scoreNivel4: Int
)