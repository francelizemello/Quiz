package br.com.quiz.source.model

import com.google.gson.annotations.SerializedName

data class  questoes (
    @SerializedName("questoes")
    val questoesQuiz: List<Question>
)