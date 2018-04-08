package com.vctapps.iddogchallenge.login.data

import com.vctapps.iddogchallenge.core.data.ErrorResponse
import com.vctapps.iddogchallenge.core.data.IDdogApi
import com.vctapps.iddogchallenge.login.data.remoteDataSource.IDdogDataSource
import com.vctapps.iddogchallenge.login.data.remoteDataSource.RemoteDataSource
import com.vctapps.iddogchallenge.login.data.remoteDataSource.entity.LoginRequest
import com.vctapps.iddogchallenge.login.data.remoteDataSource.entity.LoginResponse
import com.vctapps.iddogchallenge.login.data.remoteDataSource.entity.UserResponse
import io.reactivex.Maybe
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.*
import org.mockito.Mockito
import org.mockito.Mockito.mock

class RemoteDataSourceTest {

    val api = mock(IDdogApi::class.java)

    lateinit var remoteDataSource: RemoteDataSource

    val INVALID_EMAIL = "123"

    val VALID_EMAIL = "test@mail.com"

    val VALID_TOKEN = "12"

    @Before
    fun setUp(){
        remoteDataSource = IDdogDataSource(api)
    }

    @Test
    fun `check if can sign in`(){
        val loginResponse = getMockLoginResponseSuccess()
        val loginRequest = LoginRequest(VALID_EMAIL)

        given(api.login(loginRequest)).willReturn(Maybe.just(loginResponse))

        remoteDataSource.login(VALID_EMAIL)
                .test()
                .assertValue(loginResponse)

        Mockito.verify(api, Mockito.times(1))
                .login(loginRequest)

    }

    @Test
    fun `on error check if cant sign in with invalid email`(){
        val loginResponse = getMockLoginResponseError()
        val loginRequest = LoginRequest(INVALID_EMAIL)

        given(api.login(loginRequest)).willReturn(Maybe.just(loginResponse))

        val response = remoteDataSource
                .login(INVALID_EMAIL)
                .test()
                .assertValue(loginResponse)

        assertThat(response.values()[0], `is`(loginResponse))

        Mockito.verify(api, Mockito.times(1))
                .login(loginRequest)

    }

    fun getMockLoginResponseSuccess() = LoginResponse(
            getMockUserResponse()
    )

    fun getMockLoginResponseError() = LoginResponse(error = ErrorResponse("Email is not valid"))

    private fun getMockUserResponse() = UserResponse(
            VALID_EMAIL,
            VALID_TOKEN
    )

}