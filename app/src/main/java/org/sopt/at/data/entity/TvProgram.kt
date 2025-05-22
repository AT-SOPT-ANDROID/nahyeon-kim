package org.sopt.at.data.entity

data class TvProgram(
    val id: Int,
    val title: String,
    val imageRes: Int,
    val rank: Int? = null
)
