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
    val userId: Long,
    val nickname: String? = null
)

@Serializable
data class RequestSignInDto(
    val loginId: String,
    val password: String
)

@Serializable
data class ResponseSignInDto(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: SignUpData?
)

@Serializable
data class ResponseNicknameDto(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: NicknameData?
)

@Serializable
data class NicknameData(
    val nickname: String
)

@Serializable
data class ResponseNicknameListDto(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: NicknameListData
)

@Serializable
data class NicknameListData(
    val nicknameList: List<String>
)

@Serializable
data class ResponseModNicknameDto(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: RequestModNicknameDto?
)

@Serializable
data class RequestModNicknameDto(
    val nickname: String
)