package itu.m1.edukids.model

data class Quiz(
    val question: Question,
    val indice: Indice,
    val reponses: List<Reponse>
)
