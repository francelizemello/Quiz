package br.com.quiz.source.model

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("Question")
    val question: String,
    @SerializedName("OptionA")
    val optiona: String,
    @SerializedName("OptionB")
    val optionb: String,
    @SerializedName("OptionC")
    val optionc: String,
    @SerializedName("OptionD")
    val optiond: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("Answer")
    val answer: Int
    )

