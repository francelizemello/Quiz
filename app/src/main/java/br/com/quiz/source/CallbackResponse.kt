package br.com.quiz.source

interface CallbackResponse<T> {

    fun sucesso(response: T)

    fun erro()
}