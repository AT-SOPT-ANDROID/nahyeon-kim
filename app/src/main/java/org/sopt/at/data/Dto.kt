package org.sopt.at.data

import kotlinx.serialization.Serializable

@Serializable
data class RequestSignUpDto(
    val loginId: String,
    val password: String,
    val nickname: String
)

@Serializable
data class ResponseSignUpDto(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: SignUpData?
)

@Serializable
data class SignUpData(
    val userId: Int,
    val nickname: String
)
