package com.vctapps.iddogchallenge.login.data

import com.vctapps.iddogchallenge.login.data.localDataSource.LocalDataSource
import com.vctapps.iddogchallenge.login.data.remoteDataSource.RemoteDataSource
import com.vctapps.iddogchallenge.login.data.remoteDataSource.entity.LoginResponse
import com.vctapps.iddogchallenge.login.data.remoteDataSource.entity.UserResponse
import com.vctapps.iddogchallenge.login.data.throwables.InvalidUser
import io.reactivex.Completable
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.*

class TheGuardianRepositoryTest {

    lateinit var theGuardianRepository: TheGuardianRepository

    val localDataSource = mock(LocalDataSource::class.java)

    val remoteDataSource = mock(RemoteDataSource::class.java)

    val VALID_USER = "test@mail.com"

    val VALID_TOKEN = "123"

    val INVALID_USER = "123"

    @Before
    fun setUp(){
        theGuardianRepository = TheGuardianRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun `check if request to api sign in and save the token`(){
        given(localDataSource.saveToken(VALID_USER, VALID_TOKEN))
                .willReturn(Completable.complete())

        given(remoteDataSource.login(VALID_USER))
                .willReturn(Maybe.just(getMockLoginResponse()))

        theGuardianRepository.saveUser(VALID_USER)
                .test()
                .assertComplete()

        verify(remoteDataSource, times(1)).login(anyString())
        verify(localDataSource, times(1)).saveToken(anyString(), anyString())
    }

    @Test
    fun `check if can't sign in with invalid user`(){
        given(remoteDataSource.login(INVALID_USER)).willReturn(Maybe.error(InvalidUser()))

        theGuardianRepository.saveUser(INVALID_USER)
                .test()
                .assertError(InvalidUser::class.java)

        verify(remoteDataSource, times(1)).login(anyString())
        verify(localDataSource, times(0)).saveToken(anyString(), anyString())
    }

    @Test
    fun `successful get token if user has logged`(){
        given(localDataSource.getToken()).willReturn(Maybe.just(VALID_TOKEN))

        theGuardianRepository.getToken()
                .test()
                .assertComplete()
                .assertValue(VALID_TOKEN)

        verify(remoteDataSource, times(0)).login(anyString())
        verify(localDataSource, times(1)).getToken()
    }

    @Test
    fun `on error get token`(){
        given(localDataSource.getToken()).willReturn(Maybe.error(InvalidUser()))

        theGuardianRepository.getToken()
                .test()
                .assertError(InvalidUser::class.java)

        verify(remoteDataSource, times(0)).login(anyString())
        verify(localDataSource, times(1)).getToken()
    }

    @Test
    fun `successful check has user logged`(){
        given(localDataSource.getToken()).willReturn(Maybe.just(VALID_TOKEN))

        theGuardianRepository.hasUserStored()
                .test()
                .assertComplete()

        verify(remoteDataSource, times(0)).login(anyString())
        verify(localDataSource, times(1)).getToken()
    }

    @Test
    fun `on error check has user logged`(){
        given(localDataSource.getToken()).willReturn(Maybe.just(""))

        theGuardianRepository.hasUserStored()
                .test()
                .assertValue(false)

        verify(remoteDataSource, times(0)).login(anyString())
        verify(localDataSource, times(1)).getToken()
    }

    private fun getMockLoginResponse() = LoginResponse(
            getMockUserResponse()
    )

    private fun getMockUserResponse() = UserResponse(
            VALID_USER,
            VALID_TOKEN
    )

}