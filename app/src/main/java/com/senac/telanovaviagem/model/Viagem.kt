package com.senac.telanovaviagem.model

data class Viagem (
    val destino: String = "",
    val orcamento: Float = 0.0f
)
{
    fun getdestino() = destino

    fun getorcamento() = orcamento
}
