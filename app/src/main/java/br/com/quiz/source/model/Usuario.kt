package br.com.quiz.source.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class Usuario(
    @PrimaryKey()
    @SerializedName("nome")
    val nome:String
)