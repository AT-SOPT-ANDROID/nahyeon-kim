package org.sopt.at.data.api

import org.sopt.at.data.model.RequestModNicknameDto
import org.sopt.at.data.model.RequestSignInDto
import org.sopt.at.data.model.RequestSignUpDto
import org.sopt.at.data.model.ResponseModNicknameDto
import org.sopt.at.data.model.ResponseNicknameDto
import org.sopt.at.data.model.ResponseNicknameListDto
import org.sopt.at.data.model.ResponseSignInDto
import org.sopt.at.data.model.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST("/api/v1/auth/signup")
    suspend fun signUp(
        @Body request: RequestSignUpDto
    ): ResponseSignUpDto

    @POST("/api/v1/auth/signin")
    suspend fun signIn(
        @Body request: RequestSignInDto
    ): ResponseSignInDto

    @GET("/api/v1/users/me")
    fun getNickname(
        @Header("userId") userId: Long
    ): Call<ResponseNicknameDto>

    @GET("/api/v1/users")
    fun searchNickname(
        @Query("keyword") keyword: String?
    ) : Call<ResponseNicknameListDto>

    @PATCH("/api/v1/users")
    fun changeNickname(
        @Header("userId") userId: Long,
        @Body body: RequestModNicknameDto
    ): Call<ResponseModNicknameDto>
}
