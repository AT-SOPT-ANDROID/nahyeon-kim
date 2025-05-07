package org.sopt.at.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST("/api/v1/auth/signup")
    fun signUp(
        @Body request: RequestSignUpDto
    ): Call<ResponseSignUpDto>

    @POST("/api/v1/auth/signin")
    fun signIn(
        @Body request: RequestSignInDto
    ): Call<ResponseSignInDto>

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
