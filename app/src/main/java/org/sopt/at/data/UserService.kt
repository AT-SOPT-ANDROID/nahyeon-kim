package org.sopt.at.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/api/v1/auth/signup")
    fun signUp(
        @Body request: RequestSignUpDto
    ): Call<ResponseSignUpDto>

    @POST("/api/v1/auth/signin")
    fun signIn(
        @Body request: RequestSignInDto
    ): Call<ResponseSignInDto>
}
