package org.sopt.at.data

data class TvProgram(
    val id: Int,
    val title: String,
    val imageRes: Int,
    val rank: Int? = null
)
